package com.beeapic.beeapicback.apiculture.controller;

import com.beeapic.beeapicback.apiculture.service.AdresseService;
import com.beeapic.beeapicback.entity.Adresse;
import com.beeapic.beeapicback.jeuxDonnee.JeuxDonneeTest;
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
class AdresseControllerTest {

    private JeuxDonneeTest jeuxDonnee = new JeuxDonneeTest();

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AdresseService adresseService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createAdresseAPI() throws Exception {
        //ARRANGE
        Adresse address = jeuxDonnee.getNewAdresse();

        //MOCK
        when(adresseService.createAdresse(any(Adresse.class))).thenReturn(address);

        //ACT & ASSERT
        mvc.perform(MockMvcRequestBuilders
                .post("/apiculture/adresse/create")
                .content(objectMapper.writeValueAsString(address))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.ville").value("Nantes"));
    }

    @Test
    void createAdresseAPIKO() throws Exception {
        //ARRANGE
        Adresse address = jeuxDonnee.getNewAdresse();

        //MOCK
        when(adresseService.createAdresse(any(Adresse.class))).thenThrow(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));

        //ACT & ASSERT
        mvc.perform(MockMvcRequestBuilders
                    .post("/apiculture/adresse/create")
                    .content(objectMapper.writeValueAsString(address))
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
}
