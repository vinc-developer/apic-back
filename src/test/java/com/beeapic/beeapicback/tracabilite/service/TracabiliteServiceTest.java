package com.beeapic.beeapicback.tracabilite.service;

import com.beeapic.beeapicback.apiculture.repository.ApiculteurRepository;
import com.beeapic.beeapicback.apiculture.repository.RecolteRepository;
import com.beeapic.beeapicback.apiculture.repository.RucheRepository;
import com.beeapic.beeapicback.apiculture.repository.RucherRepository;
import com.beeapic.beeapicback.apiculture.service.ApiculteurService;
import com.beeapic.beeapicback.apiculture.service.RecolteService;
import com.beeapic.beeapicback.apiculture.service.RucheService;
import com.beeapic.beeapicback.apiculture.service.RucherService;
import com.beeapic.beeapicback.entity.*;
import com.beeapic.beeapicback.jeuxDonnee.JeuxDonneeTest;
import com.beeapic.beeapicback.tracabilite.dto.TracabiliteDto;
import com.beeapic.beeapicback.vente.repository.ProductMielRepository;
import com.beeapic.beeapicback.vente.service.ProductMielService;
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
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class TracabiliteServiceTest {

    private TracabiliteService sut;

    @Mock
    private ProductMielRepository productMielRepository;
    @Mock
    private ApiculteurRepository apiculteurRepository;
    @Mock
    private RecolteRepository recolteRepository;
    @Mock
    private RucheRepository rucheRepository;
    @Mock
    private RucherRepository rucherRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialise les mocks
        sut = new TracabiliteService(
                new ProductMielService(productMielRepository),
                new ApiculteurService(apiculteurRepository),
                new RecolteService(recolteRepository),
                new RucheService(rucheRepository),
                new RucherService(rucherRepository));
    }

    private JeuxDonneeTest jeuxDonnee = new JeuxDonneeTest();

    @Test
    void getTracabiliteService() throws ParseException {
        //ARRANGE
        Long id = 79L;
        ProductMiel product = jeuxDonnee.getNewProduct(79L, 7.50, 500L, 1000L, 780L);
        Recolte recolte = jeuxDonnee.getNewRecolte(9L, "Printemps", LocalDate.of(2023, 5, 20), 500L);
        Ruche ruche = jeuxDonnee.getNewRuche(55L, "#55", "Noir", "Dadant");
        Rucher rucher = jeuxDonnee.getNewRucher(3L, "Rucher du petit bois", "Bois");

        //MOCK
        when(productMielRepository.findById(id)).thenReturn(Optional.ofNullable(product));
        when(recolteRepository.findById(product.getRecolte().getId())).thenReturn(Optional.ofNullable(recolte));
        when(rucheRepository.findById(recolte.getRuche().getId())).thenReturn(Optional.ofNullable(ruche));
        when(rucherRepository.findById(ruche.getRucher().getId())).thenReturn(Optional.ofNullable(rucher));
        when(apiculteurRepository.findById(rucher.getApiculteur().getId())).thenReturn(Optional.ofNullable(jeuxDonnee.getNewApiculteur()));

        //ACT
        TracabiliteDto result =  sut.getTracabilites(id);

        //ASSERT
        assertEquals(result.getProduct().getPoids(), 500L);
        assertEquals(result.getRecolte().getName(), "Printemps");
        assertEquals(result.getRuche().getBeetype(), "Noir");
        assertEquals(result.getRucher().getApiculteurId(), 1L);
        assertNotNull(result.getApiculteur().getAdresse());
    }

    @Test
    void getTracabiliteServiceNullProduct() {
        //ARRANGE
        Long id = 79L;

        //Mock
        when(productMielRepository.findById(id)).thenReturn(null);

        //ACT & ASSERT
        assertThrows(NullPointerException.class,
                () -> sut.getTracabilites(id)
        );
    }

    @Test
    void getTracabiliteServiceNullRecolte() throws ParseException {
        //ARRANGE
        Long id = 79L;
        ProductMiel product = jeuxDonnee.getNewProduct(79L, 7.50, 500L, 1000L, 780L);

        //Mock
        when(productMielRepository.findById(id)).thenReturn(Optional.ofNullable(product));
        when(recolteRepository.findById(product.getRecolte().getId())).thenReturn(null);

        //ACT & ASSERT
        assertThrows(NullPointerException.class,
                () -> sut.getTracabilites(id)
        );
    }

    @Test
    void getTracabiliteServiceNullRuche() throws ParseException {
        //ARRANGE
        Long id = 79L;
        ProductMiel product = jeuxDonnee.getNewProduct(79L, 7.50, 500L, 1000L, 780L);
        Recolte recolte = jeuxDonnee.getNewRecolte(9L, "Printemps", LocalDate.of(2023, 5, 20), 500L);

        //Mock
        when(productMielRepository.findById(id)).thenReturn(Optional.ofNullable(product));
        when(recolteRepository.findById(product.getRecolte().getId())).thenReturn(Optional.ofNullable(recolte));
        when(rucheRepository.findById(recolte.getRuche().getId())).thenReturn(null);

        //ACT & ASSERT
        assertThrows(NullPointerException.class,
                () -> sut.getTracabilites(id)
        );
    }

    @Test
    void getTracabiliteServiceNullRucher() throws ParseException {
        //ARRANGE
        Long id = 79L;
        ProductMiel product = jeuxDonnee.getNewProduct(79L, 7.50, 500L, 1000L, 780L);
        Recolte recolte = jeuxDonnee.getNewRecolte(9L, "Printemps", LocalDate.of(2023, 5, 20), 500L);
        Ruche ruche = jeuxDonnee.getNewRuche(55L, "#55", "Noir", "Dadant");

        //Mock
        when(productMielRepository.findById(id)).thenReturn(Optional.ofNullable(product));
        when(recolteRepository.findById(product.getRecolte().getId())).thenReturn(Optional.ofNullable(recolte));
        when(rucheRepository.findById(recolte.getRuche().getId())).thenReturn(Optional.ofNullable(ruche));
        when(rucherRepository.findById(ruche.getRucher().getId())).thenReturn(null);

        //ACT & ASSERT
        assertThrows(NullPointerException.class,
                () -> sut.getTracabilites(id)
        );
    }

    @Test
    void getTracabiliteServiceNullApiculteur() throws ParseException {
        //ARRANGE
        Long id = 79L;
        ProductMiel product = jeuxDonnee.getNewProduct(79L, 7.50, 500L, 1000L, 780L);
        Recolte recolte = jeuxDonnee.getNewRecolte(9L, "Printemps", LocalDate.of(2023, 5, 20), 500L);
        Ruche ruche = jeuxDonnee.getNewRuche(55L, "#55", "Noir", "Dadant");
        Rucher rucher = jeuxDonnee.getNewRucher(3L, "Rucher du petit bois", "Bois");

        //Mock
        when(productMielRepository.findById(id)).thenReturn(Optional.ofNullable(product));
        when(recolteRepository.findById(product.getRecolte().getId())).thenReturn(Optional.ofNullable(recolte));
        when(rucheRepository.findById(recolte.getRuche().getId())).thenReturn(Optional.ofNullable(ruche));
        when(rucherRepository.findById(ruche.getRucher().getId())).thenReturn(Optional.ofNullable(rucher));
        when(apiculteurRepository.findById(rucher.getApiculteur().getId())).thenReturn(null);

        //ACT & ASSERT
        assertThrows(NullPointerException.class,
                () -> sut.getTracabilites(id)
        );
    }
}
