package com.beeapic.beeapicback.vente.controller;

import com.beeapic.beeapicback.entity.ProductMiel;
import com.beeapic.beeapicback.jeuxDonnee.JeuxDonneeTest;
import com.beeapic.beeapicback.vente.service.ProductMielService;
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
class ProductControllerTest {

    private JeuxDonneeTest jeuxDonnee = new JeuxDonneeTest();

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductMielService productMielService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createProductAPI() throws Exception {
        //ARRANGE
        ProductMiel productMiel = jeuxDonnee.getNewProduct(0L,4.50, 250L, 1500L, 550L);

        ProductMiel productMielRetour = productMiel;
        productMielRetour.setId(55L);

        String jsonBody = objectMapper.writeValueAsString(productMiel);

        // MOCK
        when(productMielService.createProduct(any(ProductMiel.class))).thenReturn(productMielRetour);

        //ACT & ASSERT
        mvc.perform(MockMvcRequestBuilders
                    .post("/vente/produit/create-miel")
                    .content(jsonBody)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(55));
    }

    @Test
    void createProductAPIKO() throws Exception {
        //ARRANGE
        ProductMiel productMiel = jeuxDonnee.getNewProduct(0L,4.50, 250L, 1500L, 550L);

        String jsonBody = objectMapper.writeValueAsString(productMiel);

        // MOCK
        when(productMielService.createProduct(any(ProductMiel.class))).thenThrow(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));

        mvc.perform(MockMvcRequestBuilders
                .post("/vente/produit/create-miel")
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().is5xxServerError())
            .andExpect(result -> assertInstanceOf(ResponseStatusException.class, result.getResolvedException()))
            .andExpect(result -> {
                ResponseStatusException exception = (ResponseStatusException) result.getResolvedException();
                assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, Objects.requireNonNull(exception).getStatusCode());
                assertEquals("Une erreur c'est produite durant la création des données.", exception.getReason());
            });
    }

    @Test
    void getProductMielAPI() throws Exception  {
        //ARRANGE
        Long id = 55L;
        //MOCK
        when(productMielService.getProduct(id)).thenReturn(jeuxDonnee.getProductMiel(id, 4.50, 250L, 1500L, 550L, 9L));
        //ACT & ASSERT
        mvc.perform(MockMvcRequestBuilders
                    .get("/vente/produit/{id}", id)
                    .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantitetotalpot").value(1500));
    }

    @Test
    void getProductMielAPIKO() throws Exception  {
        //ARRANGE
        Long id = 55L;
        //MOCK
        when(productMielService.getProduct(id)).thenThrow(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));
        //ACT & ASSERT
        mvc.perform(MockMvcRequestBuilders
                .get("/vente/produit/{id}", id)
                .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().is5xxServerError())
            .andExpect(result -> assertInstanceOf(ResponseStatusException.class, result.getResolvedException()))
            .andExpect(result -> {
                ResponseStatusException exception = (ResponseStatusException) result.getResolvedException();
                assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, Objects.requireNonNull(exception).getStatusCode());
                assertEquals("Une erreur c'est produite durant la récupération des données.", exception.getReason());
            });
    }
}
