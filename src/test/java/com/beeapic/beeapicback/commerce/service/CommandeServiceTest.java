package com.beeapic.beeapicback.commerce.service;

import com.beeapic.beeapicback.jeuxDonnee.JeuxDonneeTest;
import com.beeapic.beeapicback.commerce.dto.CommandeAllProductDto;
import com.beeapic.beeapicback.commerce.dto.CommandeProductDto;
import com.beeapic.beeapicback.commerce.repository.CommandeProductsRepository;
import com.beeapic.beeapicback.commerce.repository.CommandeRepository;
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
class CommandeServiceTest {
   /* private JeuxDonneeTest jeuxDonnee = new JeuxDonneeTest();
    private CommandeService sut;

    @Mock
    private CommandeRepository commandeRepository;
    @Mock
    private CommandeProductsRepository commandeProductsRepository;
    @Mock
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        sut = new CommandeService(commandeRepository, commandeProductsRepository, productService);
    }

    @Test
    void createVente() throws Exception {
        // ARRANGE
        List<CommandeProductDto> listProducts = jeuxDonnee.getListVenteProductMielDto();

        //MOCK
        // Simule la sauvegarde initiale de la vente
        when(commandeRepository.save(any(Vente.class))).thenAnswer(invocation -> {
            Vente v = invocation.getArgument(0);
            if (v.getId() == null) {
                v.setId(100L); // Simule l'attribution d'un ID après la première sauvegarde
            }
            return v;
        });
        // Simule la sauvegarde des produits de la vente
        when(commandeProductsRepository.save(any(VentesProductMiel.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(productService.createProduct(any(ProductMiel.class))).thenReturn(null);

        // ACT
        Vente resultVente = sut.createVente(listProducts);

        // ASSERT
        // Vérifie que la méthode save est appelée deux fois
        verify(commandeRepository, times(2)).save(any(Vente.class));
        // Vérifie que save est appelé sur venteProductRepository pour chaque produit de la liste
        verify(commandeProductsRepository, times(listProducts.size())).save(any(VentesProductMiel.class));
        // Vérifie que la méthode createProduct est appelée pour chaque produit
        verify(productService, times(listProducts.size())).createProduct(any(ProductMiel.class));
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
        when (commandeRepository.findById(id)).thenReturn(Optional.of(vente));
        when(commandeProductsRepository.findAllByVente(vente)).thenReturn(venteProducts);
        //ACT
        CommandeAllProductDto venteProduct = sut.getVenteAllProduct(id);

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
        when (commandeRepository.findById(id)).thenReturn(null);
        //ACT & ASSERT
        assertThrows(NullPointerException.class, () -> sut.getVenteAllProduct(id));
    }*/
}
