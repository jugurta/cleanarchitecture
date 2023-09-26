package com.jai.cleanarchitecture.infrastructure.persistence.mongodb.mapper;

import com.jai.cleanarchitecture.domain.model.Person;
import com.jai.cleanarchitecture.infrastructure.persistence.mongodb.entity.PersonEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class PersonMongoMapperTest {

    PersonMongoMapper personMongoMapper;

    @BeforeEach
    void setUp() {
        personMongoMapper = new PersonMongoMapperImpl();
    }

    @ParameterizedTest
    @MethodSource("com.jai.cleanarchitecture.providers.PersonProviders#provideModelAndEntity")
    void givenPersonEntityShouldMapPersonModel(Person person, PersonEntity personEntity) {
        Assertions.assertEquals(personMongoMapper.toEntity(person), personEntity);
    }

    @ParameterizedTest
    @MethodSource("com.jai.cleanarchitecture.providers.PersonProviders#provideModelAndEntity")
    void givenPersonModelShouldMapPersonEntity(Person person, PersonEntity personEntity) {
        Assertions.assertEquals(personMongoMapper.toDomain(personEntity), person);
    }

}
