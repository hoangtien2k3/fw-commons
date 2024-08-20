/*
 * Copyright 2024 author - Hoàng Anh Tiến
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 */
package io.hoangtien2k3.commons.utils;

import io.hoangtien2k3.commons.constants.Constants;
import io.hoangtien2k3.commons.factory.ObjectMapperFactory;
import io.hoangtien2k3.commons.model.TokenUser;
import io.hoangtien2k3.commons.model.UserDTO;
import com.nimbusds.jose.util.Base64;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.oauth2.jwt.Jwt;
import reactor.core.publisher.Mono;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.SignatureException;
import java.util.Map;

/**
 * Utility class for security-related operations. Provides methods to extract
 * the current user, generate HMAC, and check authorization.
 */
public class SecurityUtils {

  /**
   * Retrieves the current authenticated user as a TokenUser.
   *
   * @return a Mono containing the TokenUser, or empty if no user is authenticated
   */
  public static Mono<TokenUser> getCurrentUser() {
    return ReactiveSecurityContextHolder.getContext().map(SecurityContext::getAuthentication)
        .flatMap(authentication -> Mono.justOrEmpty(extractUser(authentication)));
  }

  /**
   * Retrieves the token of the current authenticated user.
   *
   * @return a Mono containing the token as a String, or empty if no token is
   *         found
   */
  public static Mono<String> getTokenUser() {
    return ReactiveSecurityContextHolder.getContext().map(SecurityContext::getAuthentication)
        .flatMap(authentication -> Mono.justOrEmpty(extractToken(authentication)));
  }

  /**
   * Extracts the token from the given Authentication object.
   *
   * @param authentication
   *            the Authentication object from which to extract the token
   * @return the token as a String, or null if no token is found
   */
  public static String extractToken(Authentication authentication) {
    if (authentication == null)
      return null;
    Jwt jwt = (Jwt) authentication.getPrincipal();
    if (jwt == null)
      return null;
    return jwt.getTokenValue();
  }

  /**
   * Extracts the TokenUser from the given Authentication object.
   *
   * @param authentication
   *            the Authentication object from which to extract the user
   * @return the TokenUser, or null if no user is found
   */
  public static TokenUser extractUser(Authentication authentication) {
    if (authentication == null)
      return null;
    Jwt jwt = (Jwt) authentication.getPrincipal();
    if (jwt == null)
      return null;
    Map<String, Object> claims = jwt.getClaims();
    if (claims == null)
      return null;
    return TokenUser.builder().username((String) claims.get(Constants.TokenProperties.USERNAME))
        .id((String) claims.get(Constants.TokenProperties.ID))
        .email((String) claims.get(Constants.TokenProperties.EMAIL))
        .name((String) claims.get(Constants.TokenProperties.NAME))
        .organizationId(DataUtil.safeToString(claims.get(Constants.TokenProperties.ORGANIZATION_ID))).build();
  }

  /**
   * Generates an HMAC for the given data using the specified key and algorithm.
   *
   * @param data
   *            the data to be signed
   * @param key
   *            the key to be used for signing
   * @param algorithm
   *            the algorithm to be used for signing
   * @return the generated HMAC as a String
   * @throws SignatureException
   *             if an error occurs during HMAC generation
   */
  public static String hmac(String data, String key, String algorithm) throws SignatureException {
    String result;
    try {
      SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), algorithm);
      Mac mac = Mac.getInstance(algorithm);
      mac.init(signingKey);
      byte[] rawHmac = mac.doFinal(data.getBytes());
      result = String.valueOf(Base64.encode(rawHmac));
    } catch (Exception e) {
      throw new SignatureException("Failed to generate HMAC : " + e);
    }
    return result;
  }

  /**
   * Checks if the current user is authorized.
   *
   * @return a Mono containing true if the user is authorized, false otherwise
   */
  public static Mono<Boolean> isAuthorized() {
    return ReactiveSecurityContextHolder.getContext().map(SecurityContext::getAuthentication)
        .flatMap(authentication -> {
          if (authentication == null || authentication.getPrincipal() == null) {
            return Mono.just(false);
          }
          return Mono.just(true);
        }).switchIfEmpty(Mono.just(false));
  }

  /**
   * Retrieves the UserDTO from the given access token.
   *
   * @param accessToken
   *            the access token from which to extract the user
   * @return the UserDTO, or null if an error occurs during extraction
   */
  public static UserDTO getUserByAccessToken(String accessToken) {
    SignedJWT signedJWT = null;
    try {
      signedJWT = SignedJWT.parse(accessToken);
      String data = signedJWT.getPayload().toString();
      return ObjectMapperFactory.getInstance().readValue(data, UserDTO.class);
    } catch (Exception e) {
      return null;
    }
  }
}
