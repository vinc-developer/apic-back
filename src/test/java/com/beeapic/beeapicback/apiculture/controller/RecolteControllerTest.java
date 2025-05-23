package com.beeapic.beeapicback.apiculture.controller;

import com.beeapic.beeapicback.apiculture.service.RecolteService;
import com.beeapic.beeapicback.entity.Recolte;
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
class RecolteControllerTest {

    private JeuxDonneeTest jeuxDonnee = new JeuxDonneeTest();

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RecolteService recolteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getRecolteAPI() throws Exception {
        //ARRANGE
        Long id = 10L;
        //MOCK
        when(recolteService.getRecolte(id)).thenReturn(jeuxDonnee.getRecolteDto(id,"printemps", LocalDate.of(2024, 6, 27), 25L, 2L, 1L, 2L));
        //ACT & ASSERT
        mvc.perform(MockMvcRequestBuilders
                .get("/apiculture/recolte/{id}", id)
                .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("printemps"));
    }

    @Test
    void getRecolteAPIKO() throws Exception {
        //ARRANGE
        Long id = 10L;
        //MOCK
        when(recolteService.getRecolte(id)).thenThrow(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));
        //ACT & ASSERT
        mvc.perform(MockMvcRequestBuilders
                    .get("/apiculture/recolte/{id}", id)
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

    @Test
    void createRecolteAPI() throws Exception {
        //ARRANGE
        Recolte recolte = jeuxDonnee.getNewRecolte(0L, "printemps", LocalDate.of(2024, 6, 27), 25L, 2L);
        Recolte recolteRetour = recolte;
        recolteRetour.setId(9L);

        String jsonBody = objectMapper.writeValueAsString(recolte);

        //MOCK
        when(recolteService.createRecolte(any(Recolte.class))).thenReturn(recolteRetour);

        //ACT & ASSERT
        mvc.perform(MockMvcRequestBuilders
                    .post("/apiculture/recolte/create")
                    .content(jsonBody)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(9L));
    }

    @Test
    void createRecolteAPIKO() throws Exception {
        //ARRANGE
        Recolte recolte = jeuxDonnee.getNewRecolte(0L, "printemps", LocalDate.of(2024, 6, 27), 25L, 2L);

        String jsonBody = objectMapper.writeValueAsString(recolte);

        //MOCK
        when(recolteService.createRecolte(any(Recolte.class))).thenThrow(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));

        //ACT & ASSERT
        mvc.perform(MockMvcRequestBuilders
                        .post("/apiculture/recolte/create")
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
}
