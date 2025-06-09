package com.sudo.initializr;

import com.palantir.javapoet.FieldSpec;
import com.palantir.javapoet.JavaFile;
import com.sudo.initializr.gen.entity.Enum;
import com.sudo.initializr.spec.DtoSpec;
import com.sudo.initializr.spec.EntitySpec;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Path;
import java.time.*;
import java.util.*;

class EntitySpecTest {

  private static final Path OUTPUT_PATH = Path.of(
    "C:/Users/nmman/Desktop/sudo/initializr/src/main/java"
  );

  @Test
  void createEntityClass_shouldWriteJavaFileSuccessfully() throws IOException {
    EntitySpec entitySpec = new EntitySpec("data_types", createDtoSpec());

    JavaFile javaFile = JavaFile.builder("com.sudo.initializr.gen.entity", entitySpec.typeSpec()).build();
    javaFile.writeTo(OUTPUT_PATH);
  }

  private DtoSpec createDtoSpec() {
    List<FieldSpec> fields = List.of(
      // ID
      FieldSpec.builder(String.class, "id").build(),

      // Enums
      FieldSpec.builder(Enum.class, "enumValue").build(),

      // Primitive Wrappers
      FieldSpec.builder(Boolean.class, "booleanValue").build(),
      FieldSpec.builder(Byte.class, "byteValue").build(),
      FieldSpec.builder(Short.class, "shortValue").build(),
      FieldSpec.builder(Integer.class, "integerValue").build(),
      FieldSpec.builder(BigInteger.class, "bigIntegerValue").build(),
      FieldSpec.builder(Long.class, "longValue").build(),
      FieldSpec.builder(Float.class, "floatValue").build(),
      FieldSpec.builder(Double.class, "doubleValue").build(),
      FieldSpec.builder(BigDecimal.class, "bigDecimalValue").build(),
      FieldSpec.builder(Character.class, "characterValue").build(),

      // String
      FieldSpec.builder(String.class, "stringValue").build(),

      // java.time.*
      FieldSpec.builder(Duration.class, "durationValue").build(),
      FieldSpec.builder(Instant.class, "instantValue").build(),
      FieldSpec.builder(LocalDate.class, "localDateValue").build(),
      FieldSpec.builder(LocalTime.class, "localTimeValue").build(),
      FieldSpec.builder(LocalDateTime.class, "localDateTimeValue").build(),
      FieldSpec.builder(OffsetDateTime.class, "offsetDateTimeValue").build(),
      FieldSpec.builder(OffsetTime.class, "offsetTimeValue").build(),
      FieldSpec.builder(ZonedDateTime.class, "zonedDateTimeValue").build(),
      FieldSpec.builder(ZoneOffset.class, "zoneOffsetValue").build(),

      // Currency, Locale, UUID
      FieldSpec.builder(Currency.class, "currencyValue").build(),
      FieldSpec.builder(Locale.class, "localeValue").build(),
      FieldSpec.builder(UUID.class, "uuidValue").build()
    );

    return new DtoSpec("DataTypes", fields);
  }
}
