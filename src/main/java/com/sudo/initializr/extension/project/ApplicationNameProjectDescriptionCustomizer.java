package com.sudo.initializr.extension.project;

import io.spring.initializr.generator.project.MutableProjectDescription;
import io.spring.initializr.generator.project.ProjectDescriptionCustomizer;

public class ApplicationNameProjectDescriptionCustomizer implements ProjectDescriptionCustomizer {

  @Override
  public void customize(MutableProjectDescription description) {
    description.setApplicationName("Application");
  }
}
