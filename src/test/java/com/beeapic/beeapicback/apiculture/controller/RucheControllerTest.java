package com.beeapic.beeapicback.apiculture.controller;

import com.beeapic.beeapicback.apiculture.service.RucheService;
import com.beeapic.beeapicback.entity.Ruche;
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
class RucheControllerTest {

    private JeuxDonneeTest jeuxDonnee = new JeuxDonneeTest();

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RucheService rucheService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Methode qui permet de controller l'API d'un POST et du retour attendu
     * @throws Exception
     */
    @Test
    void createRucheAPI() throws Exception {
        //ARRANGE
        Ruche ruche = jeuxDonnee.getNewRuche(66L, "#66", "Buck", "Dadant");

        //MOCK
        when(rucheService.createRuche(any(Ruche.class))).thenReturn(ruche);

        //ACT & ASSERT
        mvc.perform(MockMvcRequestBuilders
                .post("/apiculture/ruche/create")
                .content(new ObjectMapper().writeValueAsString(ruche))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.beetype").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.beetype").value("Buck"));
    }

    @Test
    void createRucheAPIKO() throws Exception {
        //ARRANGE
        Ruche ruche = jeuxDonnee.getNewRuche(66L, "#66", "Buck", "Dadant");

        //MOCK
        when(rucheService.createRuche(any(Ruche.class))).thenThrow(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));

        //ACT & ASSERT
        mvc.perform(MockMvcRequestBuilders
                    .post("/apiculture/ruche/create")
                    .content(new ObjectMapper().writeValueAsString(ruche))
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
    void getRucheAPI() throws Exception {
        //ARRANGE
        Long id = 2L;

        //MOCK
        when(rucheService.getRuche(id)).thenReturn(jeuxDonnee.getRucheDto(id, "#1", "noir", "dadant", 3L));

        //ACT & ASSERT
        mvc.perform(MockMvcRequestBuilders
                .get("/apiculture/ruche/{id}", id)
                .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("#1"));
    }

    @Test
    void getRucheAPIKO() throws Exception {
        //ARRANGE
        Long id = 2L;

        //MOCK
        when(rucheService.getRuche(id)).thenThrow(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));

        //ACT & ASSERT
        mvc.perform(MockMvcRequestBuilders
                    .get("/apiculture/ruche/{id}", id)
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
