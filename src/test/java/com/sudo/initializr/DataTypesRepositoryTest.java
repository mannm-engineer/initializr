package com.sudo.initializr;

import com.sudo.initializr.gen.entity.DataTypesEntity;
import com.sudo.initializr.gen.entity.Enum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.transaction.TestTransaction;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Currency;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest(showSql = false)
@ActiveProfiles(profiles = "test")
class DataTypesRepositoryTest {

  @Autowired
  private DataTypesRepository dataTypesRepository;

  @Autowired
  private TestEntityManager entityManager;

  private static DataTypesEntity.DataTypesEntityBuilder dataTypesEntityBuilder() {
    return DataTypesEntity
      .builder()
      .enumValue(Enum.VALUE_2)
      .booleanValue(true)
      .byteValue((byte) 1)
      .shortValue((short) 5)
      .integerValue(25)
      .bigIntegerValue(new BigInteger("12345678901234567890"))
      .longValue(123456789L)
      .floatValue(12.5f)
      .doubleValue(500.123456)
      .bigDecimalValue(new BigDecimal("9876543210.123456"))
      .characterValue('M')
      .stringValue("Tony Stark")
      .durationValue(Duration.ofHours(1))
      .instantValue(Instant.now().truncatedTo(ChronoUnit.MICROS))
      .localDateValue(LocalDate.now())
      .localTimeValue(LocalTime.now().truncatedTo(ChronoUnit.MILLIS))
      .localDateTimeValue(LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS))
      .offsetDateTimeValue(OffsetDateTime.now().withOffsetSameInstant(ZoneOffset.UTC).truncatedTo(ChronoUnit.MILLIS))
      .offsetTimeValue(OffsetTime.now().withOffsetSameInstant(ZoneOffset.UTC).truncatedTo(ChronoUnit.SECONDS))
      .zonedDateTimeValue(ZonedDateTime.now().withZoneSameInstant(ZoneOffset.UTC).truncatedTo(ChronoUnit.MICROS))
      .zoneOffsetValue(ZoneOffset.ofHours(7))
      .currencyValue(Currency.getInstance("USD"))
      .localeValue(Locale.US)
      .uuidValue(UUID.randomUUID());
  }

  @Test
  void givenNewDataTypes_whenSave_thenDataTypesIsCreated() {
    // ARRANGE
    DataTypesEntity dataTypes = dataTypesEntityBuilder().build();

    // ACT
    dataTypesRepository.save(dataTypes);
    TestTransaction.flagForCommit();
    TestTransaction.end();

    // ASSERT
    TestTransaction.start();
    DataTypesEntity createdDataTypes = entityManager.find(DataTypesEntity.class, dataTypes.getId());
    assertThat(createdDataTypes).usingRecursiveComparison().isEqualTo(dataTypes);
  }

  @Test
  public void givenExistingDataTypes_whenFindById_thenReturnCorrectDataTypes() {
    // ARRANGE
    DataTypesEntity dataTypes = dataTypesEntityBuilder().build();
    entityManager.persist(dataTypes);
    TestTransaction.flagForCommit();
    TestTransaction.end();

    // ACT
    TestTransaction.start();
    Optional<DataTypesEntity> fetchedDataTypes = dataTypesRepository.findById(dataTypes.getId());

    // ASSERT
    assertThat(fetchedDataTypes).isPresent();
    assertThat(fetchedDataTypes.get()).usingRecursiveComparison().isEqualTo(dataTypes);
  }

  @Test
  void givenNonExistentDataTypesId_whenFindById_thenReturnEmptyOptional() {
    // ARRANGE
    Long nonExistentDataTypesId = -1L;

    // ACT
    Optional<DataTypesEntity> fetchedDataTypesOptional = dataTypesRepository.findById(nonExistentDataTypesId);

    // ASSERT
    assertThat(fetchedDataTypesOptional).isNotPresent();
  }

  @Test
  public void givenExistingDataTypes_whenReplace_thenDataTypesIsReplaced() {
    // ARRANGE
    DataTypesEntity dataTypes = dataTypesEntityBuilder().build();
    dataTypesRepository.save(dataTypes);
    TestTransaction.flagForCommit();
    TestTransaction.end();

    // ACT
    DataTypesEntity replacedDataTypes = dataTypesEntityBuilder().id(dataTypes.getId()).stringValue("Elon Musk").build();
    dataTypesRepository.save(replacedDataTypes);

    // ASSERT
    TestTransaction.start();
    DataTypesEntity createdDataTypes = entityManager.find(DataTypesEntity.class, replacedDataTypes.getId());
    assertThat(createdDataTypes).usingRecursiveComparison().isEqualTo(replacedDataTypes);
  }

  @Test
  public void givenNonExistentDataTypesId_whenSave_thenExceptionIsThrown() {
    // ARRANGE
    Long nonExistentDataTypesId = -1L;
    DataTypesEntity dataTypes = dataTypesEntityBuilder().id(nonExistentDataTypesId).build();

    // ACT + ASSERT
    assertThrows(OptimisticLockingFailureException.class, () -> dataTypesRepository.save(dataTypes));
  }

  @Test
  public void givenExistingDataTypes_whenDeleteById_thenDataTypesIsRemoved() {
    // ARRANGE
    DataTypesEntity dataTypes = dataTypesEntityBuilder().build();
    entityManager.persist(dataTypes);
    TestTransaction.flagForCommit();
    TestTransaction.end();

    // ACT
    dataTypesRepository.deleteById(dataTypes.getId());

    // ASSERT
    TestTransaction.start();
    DataTypesEntity createdDataTypes = entityManager.find(DataTypesEntity.class, dataTypes.getId());
    assertThat(createdDataTypes).isNull();
  }

  @Test
  void givenNonExistentDataTypesId_whenDeleteById_thenNoExceptionThrown() {
    // ARRANGE
    Long nonExistentDataTypesId = -1L;

    // ACT + ASSERT
    assertThatCode(() -> dataTypesRepository.deleteById(nonExistentDataTypesId)).doesNotThrowAnyException();
  }
}
