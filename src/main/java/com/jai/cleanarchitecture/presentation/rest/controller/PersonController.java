package com.jai.cleanarchitecture.presentation.rest.controller;

import com.jai.cleanarchitecture.presentation.rest.dto.PersonDTO;
import com.jai.cleanarchitecture.presentation.rest.mapper.PersonDTOMapper;
import com.jai.cleanarchitecture.usecase.CreatePersonUseCase;
import com.jai.cleanarchitecture.usecase.FetchPersonUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    private final CreatePersonUseCase createPersonUseCase;
    private final FetchPersonUseCase fetchPersonUseCase;
    private final PersonDTOMapper personDTOMapper;

    @PostMapping
    Mono<PersonDTO> createPerson(@RequestBody PersonDTO personDTO) {
        return createPersonUseCase.createPerson(personDTOMapper.toDomain(personDTO)).map(personDTOMapper::toDTO);
    }


    @GetMapping(path = "{id}")
    Mono<PersonDTO> fetchPerson(@PathVariable Long id) {
        return fetchPersonUseCase.fetchById(id).map(personDTOMapper::toDTO);
    }
}
