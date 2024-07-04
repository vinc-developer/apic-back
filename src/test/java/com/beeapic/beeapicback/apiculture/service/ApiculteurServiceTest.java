package com.beeapic.beeapicback.apiculture.service;

import com.beeapic.beeapicback.apiculture.dto.ApiculteurDto;
import com.beeapic.beeapicback.apiculture.repository.ApiculteurRepository;
import com.beeapic.beeapicback.entity.Apiculteur;
import com.beeapic.beeapicback.jeuxDonnee.JeuxDonneeTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApiculteurServiceTest {

    private JeuxDonneeTest jeuxDonnee = new JeuxDonneeTest();
    private ApiculteurService sut;

    @Mock
    private ApiculteurRepository apiculteurRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        sut = new ApiculteurService(apiculteurRepository);
    }

    @Test
    void createApiculteur() throws Exception {
        //ARRANGE
        Apiculteur apiculteur = jeuxDonnee.getNewApiculteur();
        //MOCK
        when(apiculteurRepository.save(any(Apiculteur.class))).thenReturn(apiculteur);
        //ACT
        Apiculteur result = sut.createApiculteur(apiculteur);
        //ASSERT
        verify(apiculteurRepository, times(1)).save(any(Apiculteur.class));
        assertNotNull(result);
    }

    @Test
    void getApiculteur() {
        //MOCK
        when(apiculteurRepository.findById(1L)).thenReturn(Optional.ofNullable(jeuxDonnee.getNewApiculteur()));
        //ACT
        ApiculteurDto result  = sut.getApiculteur(1L);
        //ASSERT
        assertEquals(result.getNapi(), "AP123456");
    }

    @Test
    void getApiculteurNULL() {
        //MOCK
        when(apiculteurRepository.findById(1L)).thenReturn(null);
        //ACT & ASSERT
        assertThrows(NullPointerException.class, () -> sut.getApiculteur(1L));
    }
}
