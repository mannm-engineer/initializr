package com.sudo.initializr;

import com.sudo.initializr.gen.entity.DataTypesEntity;
import org.springframework.data.repository.CrudRepository;

public interface DataTypesRepository extends CrudRepository<DataTypesEntity, Long> {}
