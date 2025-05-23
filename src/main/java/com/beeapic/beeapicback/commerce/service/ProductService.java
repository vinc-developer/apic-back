package com.beeapic.beeapicback.commerce.service;

import com.beeapic.beeapicback.commerce.dto.ProductDto;
import com.beeapic.beeapicback.commerce.interfaces.ProductInterface;
import com.beeapic.beeapicback.commerce.repository.ProductRepository;
import com.beeapic.beeapicback.entity.Product;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductService implements ProductInterface {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Persiste un produit
     *
     * @param product
     * @return Product
     */
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    /**
     * Récupere un produit par son id
     *
     * @param id
     * @return ProductDto
     */
    public ProductDto getProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));
        return mapperProduct(product);
    }

    /**
     * Map une entity en dto produit
     *
     * @param product
     * @return ProductDto
     */
    public ProductDto mapperProduct(Product product) {
        if (product == null) return null;
        return new ProductDto(
                product.getId(),
                product.getNomProduit(),
                product.getPrix(),
                product.getPoids(),
                product.getQuantiteCreer(),
                product.getQuantiteVendu(),
                product.getNumeroLot(),
                product.getDDM(),
                product.getDateConditionnement(),
                product.getExtraction().getId()
        );
    }
}
