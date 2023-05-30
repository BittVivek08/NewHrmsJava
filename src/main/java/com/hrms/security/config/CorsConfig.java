package com.hrms.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
      .allowedOrigins("http://localhost:4200") // Replace with your frontend origin
      .allowedMethods("GET", "POST", "PUT", "DELETE")
      .allowedHeaders("Authorization", "Content-Type")
      .allowCredentials(true);
  }
}
