package com.sudo.initializr.spec;

import com.google.common.base.CaseFormat;
import com.palantir.javapoet.AnnotationSpec;
import com.palantir.javapoet.FieldSpec;
import com.palantir.javapoet.TypeSpec;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import javax.lang.model.element.Modifier;
import java.util.List;

public record EntitySpec(String tableName,
  DtoSpec dtoSpec) {

  public TypeSpec typeSpec() {
    String className = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName) + "Entity";
    return TypeSpec
      .classBuilder(className)
      .addModifiers(Modifier.PUBLIC)
      .addAnnotations(classAnnotations())
      .addFields(fields())
      .build();
  }

  private List<AnnotationSpec> classAnnotations() {
    return List.of(
      AnnotationSpec.builder(Entity.class).build(), AnnotationSpec
        .builder(Table.class)
        .addMember("name", "$S", tableName)
        .build(), AnnotationSpec.builder(NoArgsConstructor.class).build(), AnnotationSpec
          .builder(AllArgsConstructor.class)
          .build(), AnnotationSpec.builder(Builder.class).build(), AnnotationSpec
            .builder(Getter.class)
            .build(), AnnotationSpec.builder(Setter.class).build()
    );
  }

  private List<FieldSpec> fields() {
    return dtoSpec
      .fieldSpecs()
      .stream()
      .map(this::fieldSpec)
      .toList();
  }

  private FieldSpec fieldSpec(FieldSpec fieldSpec) {
    if (fieldSpec.name().equals("id")) {
      return FieldSpec
        .builder(Long.class, fieldSpec.name(), Modifier.PRIVATE)
        .addAnnotation(AnnotationSpec.builder(Id.class).build())
        .addAnnotation(AnnotationSpec.builder(GeneratedValue.class).build())
        .build();
    }

    return FieldSpec.builder(fieldSpec.type(), fieldSpec.name(), Modifier.PRIVATE).build();
  }
}
