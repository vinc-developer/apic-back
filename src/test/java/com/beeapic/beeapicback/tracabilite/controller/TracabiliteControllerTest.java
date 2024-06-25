package com.beeapic.beeapicback.tracabilite.controller;

import com.beeapic.beeapicback.exception.ResourceNotFoundException;
import com.beeapic.beeapicback.jeuxDonnee.JeuxDonneeTest;
import com.beeapic.beeapicback.tracabilite.service.TracabiliteService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class TracabiliteControllerTest {

    private JeuxDonneeTest jeuxDonnee = new JeuxDonneeTest();

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TracabiliteService tracabiliteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Ce test permet de controller une API ainsi que les resultats retournés par celle-ci
     * @throws Exception
     */
    @Test
    void getTracabiliteAPI() throws Exception {
        //ARRANGE
        Long id = 79L;

        //MOCK
        when(tracabiliteService.getTracabilites(id)).thenReturn(jeuxDonnee.getTracabiliteDto(id));

        //ACT & ASSERT
        mvc.perform(MockMvcRequestBuilders
                        .get("/tracabilite/{id}", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.ruche.name").value("#55"));
    }

    /**
     * Permet de controller que l'api retourne une erreur
     * @throws Exception
     */
    @Test
    void getTracabiliteAPIKO() throws Exception {
        //ARRANGE
        Long id = 79L;
        //MOCK
        when(tracabiliteService.getTracabilites(id)).thenThrow(ResourceNotFoundException.class);

        //ACT & ASSERT
        mvc.perform(MockMvcRequestBuilders
                        .get("/tracabilite/{id}", id)
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
