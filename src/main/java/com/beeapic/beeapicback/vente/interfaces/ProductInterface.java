package com.beeapic.beeapicback.vente.interfaces;

import com.beeapic.beeapicback.vente.dto.ProductMielDto;

public interface ProductInterface {
    /**
     * Interface d'appel pour récupération d'un produit
     * @param id
     * @return ProductDto
     */
    ProductMielDto getProduct(Long id);
}
