package com.beeapic.beeapicback.apiculture.service;

import com.beeapic.beeapicback.apiculture.dto.RucherDto;
import com.beeapic.beeapicback.apiculture.repository.RucherRepository;
import com.beeapic.beeapicback.entity.Rucher;
import com.beeapic.beeapicback.jeuxDonnee.JeuxDonneeTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RucherServiceTest {

    private JeuxDonneeTest jeuxDonnee = new JeuxDonneeTest();

    private RucherService sut;

    @Mock
    RucherRepository rucherRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        sut = new RucherService(rucherRepository);
    }

    @Test
    void createRucher() {
        //ARRANGE
        Rucher rucher = jeuxDonnee.getNewRucher(5L, "Petit bois", "foret");
        //MOCK
        when(rucherRepository.save(any(Rucher.class))).thenReturn(rucher);
        //ACT
        Rucher result = sut.createRucher(rucher);
        //ASSERT
        verify(rucherRepository, times(1)).save(any(Rucher.class));
        assertNotNull(result);
    }

    @Test
    void getRucher() {
        //MOCK
        when(rucherRepository.findById(5L)).thenReturn(Optional.ofNullable(jeuxDonnee.getNewRucher(5L, "petit bois", "foret")));
        //ACT
        RucherDto result = sut.getRucher(5L);
        //ASSERT
        assertEquals(result.getEnvironnement(), "foret");
    }

    @Test
    void getRucherNull() {
        //MOCK
        when(rucherRepository.findById(5L)).thenReturn(null);
        //ACT & ASSERT
        assertThrows(NullPointerException.class, () -> sut.getRucher(5L));
    }
}
