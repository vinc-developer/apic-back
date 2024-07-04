package com.beeapic.beeapicback.apiculture.service;

import com.beeapic.beeapicback.apiculture.repository.AdresseRepository;
import com.beeapic.beeapicback.entity.Adresse;
import com.beeapic.beeapicback.jeuxDonnee.JeuxDonneeTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdresseServiceTest {

    private JeuxDonneeTest jeuxDonnee = new JeuxDonneeTest();
    private AdresseService sut;

    @Mock
    private AdresseRepository adresseRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        sut = new AdresseService(adresseRepository);
    }

    @Test
    void createAdresse() {
        //ARRANGE
        Adresse address = jeuxDonnee.getNewAdresse();
        //MOCK
        when(adresseRepository.save(any(Adresse.class))).thenReturn(address);
        //ACT
        Adresse addressCreated = sut.createAdresse(address);
        //ASSERT
        verify(adresseRepository, times(1)).save(any(Adresse.class));
        assertNotNull(addressCreated);
    }
}
