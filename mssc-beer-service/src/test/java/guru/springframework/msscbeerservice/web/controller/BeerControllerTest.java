package guru.springframework.msscbeerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(BeerController.class)
class BeerControllerTest {

    // the mockMvc allows us to execute our controller methods in the same fashion that
    // they would get REST requests
    @Autowired
    MockMvc mockMvc;

    // bring in the objectMapper from SpringBoot which gives us access to Jackson which provides
    // JSON marshall operations
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getBeerById() throws Exception {
        // send a Get to the Mock specifying a UUID to verify that we get back a response. Not verifying the payload
        mockMvc.perform(get("/api/v1/beer/" + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void saveNewBeer() throws Exception {
        // create a BeerDto object and then use the objectMapper to convert it to a JSON document
        BeerDto beerDto = BeerDto.builder().build();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        // send a Post passing the BeerDto as JSON in the body and verify that the controller
        // replies with the CREATED status
        mockMvc.perform(post("/api/v1/beer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isCreated());

    }

    @Test
    void updateBeerById() throws Exception {
        // create a BeerDto object and then use the objectMapper to convert it to a JSON document
        BeerDto beerDto = BeerDto.builder().build();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        // send a Put with the UUID of the beer along with the updated BeerDto in the body
        // confirm that the controller replies with NO_CONTENT note we always need to use
        // .content(Obj) to actually put an object in the body
        mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect((status().isNoContent()));
    }
}