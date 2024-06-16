package com.example.callingapisfromsiffproject.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.data.redis")
public class RedisConfig {
  private String host;
  private int port;
}
