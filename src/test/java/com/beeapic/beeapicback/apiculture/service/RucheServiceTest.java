package com.beeapic.beeapicback.apiculture.service;

import com.beeapic.beeapicback.apiculture.dto.RucheDto;
import com.beeapic.beeapicback.apiculture.repository.RucheRepository;
import com.beeapic.beeapicback.entity.Ruche;
import com.beeapic.beeapicback.jeuxDonnee.JeuxDonneeTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RucheServiceTest {

    private JeuxDonneeTest jeuxDonnee = new JeuxDonneeTest();
    private RucheService sut;

    @Mock
    private RucheRepository rucheRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        sut = new RucheService(rucheRepository);
    }

    @Test
    void createRuche() throws ParseException {
        //ARRANGE
        Ruche ruche = jeuxDonnee.getNewRuche(4L, "#4", "noire", "dadant");
        //MOCK
        when(rucheRepository.save(any(Ruche.class))).thenReturn(ruche);
        //ACT
        Ruche result = sut.createRuche(ruche);
        //ASSERT
        verify(rucheRepository, times(1)).save(any(Ruche.class));
        assertNotNull(result);
    }

    @Test
    void getRuche() throws ParseException {
        //MOCK
        when(rucheRepository.findById(4L)).thenReturn(Optional.ofNullable(jeuxDonnee.getNewRuche(4L, "#4", "noire", "dadant")));
        //ACT
        RucheDto result = sut.getRuche(4L);
        //ASSERT
        assertEquals(result.getName(), "#4");
    }

    @Test
    void getRucheNull(){
        //MOCK
        when(rucheRepository.findById(4L)).thenReturn(null);
        //ACT & ASSERT
        assertThrows(NullPointerException.class, () -> sut.getRuche(4L));
    }
}
