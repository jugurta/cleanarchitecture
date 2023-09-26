package com.jai.cleanarchitecture.presentation.mapper;

import com.jai.cleanarchitecture.domain.model.Person;
import com.jai.cleanarchitecture.presentation.dto.PersonDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PersonDTOMapper {
    Person toDomain(PersonDTO personDTO);

    PersonDTO toDTO(Person person);
}