package com.beeapic.beeapicback.commerce.service;

import com.beeapic.beeapicback.jeuxDonnee.JeuxDonneeTest;
import com.beeapic.beeapicback.commerce.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    private JeuxDonneeTest jeuxDonnee = new JeuxDonneeTest();
    private ProductService sut;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        sut = new ProductService(productRepository);
    }

   /* @Test
    void createProduct() throws ParseException {
        //ARRANGE
        ProductMiel product = jeuxDonnee.getNewProduct(2l, 7.50, 500L, 1000L, 0L);
        //MOCK
        when(productRepository.save(product)).thenReturn(product);
        //ACT
        ProductMiel result = sut.createProduct(product);
        //ASSERT
        assertEquals(result.getPrice(), 7.50);
    }

    @Test
    void getProduct() throws ParseException {
        //ARRANGE
        Long id = 5L;
        ProductMiel product = jeuxDonnee.getNewProduct(id, 7.50, 500L, 1000L, 0L);
        //MOCK
        when(productRepository.findById(id)).thenReturn(Optional.ofNullable(product));
        //ACT
        ProductMielDto productMapper = sut.getProduct(id);
        //ASSERT
        assertEquals(productMapper.getRecolteId(), 9L);
        assertEquals(productMapper.getPrice(), 7.50);
        assertEquals(productMapper.getId(), id);
    }

    @Test
    void getProductNULL() {
        //ARRANGE
        Long id = 5L;
        //MOCK
        when(productRepository.findById(id)).thenReturn(null);
        //ACT & ASSERT
        assertThrows(NullPointerException.class, () -> sut.getProduct(id));
    }*/
}
