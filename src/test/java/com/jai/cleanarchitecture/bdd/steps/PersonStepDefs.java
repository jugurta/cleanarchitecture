package com.jai.cleanarchitecture.bdd.steps;

import com.jai.cleanarchitecture.presentation.rest.dto.PersonDTO;
import com.jai.cleanarchitecture.providers.PersonDTOProvider;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


public class PersonStepDefs {

    @LocalServerPort
    private int port;

    WebClient webTestClient;

    Mono<PersonDTO> result;

    @Before
    public void setUp() {
        webTestClient = WebClient.builder().baseUrl("http://localhost:".concat(String.valueOf(port))).build();
        result = Mono.empty();
    }

    @Given("A user publish a new Person named {word}")
    public void aUserPublishANewPerson(String name) {

        Mono<PersonDTO> requestResult = webTestClient.post().uri("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(new PersonDTOProvider().getPersonDTO()))
                .retrieve()
                .bodyToMono(PersonDTO.class);
        StepVerifier.create(requestResult)
                .expectNext(new PersonDTOProvider().getPersonDTO())
                .verifyComplete();
    }

    @When("A user asks for the person with id {word}")
    public void aUserGetPersonWithId(String id) {
        result = webTestClient.get().uri(uriBuilder -> uriBuilder.path("/person/{id}").build(Long.parseLong(id)))
                .retrieve().bodyToMono(PersonDTO.class);
    }

    @Then("The following person should be in the response content")
    public void heShouldHaveASuccessResponse(DataTable table) {
        StepVerifier.create(result)
                .expectNext(new PersonDTOProvider().getPersonDTO())
                .verifyComplete();
    }
}
