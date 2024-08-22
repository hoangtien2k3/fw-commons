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
package io.hoangtien2k3.commons.filter.properties;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.logging.LoggingMeterRegistry;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The `MonitoringProperties` class holds configuration properties related to
 * monitoring within an application. It uses Lombok annotations to automatically
 * generate boilerplate code such as getters, setters, and constructors.
 *
 * <h2>Attributes:</h2>
 * <ul>
 * <li><strong>isEnable</strong>: A boolean flag indicating whether monitoring
 * is enabled or not. Defaults to `true` if not explicitly set.</li>
 * <li><strong>meterRegistry</strong>: An instance of `MeterRegistry` used for
 * registering and managing metrics. Defaults to a `LoggingMeterRegistry` if not
 * explicitly set.</li>
 * </ul>
 *
 * <h2>Lombok Annotations:</h2>
 * <ul>
 * <li><strong>@Data</strong>: Generates getters, setters, `equals()`,
 * `hashCode()`, and `toString()` methods automatically.</li>
 * <li><strong>@AllArgsConstructor</strong>: Creates a constructor with all
 * fields as parameters, allowing for easy instantiation of the class with all
 * properties set.</li>
 * <li><strong>@NoArgsConstructor</strong>: Provides a no-argument constructor
 * for creating instances of the class without initializing the fields.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 *
 * <pre>
 * {
 * 	&#64;code
 * 	&#64;Configuration
 * 	&#64;ConfigurationProperties(prefix = "monitoring")
 * 	public class MonitoringConfig {
 *
 * 		private final MonitoringProperties monitoringProperties;
 *
 * 		&#64;Autowired
 * 		public MonitoringConfig(MonitoringProperties monitoringProperties) {
 * 			this.monitoringProperties = monitoringProperties;
 * 		}
 *
 * 		@PostConstruct
 * 		public void init() {
 * 			// Example of accessing properties
 * 			System.out.println("Monitoring Enabled: " + monitoringProperties.isEnable());
 * 			System.out.println("Meter Registry: " + monitoringProperties.getMeterRegistry());
 * 		}
 * 	}
 * }
 * </pre>
 *
 * <h2>Configuration Example:</h2>
 *
 * <pre>{@code
 * # application.yml
 * monitoring:
 *   isEnable: true
 *   meterRegistry: com.example.CustomMeterRegistry
 * }</pre>
 *
 * <h2>Detailed Description:</h2>
 *
 * <p>
 * The `MonitoringProperties` class is used to configure monitoring settings for
 * an application. The `isEnable` flag determines whether monitoring is active,
 * while the `meterRegistry` attribute specifies the type of meter registry to
 * be used for managing metrics.
 * </p>
 *
 * <p>
 * The default value for `isEnable` is `true`, meaning monitoring is enabled by
 * default unless explicitly disabled.
 * </p>
 *
 * <p>
 * The `meterRegistry` is an instance of `MeterRegistry`, which is responsible
 * for collecting and reporting metrics. The default value is an instance of
 * `LoggingMeterRegistry`, which logs metrics to the console. You can replace
 * this with a custom `MeterRegistry` implementation as needed.
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonitoringProperties {
    private boolean isEnable = true;
    private MeterRegistry meterRegistry = new LoggingMeterRegistry();
}
