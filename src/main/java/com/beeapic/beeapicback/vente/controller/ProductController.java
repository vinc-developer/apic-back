package com.beeapic.beeapicback.vente.controller;

import com.beeapic.beeapicback.entity.ProductMiel;
import com.beeapic.beeapicback.vente.dto.ProductMielDto;
import com.beeapic.beeapicback.vente.service.ProductMielService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vente/produit")
public class ProductController {

    private final ProductMielService productMielService;

    public ProductController(ProductMielService productMielService) {
        this.productMielService = productMielService;
    }

    /**
     * Api @Post Création d'un produit
     * @param product
     * @return Product
     */
    @PostMapping("/create")
    public ProductMiel createProduct(@Valid @RequestBody ProductMiel product){
        return productMielService.createProduct(product);
    }

    /**
     * Api @Get récuperation produit
     * @param id
     * @return ProductDto
     */
    @GetMapping("/{id}")
    public ProductMielDto getProduct(@PathVariable Long id) {
        return productMielService.getProduct(id);
    }
}
