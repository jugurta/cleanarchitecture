package com.jai.cleanarchitecture.providers;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class PersonProviders {

    private static Stream<Arguments> provideModelAndEntity() {
        return Stream.of(Arguments.of(new PersonProvider().getPerson(), new PersonEntityProvider().getPersonEntity()));
    }

    private static Stream<Arguments> provideModelAndDTO() {
        return Stream.of(Arguments.of(new PersonProvider().getPerson(), new PersonDTOProvider().getPersonDTO()));
    }
}