package com.beeapic.beeapicback.vente.service;

import com.beeapic.beeapicback.entity.ProductMiel;
import com.beeapic.beeapicback.entity.Vente;
import com.beeapic.beeapicback.entity.VentesProductMiel;
import com.beeapic.beeapicback.jeuxDonnee.JeuxDonneeTest;
import com.beeapic.beeapicback.vente.dto.VenteAllProductDto;
import com.beeapic.beeapicback.vente.dto.VenteProductMielDto;
import com.beeapic.beeapicback.vente.repository.VenteProductRepository;
import com.beeapic.beeapicback.vente.repository.VenteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VenteServiceTest {
    private JeuxDonneeTest jeuxDonnee = new JeuxDonneeTest();
    private VenteService sut;

    @Mock
    private VenteRepository venteRepository;
    @Mock
    private VenteProductRepository venteProductRepository;
    @Mock
    private ProductMielService productMielService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        sut = new VenteService(venteRepository, venteProductRepository, productMielService);
    }

    @Test
    void createVente() throws Exception {
        // ARRANGE
        List<VenteProductMielDto> listProducts = jeuxDonnee.getListVenteProductMielDto();

        //MOCK
        // Simule la sauvegarde initiale de la vente
        when(venteRepository.save(any(Vente.class))).thenAnswer(invocation -> {
            Vente v = invocation.getArgument(0);
            if (v.getId() == null) {
                v.setId(100L); // Simule l'attribution d'un ID après la première sauvegarde
            }
            return v;
        });
        // Simule la sauvegarde des produits de la vente
        when(venteProductRepository.save(any(VentesProductMiel.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(productMielService.createProduct(any(ProductMiel.class))).thenReturn(null);

        // ACT
        Vente resultVente = sut.createVente(listProducts);

        // ASSERT
        // Vérifie que la méthode save est appelée deux fois
        verify(venteRepository, times(2)).save(any(Vente.class));
        // Vérifie que save est appelé sur venteProductRepository pour chaque produit de la liste
        verify(venteProductRepository, times(listProducts.size())).save(any(VentesProductMiel.class));
        // Vérifie que la méthode createProduct est appelée pour chaque produit
        verify(productMielService, times(listProducts.size())).createProduct(any(ProductMiel.class));
        assertNotNull(resultVente);
        assertEquals(100L, resultVente.getId());
        assertEquals(19.50, resultVente.getTotalprice());
    }

    @Test
    void getVentesAllProduct() throws Exception {
        //ARRANGE
        Long id = 100L;
        List<VentesProductMiel> venteProducts = jeuxDonnee.getListVenteProductMiel();
        Vente vente = new Vente(id, 19.50);
        //MOCK
        when (venteRepository.findById(id)).thenReturn(Optional.of(vente));
        when(venteProductRepository.findAllByVente(vente)).thenReturn(venteProducts);
        //ACT
        VenteAllProductDto venteProduct = sut.getVenteAllProduct(id);

        //ASSERT
        assertNotNull(venteProduct);
        assertEquals(venteProduct.getListProduct().size(), 2);
        assertEquals(venteProduct.getVente().getTotalprice(), 19.50);
    }

    @Test
    void getVentesAllProductNull() {
        //ARRANGE
        Long id = 8L;
        //MOCK
        when (venteRepository.findById(id)).thenReturn(null);
        //ACT & ASSERT
        assertThrows(NullPointerException.class, () -> sut.getVenteAllProduct(id));
    }
}
