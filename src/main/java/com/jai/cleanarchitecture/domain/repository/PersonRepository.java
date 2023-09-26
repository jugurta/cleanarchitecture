package com.jai.cleanarchitecture.domain.repository;

import com.jai.cleanarchitecture.domain.model.Person;
import reactor.core.publisher.Mono;

public interface PersonRepository {
    Mono<Person> save(Person person);

    Mono<Person> findById(Long id);

}