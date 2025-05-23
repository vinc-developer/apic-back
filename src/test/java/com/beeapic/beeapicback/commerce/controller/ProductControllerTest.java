package com.beeapic.beeapicback.commerce.controller;

import com.beeapic.beeapicback.entity.Product;
import com.beeapic.beeapicback.jeuxDonnee.JeuxDonneeTest;
import com.beeapic.beeapicback.commerce.service.ProductService;
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

import java.time.LocalDate;
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
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createProductAPI() throws Exception {
        //ARRANGE
        Product productMiel = jeuxDonnee.getNewProduct(0L, 4.50, 250L, 1500L, 550L, "05052025", LocalDate.of(2024, 5, 20), LocalDate.of(2024, 5, 20));

        Product productMielRetour = productMiel;
        productMielRetour.setId(55L);

        String jsonBody = objectMapper.writeValueAsString(productMiel);

        // MOCK
        when(productService.createProduct(any(Product.class))).thenReturn(productMielRetour);

        //ACT & ASSERT
        mvc.perform(MockMvcRequestBuilders
                        .post("/produit/create-miel")
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
        Product productMiel = jeuxDonnee.getNewProduct(0L, 4.50, 250L, 1500L, 550L, "05052025", LocalDate.of(2024, 5, 20), LocalDate.of(2024, 5, 20));

        String jsonBody = objectMapper.writeValueAsString(productMiel);

        // MOCK
        when(productService.createProduct(any(Product.class))).thenThrow(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));

        mvc.perform(MockMvcRequestBuilders
                        .post("/produit/create-miel")
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
    void getProductMielAPI() throws Exception {
        //ARRANGE
        Long id = 55L;
        //MOCK
        when(productService.getProduct(id)).thenReturn(jeuxDonnee.getProductMiel(id, "miel printemps 2025", 5.40, 250L, 200L, 0L, "25052025", LocalDate.of(2027, 05, 25),LocalDate.of(2025, 05, 25), 1L));
        //ACT & ASSERT
        mvc.perform(MockMvcRequestBuilders
                        .get("/produit/{id}", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantitetotalpot").value(1500));
    }

    @Test
    void getProductMielAPIKO() throws Exception {
        //ARRANGE
        Long id = 55L;
        //MOCK
        when(productService.getProduct(id)).thenThrow(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));
        //ACT & ASSERT
        mvc.perform(MockMvcRequestBuilders
                        .get("/produit/{id}", id)
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
