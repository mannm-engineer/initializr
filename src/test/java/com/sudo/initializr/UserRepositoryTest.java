package com.sudo.initializr;

import com.sudo.initializr.entity.Address;
import com.sudo.initializr.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.transaction.TestTransaction;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(showSql = false)
@ActiveProfiles("test")
class UserRepositoryTest {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private TestEntityManager entityManager;

  private static User.UserBuilder userBuilder() {
    return User.builder().address(Address.builder().street("Street").city("City").zipcode("Zipcode").build());
  }

  @Test
  void givenNewUser_whenSave_thenUserIsCreated() {
    // ARRANGE
    User user = userBuilder().build();

    // ACT
    userRepository.save(user);
    TestTransaction.flagForCommit();
    TestTransaction.end();

    // ASSERT
    TestTransaction.start();
    User createdUser = entityManager.find(User.class, user.getId());
    assertThat(createdUser).usingRecursiveComparison().isEqualTo(user);
  }
}
