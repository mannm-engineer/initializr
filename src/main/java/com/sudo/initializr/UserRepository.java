package com.sudo.initializr;

import com.sudo.initializr.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {}
