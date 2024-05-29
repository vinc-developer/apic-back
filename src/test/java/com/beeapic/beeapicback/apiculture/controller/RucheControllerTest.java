package com.beeapic.beeapicback.apiculture.controller;

import com.beeapic.beeapicback.apiculture.repository.RucheRepository;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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

    @MockBean
    private RucheRepository rucheRepository;

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
        when(rucheRepository.save(any(Ruche.class))).thenReturn(ruche);
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
}
