package com.jai.cleanarchitecture.infrastructure.persistence.mongodb.repository;

import com.jai.cleanarchitecture.infrastructure.persistence.mongodb.entity.PersonEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ReactivePersonMongoRepository extends ReactiveMongoRepository<PersonEntity, Long> {
}
