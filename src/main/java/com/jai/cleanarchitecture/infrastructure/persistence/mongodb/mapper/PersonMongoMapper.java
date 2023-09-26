package com.jai.cleanarchitecture.infrastructure.persistence.mongodb.mapper;

import com.jai.cleanarchitecture.domain.model.Person;
import com.jai.cleanarchitecture.infrastructure.persistence.mongodb.entity.PersonEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PersonMongoMapper {
    Person toDomain(PersonEntity personEntity);

    PersonEntity toEntity(Person person);
}
