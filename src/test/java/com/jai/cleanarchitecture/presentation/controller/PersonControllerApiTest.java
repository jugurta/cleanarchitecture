package com.jai.cleanarchitecture.presentation.controller;

import com.jai.cleanarchitecture.domain.model.Person;
import com.jai.cleanarchitecture.presentation.dto.PersonDTO;
import com.jai.cleanarchitecture.presentation.mapper.PersonDTOMapper;
import com.jai.cleanarchitecture.presentation.mapper.PersonDTOMapperImpl;
import com.jai.cleanarchitecture.providers.PersonDTOProvider;
import com.jai.cleanarchitecture.usecase.CreatePersonUseCase;
import com.jai.cleanarchitecture.usecase.FetchPersonUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.MethodSource;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class PersonControllerApiTest {

    CreatePersonUseCase createPersonUseCase;
    FetchPersonUseCase fetchPersonUseCase;
    PersonDTOMapper personDTOMapper;
    PersonController personControllerApi;

    @BeforeEach
    void setUp() {
        personDTOMapper = new PersonDTOMapperImpl();
        createPersonUseCase = mock(CreatePersonUseCase.class);
        fetchPersonUseCase = mock(FetchPersonUseCase.class);
        personControllerApi = new PersonController(createPersonUseCase, fetchPersonUseCase, personDTOMapper);
    }

    @ParameterizedTest
    @ArgumentsSource(PersonDTOProvider.class)
    void givenPersonDTOShouldCreatePerson(PersonDTO personDTO) {
        //GIVE
        when(createPersonUseCase.createPerson(any(Person.class))).thenAnswer(invocationOnMock -> Mono.just(invocationOnMock.getArguments()[0]));
        //WHEN
        Mono<PersonDTO> result = personControllerApi.createPerson(personDTO);
        //THEN
        StepVerifier
                .create(result)
                .expectNext(personDTO)
                .verifyComplete();
        verify(createPersonUseCase, times(1)).createPerson(any(Person.class));
    }

    @ParameterizedTest
    @MethodSource("com.jai.cleanarchitecture.providers.PersonProviders#provideModelAndDTO")
    void whenFindByIdShouldReturnPersonDTO(Person person, PersonDTO personDTO) {
        //GIVEN
        when(fetchPersonUseCase.fetchById(anyLong())).thenAnswer(invocationOnMock -> Mono.just(person));
        //WHEN
        Mono<PersonDTO> result = personControllerApi.fetchPerson(1L);
        //THEN
        StepVerifier.create(result)
                .expectNext(personDTO)
                .verifyComplete();
    }

}
