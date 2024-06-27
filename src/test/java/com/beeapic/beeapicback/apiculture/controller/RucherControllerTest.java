package com.beeapic.beeapicback.apiculture.controller;

import com.beeapic.beeapicback.apiculture.dto.RucherDto;
import com.beeapic.beeapicback.apiculture.service.RucherService;
import com.beeapic.beeapicback.entity.Rucher;
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
class RucherControllerTest {

    private JeuxDonneeTest jeuxDonnee = new JeuxDonneeTest();

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RucherService rucherService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createRucherAPI() throws Exception {
        //ARRANGE
        Rucher rucher = jeuxDonnee.getNewRucher(0L, "petit bois", "foret");
        Rucher rucherRetour = rucher;
        rucherRetour.setId(1L);
        String jsonBody = objectMapper.writeValueAsString(rucher);

        //Mock
        when(rucherService.createRucher(any(Rucher.class))).thenReturn(rucherRetour);

        //ACT & ASSERT
        mvc.perform(MockMvcRequestBuilders
                .post("/apiculture/rucher/create")
                .content(jsonBody)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L));
    }

    @Test
    void createRucherAPIKO() throws Exception {
        //ARRANGE
        Rucher rucher = jeuxDonnee.getNewRucher(0L, "petit bois", "foret");
        String jsonBody = objectMapper.writeValueAsString(rucher);

        //Mock
        when(rucherService.createRucher(any(Rucher.class))).thenThrow(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));

        //ACT & ASSERT
        mvc.perform(MockMvcRequestBuilders
                    .post("/apiculture/rucher/create")
                    .content(jsonBody)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON))
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
    void getRucherAPI() throws Exception {
        //ARRANGE
        Long id = 5L;
        //MOCK
        when(rucherService.getRucher(id)).thenReturn(jeuxDonnee.getRucherDto(id, "petit bois", "foret", 1L));
        //ACT & ASSERT
        mvc.perform(MockMvcRequestBuilders
                .get("/apiculture/rucher/{id}", id)
                .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.environnement").value("foret"));
    }

    @Test
    void getRucherAPIKO() throws Exception {
        //ARRANGE
        Long id = 5L;
        //MOCK
        when(rucherService.getRucher(id)).thenThrow(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));
        //ACT & ASSERT
        mvc.perform(MockMvcRequestBuilders
                    .get("/apiculture/rucher/{id}", id)
                    .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(result -> assertInstanceOf(ResponseStatusException.class, result.getResolvedException()))
            .andExpect(result -> {
                ResponseStatusException exception = (ResponseStatusException) result.getResolvedException();
                assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, Objects.requireNonNull(exception).getStatusCode());
                assertEquals("Une erreur c'est produite durant la récupération des données.", exception.getReason());
            });
    }  ;
}
