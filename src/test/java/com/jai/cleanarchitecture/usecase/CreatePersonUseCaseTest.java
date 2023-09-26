package com.jai.cleanarchitecture.usecase;

import com.jai.cleanarchitecture.domain.model.Person;
import com.jai.cleanarchitecture.domain.repository.PersonRepository;
import com.jai.cleanarchitecture.providers.PersonProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreatePersonUseCaseTest {

    PersonRepository personRepository;
    CreatePersonUseCase createPersonUseCase;

    @BeforeEach
    void setUp() {
        personRepository = mock(PersonRepository.class);
        createPersonUseCase = new CreatePersonUseCase(personRepository);
    }

    @ParameterizedTest
    @ArgumentsSource(PersonProvider.class)
    void givenPersonToSaveShouldReturnPersonModel(Person person) {
        //GIVEN
        when(personRepository.save(any(Person.class))).thenAnswer(invocationOnMock -> Mono.just(person));
        //WHEN
        Mono<Person> result = createPersonUseCase.createPerson(person);
        //THEN
        StepVerifier
                .create(result)
                .expectNext(person)
                .verifyComplete();
        verify(personRepository, times(1)).save(person);
    }


}
