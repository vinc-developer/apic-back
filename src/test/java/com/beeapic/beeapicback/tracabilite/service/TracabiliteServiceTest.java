package com.beeapic.beeapicback.tracabilite.service;

import com.beeapic.beeapicback.apiculture.repository.*;
import com.beeapic.beeapicback.apiculture.service.*;
import com.beeapic.beeapicback.entity.*;
import com.beeapic.beeapicback.jeuxDonnee.JeuxDonneeTest;
import com.beeapic.beeapicback.tracabilite.dto.TracabiliteDto;
import com.beeapic.beeapicback.commerce.repository.ProductRepository;
import com.beeapic.beeapicback.commerce.service.ProductService;
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
    private ProductRepository productRepository;
    @Mock
    private ApiculteurRepository apiculteurRepository;
    @Mock
    private RecolteRepository recolteRepository;
    @Mock
    private RucheRepository rucheRepository;
    @Mock
    private RucherRepository rucherRepository;
    @Mock
    private ExtractionRepository extractionRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialise les mocks
        sut = new TracabiliteService(
                new ProductService(productRepository),
                new ApiculteurService(apiculteurRepository),
                new RecolteService(recolteRepository),
                new RucheService(rucheRepository),
                new RucherService(rucherRepository),
                new ExtractionService(extractionRepository));
    }

    private JeuxDonneeTest jeuxDonnee = new JeuxDonneeTest();

    @Test
    void getTracabiliteService() throws ParseException {
        //ARRANGE
        Long id = 79L;
        Product product = jeuxDonnee.getNewProduct(79L, 7.50, 500L, 1000L, 780L, "20270525", LocalDate.of(2027,05,25), LocalDate.of(2025,05,25));
        Extraction extraction = jeuxDonnee.getNewExtraction(5L, LocalDate.of(2025,05,25), "printemps 2025", 500L, 20L);
        Recolte recolte = jeuxDonnee.getNewRecolte(9L, "Printemps", LocalDate.of(2023, 5, 20), 500L, 20L);
        Ruche ruche = jeuxDonnee.getNewRuche(55L, "#55", "Noir", "Dadant");
        Rucher rucher = jeuxDonnee.getNewRucher(3L, "Rucher du petit bois", "Bois");

        //MOCK
        when(productRepository.findById(id)).thenReturn(Optional.ofNullable(product));
        when(extractionRepository.findById(product.getExtraction().getId())).thenReturn(Optional.ofNullable(extraction));
        when(recolteRepository.findById(extraction.getRecoltes().get(0).getId())).thenReturn(Optional.ofNullable(recolte));
        when(rucheRepository.findById(recolte.getRuche().getId())).thenReturn(Optional.ofNullable(ruche));
        when(rucherRepository.findById(ruche.getRucher().getId())).thenReturn(Optional.ofNullable(rucher));
        when(apiculteurRepository.findById(rucher.getApiculteur().getId())).thenReturn(Optional.ofNullable(jeuxDonnee.getNewApiculteur()));

        //ACT
        TracabiliteDto result =  sut.getTracabilites(id);

        //ASSERT
        assertEquals(result.getProduct().getPoids(), 500L);
        assertEquals(result.getRecoltes().size(), 1);
        assertEquals(result.getRuches().size(), 1);
        assertEquals(result.getRuchers().size(), 1L);
        assertNotNull(result.getApiculteur().getAdresse());
    }

    @Test
    void getTracabiliteServiceNullProduct() {
        //ARRANGE
        Long id = 79L;

        //Mock
        when(productRepository.findById(id)).thenReturn(null);

        //ACT & ASSERT
        assertThrows(NullPointerException.class,
                () -> sut.getTracabilites(id)
        );
    }

    @Test
    void getTracabiliteServiceNullRecolte() throws ParseException {
        //ARRANGE
        Long id = 79L;
        Product product = jeuxDonnee.getNewProduct(79L, 7.50, 500L, 1000L, 780L, "20270525", LocalDate.of(2027,05,25), LocalDate.of(2025,05,25));
        Extraction extraction = jeuxDonnee.getNewExtraction(5L, LocalDate.of(2025,05,25), "printemps 2025", 500L, 20L);
        //Mock
        when(productRepository.findById(id)).thenReturn(Optional.ofNullable(product));
        when(extractionRepository.findById(product.getExtraction().getId())).thenReturn(Optional.ofNullable(extraction));
        when(recolteRepository.findById(extraction.getRecoltes().get(0).getId())).thenReturn(null);

        //ACT & ASSERT
        assertThrows(NullPointerException.class,
                () -> sut.getTracabilites(id)
        );
    }

    @Test
    void getTracabiliteServiceNullRuche() throws ParseException {
        //ARRANGE
        Long id = 79L;
        Product product = jeuxDonnee.getNewProduct(79L, 7.50, 500L, 1000L, 780L, "20270525", LocalDate.of(2027,05,25), LocalDate.of(2025,05,25));
        Extraction extraction = jeuxDonnee.getNewExtraction(5L, LocalDate.of(2025,05,25), "printemps 2025", 500L, 20L);
        Recolte recolte = jeuxDonnee.getNewRecolte(9L, "Printemps", LocalDate.of(2023, 5, 20), 500L, 20L);

        //Mock
        when(productRepository.findById(id)).thenReturn(Optional.ofNullable(product));
        when(extractionRepository.findById(product.getExtraction().getId())).thenReturn(Optional.ofNullable(extraction));
        when(recolteRepository.findById(extraction.getRecoltes().get(0).getId())).thenReturn(Optional.ofNullable(recolte));
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
        Product product = jeuxDonnee.getNewProduct(79L, 7.50, 500L, 1000L, 780L, "20270525", LocalDate.of(2027,05,25), LocalDate.of(2025,05,25));
        Extraction extraction = jeuxDonnee.getNewExtraction(5L, LocalDate.of(2025,05,25), "printemps 2025", 500L, 20L);
        Recolte recolte = jeuxDonnee.getNewRecolte(9L, "Printemps", LocalDate.of(2023, 5, 20), 500L, 20L);
        Ruche ruche = jeuxDonnee.getNewRuche(55L, "#55", "Noir", "Dadant");

        //Mock
        when(productRepository.findById(id)).thenReturn(Optional.ofNullable(product));
        when(extractionRepository.findById(product.getExtraction().getId())).thenReturn(Optional.ofNullable(extraction));
        when(recolteRepository.findById(extraction.getRecoltes().get(0).getId())).thenReturn(Optional.ofNullable(recolte));
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
        Product product = jeuxDonnee.getNewProduct(79L, 7.50, 500L, 1000L, 780L, "20270525", LocalDate.of(2027,05,25), LocalDate.of(2025,05,25));
        Extraction extraction = jeuxDonnee.getNewExtraction(5L, LocalDate.of(2025,05,25), "printemps 2025", 500L, 20L);
        Recolte recolte = jeuxDonnee.getNewRecolte(9L, "Printemps", LocalDate.of(2023, 5, 20), 500L, 20L);
        Ruche ruche = jeuxDonnee.getNewRuche(55L, "#55", "Noir", "Dadant");
        Rucher rucher = jeuxDonnee.getNewRucher(3L, "Rucher du petit bois", "Bois");

        //Mock
        when(productRepository.findById(id)).thenReturn(Optional.ofNullable(product));
        when(extractionRepository.findById(product.getExtraction().getId())).thenReturn(Optional.ofNullable(extraction));
        when(recolteRepository.findById(extraction.getRecoltes().get(0).getId())).thenReturn(Optional.ofNullable(recolte));
        when(rucheRepository.findById(recolte.getRuche().getId())).thenReturn(Optional.ofNullable(ruche));
        when(rucherRepository.findById(ruche.getRucher().getId())).thenReturn(Optional.ofNullable(rucher));
        when(apiculteurRepository.findById(rucher.getApiculteur().getId())).thenReturn(null);

        //ACT & ASSERT
        assertThrows(NullPointerException.class,
                () -> sut.getTracabilites(id)
        );
    }
}
