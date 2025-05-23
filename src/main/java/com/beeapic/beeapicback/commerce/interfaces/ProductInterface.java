package com.beeapic.beeapicback.commerce.interfaces;

import com.beeapic.beeapicback.commerce.dto.ProductDto;

public interface ProductInterface {
    /**
     * Interface d'appel pour récupération d'un produit
     * @param id
     * @return ProductDto
     */
    ProductDto getProduct(Long id);
}
