package com.jai.cleanarchitecture.infrastructure.persistence.mongodb.adapter;

import com.jai.cleanarchitecture.domain.model.Person;
import com.jai.cleanarchitecture.domain.repository.PersonRepository;
import com.jai.cleanarchitecture.infrastructure.persistence.mongodb.mapper.PersonMongoMapper;
import com.jai.cleanarchitecture.infrastructure.persistence.mongodb.repository.ReactivePersonMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PersonRepositoryAdapter implements PersonRepository {

    private final ReactivePersonMongoRepository reactivePersonMongoRepository;
    private final PersonMongoMapper personMongoMapper;

    @Override
    public Mono<Person> save(Person person) {
        return reactivePersonMongoRepository.save(personMongoMapper.toEntity(person)).map(personMongoMapper::toDomain);
    }

    @Override
    public Mono<Person> findById(Long id) {
        return reactivePersonMongoRepository.findById(id).map(personMongoMapper::toDomain);
    }
}
