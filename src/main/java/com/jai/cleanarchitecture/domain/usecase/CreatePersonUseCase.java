package com.jai.cleanarchitecture.usecase;

import com.jai.cleanarchitecture.domain.model.Person;
import com.jai.cleanarchitecture.domain.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;


@Component
@RequiredArgsConstructor
public class CreatePersonUseCase {
    private final PersonRepository personRepository;

    public Mono<Person> createPerson(Person person) {
        return personRepository.save(person);
    }
}
