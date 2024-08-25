# fw-commons

Fw-Commons [a commons Java lib]() with spring boot framework, Supports using keycloak, filter, trace log, cached, minio
server, exception handler, validate and call API with webclient

This README provides quickstart instructions on running [`fw-commons`]() on bare metal project spring boot.



[![SonarCloud](https://sonarcloud.io/images/project_badges/sonarcloud-black.svg)](https://sonarcloud.io/project/overview?id=hoangtien2k3_fw-commons)

[![CircleCI](https://circleci.com/gh/hoangtien2k3/fw-commons.svg?style=svg)](https://app.circleci.com/pipelines/github/hoangtien2k3/fw-commons)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=hoangtien2k3_fw-commons&metric=ncloc)](https://sonarcloud.io/summary/overall?id=hoangtien2k3_fw-commons)
![GitHub Release](https://img.shields.io/github/v/release/hoangtien2k3/fw-commons?label=latest%20release)
[![License](https://img.shields.io/badge/license-Apache--2.0-green.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)
[![JDK](https://img.shields.io/badge/jdk-21-green.svg)](https://www.oracle.com/java/technologies/downloads/#java21)
[![Build status](https://github.com/ponfee/commons-core/workflows/build-with-maven/badge.svg)](https://github.com/hoangtien2k3/fw-commons/actions)


#### ⬇️ [Download From Gradle and Maven Central](https://central.sonatype.com/artifact/io.github.hoangtien2k3/fw-commons/1.1.3) 

#### Gradle

```kotlin
implementation("io.github.hoangtien2k3:fw-commons:1.1.3")
```

#### Maven

```xml
<dependency>
    <groupId>io.github.hoangtien2k3</groupId>
    <artifactId>fw-commons</artifactId>
    <version>1.1.3</version>
</dependency>
```

## Installation and Getting Started

1. Correct and complete setup to start the program `application.yml` or `application.properties`
   with [CONFIG](src/main/resources/application.yml)

2. The [reference documentation]() includes detailed [installation instructions]() as well as a
   comprehensive [getting started]() guide.

Here is a quick teaser of a complete Spring Boot application in Java:

```java
@SpringBootApplication
@ComponentScan(basePackages = {"io.hoangtien2k3.commons.*"})
@ImportResource({"classpath*:applicationContext.xml"})
public class Example {

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        SpringApplication.run(Example.class, args);
    }
}
```

## Contributing

If you would like to contribute to the development of this project, please follow our contribution guidelines.

![Alt](https://repobeats.axiom.co/api/embed/31a861bf21d352264c5c122808407abafb97b0ef.svg "Repobeats analytics image")


## License

This project is licensed under the [`MIT License`](LICENSE).

```text
MIT License
Copyright (c) 2024 Hoàng Anh Tiến
```

## Contributors ✨

<table>
  <tr>
    <td align="center"><a href="https://www.linkedin.com/in/hoangtien2k3/"><img src="https://avatars.githubusercontent.com/u/122768076?v=4?s=100" width="100px;" alt=""/><br /><sub><b>Hoàng Anh Tiến</b></sub></a><br /><a href="https://github.com/hoangtien2k3/news-app/commits?author=hoangtien2k3" title="Code">💻</a> <a href="#maintenance-hoangtien2k3" title="Maintenance">🚧</a> <a href="#ideas-hoangtien2k3" title="Ideas, Planning, & Feedback">🤔</a> <a href="#design-hoangtien2k3" title="Design">🎨</a> <a href="https://github.com/hoangtien2k3/news-app/issues?q=author%hoangtien2k3" title="Bug reports">🐛</a></td>
  </tr>
</table>
