package com.beeapic.beeapicback.vente.service;

import com.beeapic.beeapicback.entity.ProductMiel;
import com.beeapic.beeapicback.vente.dto.ProductMielDto;
import com.beeapic.beeapicback.vente.interfaces.ProductInterface;
import com.beeapic.beeapicback.vente.repository.ProductMielRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductMielService implements ProductInterface {

    private final ProductMielRepository productMielRepository;

    public ProductMielService(ProductMielRepository productMielRepository) {
        this.productMielRepository = productMielRepository;
    }

    /**
     * Persiste un produit
     * @param product
     * @return Product
     */
    public ProductMiel createProduct(ProductMiel product) {
        return productMielRepository.save(product);
    }

    /**
     * Récupere un produit par son id
     * @param id
     * @return ProductDto
     */
    public ProductMielDto getProduct(Long id) {
        ProductMiel product = productMielRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));
        return mapperProduct(product);
    }

    /**
     * Map une entity en dto produit
     * @param product
     * @return ProductDto
     */
    private ProductMielDto mapperProduct(ProductMiel product) {
        if(product == null) return null;
        return new ProductMielDto(product.getId(), product.getPrice(), product.getPoids(), product.getQuantitetotalpot(), product.getQuantitevendupot(), product.getRecolte().getId());
    }
}
