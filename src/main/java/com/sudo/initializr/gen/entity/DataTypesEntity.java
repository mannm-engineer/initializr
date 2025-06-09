package com.sudo.initializr.gen.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.lang.Boolean;
import java.lang.Byte;
import java.lang.Character;
import java.lang.Double;
import java.lang.Float;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Short;
import java.lang.String;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Currency;
import java.util.Locale;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
  name = "data_types"
)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class DataTypesEntity {

  @Id
  @GeneratedValue
  private Long id;

  private Enum enumValue;

  private Boolean booleanValue;

  private Byte byteValue;

  private Short shortValue;

  private Integer integerValue;

  private BigInteger bigIntegerValue;

  private Long longValue;

  private Float floatValue;

  private Double doubleValue;

  private BigDecimal bigDecimalValue;

  private Character characterValue;

  private String stringValue;

  private Duration durationValue;

  private Instant instantValue;

  private LocalDate localDateValue;

  private LocalTime localTimeValue;

  private LocalDateTime localDateTimeValue;

  private OffsetDateTime offsetDateTimeValue;

  private OffsetTime offsetTimeValue;

  private ZonedDateTime zonedDateTimeValue;

  private ZoneOffset zoneOffsetValue;

  private Currency currencyValue;

  private Locale localeValue;

  private UUID uuidValue;
}
