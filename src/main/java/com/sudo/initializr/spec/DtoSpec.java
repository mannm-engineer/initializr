package com.sudo.initializr.spec;

import com.palantir.javapoet.FieldSpec;
import com.palantir.javapoet.MethodSpec;
import com.palantir.javapoet.ParameterSpec;
import com.palantir.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import java.util.List;

public record DtoSpec(String name,
  List<FieldSpec> fieldSpecs) {

  public TypeSpec typeSpec() {
    return TypeSpec
      .recordBuilder(name)
      .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
      .recordConstructor(MethodSpec
        .constructorBuilder()
        .addParameters(fieldSpecs
          .stream()
          .map(fieldSpec -> ParameterSpec.builder(fieldSpec.type(), fieldSpec.name()).build())
          .toList())
        .build())
      .build();
  }
}
