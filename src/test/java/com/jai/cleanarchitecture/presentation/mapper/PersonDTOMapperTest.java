package com.jai.cleanarchitecture.presentation.mapper;

import com.jai.cleanarchitecture.domain.model.Person;
import com.jai.cleanarchitecture.presentation.dto.PersonDTO;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class PersonDTOMapperTest {

    PersonDTOMapper personDTOMapper;

    @BeforeEach
    void setUp() {
        personDTOMapper = new PersonDTOMapperImpl();
    }

    @ParameterizedTest
    @MethodSource("com.jai.cleanarchitecture.providers.PersonProviders#provideModelAndDTO")
    void givenPersonDTOShouldMapPersonModel(Person person, PersonDTO personDTO) {
        Assertions.assertEquals(personDTOMapper.toDomain(personDTO), person);
    }

    @ParameterizedTest
    @MethodSource("com.jai.cleanarchitecture.providers.PersonProviders#provideModelAndDTO")
    void givenPersonModelShouldMapPersonDTO(Person person, PersonDTO personDTO) {
        Assertions.assertEquals(personDTOMapper.toDTO(person), personDTO);
    }
}
