package com.beeapic.beeapicback.commerce.controller;

import com.beeapic.beeapicback.jeuxDonnee.JeuxDonneeTest;
import com.beeapic.beeapicback.commerce.dto.CommandeAllProductDto;
import com.beeapic.beeapicback.commerce.dto.CommandeProductDto;
import com.beeapic.beeapicback.commerce.service.CommandeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class CommandeControllerTest {
/*
    private JeuxDonneeTest jeuxDonnee = new JeuxDonneeTest();

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CommandeService commandeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createVenteAPI() throws Exception {
        // ARRANGE
        List<CommandeProductDto> listProduct = jeuxDonnee.getListVenteProductMielDto();
        Vente vente = jeuxDonnee.getNewVente(2L, 19.50);

        String jsonBody = objectMapper.writeValueAsString(listProduct);

        //MOCK
        when(commandeService.createVente(any(List.class))).thenReturn(vente);

        //ACT & ARRANGE
        mvc.perform(MockMvcRequestBuilders
                    .post("/vente/create")
                    .content(jsonBody)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalprice").value(19.50));
    }

    @Test
    void createVenteAPIKO() throws Exception {
        // ARRANGE
        List<CommandeProductDto> listProduct = jeuxDonnee.getListVenteProductMielDto();
        String jsonBody = objectMapper.writeValueAsString(listProduct);

        //MOCK
        when(commandeService.createVente(any(List.class))).thenThrow(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));

        //ACT & ARRANGE
        mvc.perform(MockMvcRequestBuilders
                    .post("/vente/create")
                    .content(jsonBody)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().is5xxServerError())
            .andExpect(result -> assertInstanceOf(ResponseStatusException.class, result.getResolvedException()))
            .andExpect(result -> {
                ResponseStatusException exception = (ResponseStatusException) result.getResolvedException();
                assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, Objects.requireNonNull(exception).getStatusCode());
                assertEquals("Une erreur c'est produite durant la création des données.", exception.getReason());
            });
    }

    @Test
    void getVenteAllProductAPI() throws Exception {
        //ARRANGE
        Long id = 18L;
        CommandeAllProductDto venteProducts = jeuxDonnee.getVenteAllProduct();

        //MOCK
        when(commandeService.getVenteAllProduct(id)).thenReturn(venteProducts);

        //ACT & ASSERT
        mvc.perform(MockMvcRequestBuilders
                .get("/vente/{id}", id)
                .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.vente.totalprice").value(19.50));
    }

    @Test
    void getVenteAllProductAPIKO() throws Exception  {
        //ARRANGE
        Long id = 18L;
        //Mock
        when(commandeService.getVenteAllProduct(id)).thenThrow(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));
        //ACT & ASSERT
        mvc.perform(MockMvcRequestBuilders
                    .get("/vente/{id}", id)
                    .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().is5xxServerError())
            .andExpect(result -> assertInstanceOf(ResponseStatusException.class, result.getResolvedException()))
            .andExpect(result -> {
                ResponseStatusException exception = (ResponseStatusException) result.getResolvedException();
                assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, Objects.requireNonNull(exception).getStatusCode());
                assertEquals("Une erreur c'est produite durant la récupération des données.", exception.getReason());
            });
    }*/
}
