package com.beeapic.beeapicback.apiculture.service;

import com.beeapic.beeapicback.apiculture.dto.RecolteDto;
import com.beeapic.beeapicback.apiculture.repository.RecolteRepository;
import com.beeapic.beeapicback.entity.Recolte;
import com.beeapic.beeapicback.jeuxDonnee.JeuxDonneeTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecolteServiceTest {

    private JeuxDonneeTest jeuxDonnee = new JeuxDonneeTest();
    private RecolteService sut;

    @Mock
    private RecolteRepository recolteRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        sut = new RecolteService(recolteRepository);
    }

    @Test
    void createRecolte() throws ParseException {
        //ARRANGE
        Recolte recolte = jeuxDonnee.getNewRecolte(9L,"été", LocalDate.of(2024, 6, 27),40L, 2L);
        //MOCK
        when(recolteRepository.save(any(Recolte.class))).thenReturn(recolte);
        //ACT
        Recolte result = sut.createRecolte(recolte);
        //ASSERT
        verify(recolteRepository, times(1)).save(any(Recolte.class));
        assertNotNull(result);
    }

    @Test
    void getRecolte() throws ParseException {
        //MOCK
        when(recolteRepository.findById(9L)).thenReturn(Optional.of(jeuxDonnee.getNewRecolte(9L,"été", LocalDate.of(2024, 6, 27),40L, 2L)));
        //ACT
        RecolteDto result = sut.getRecolte(9L);
        //ASSERT
        assertEquals(result.getNomMieler(), "été");
    }

    @Test
    void getRecolteNull(){
        //MOCK
        when(recolteRepository.findById(9L)).thenReturn(null);
        //ACT & ASSERT
        assertThrows(NullPointerException.class, () -> sut.getRecolte(9L));
    }
}
