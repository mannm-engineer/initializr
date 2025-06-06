package com.sudo.initializr.extension.project;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectDescriptionCustomizerConfiguration {

  @Bean
  ApplicationNameProjectDescriptionCustomizer customProjectDescriptionCustomizer() {
    return new ApplicationNameProjectDescriptionCustomizer();
  }
}
