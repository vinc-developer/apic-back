package com.beeapic.beeapicback.vente.controller;

import com.beeapic.beeapicback.entity.ProductMiel;
import com.beeapic.beeapicback.vente.dto.ProductMielDto;
import com.beeapic.beeapicback.vente.service.ProductMielService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    @PostMapping("/create-miel")
    public ResponseEntity<ProductMiel> createProduct(@Valid @RequestBody ProductMiel product) {
        try {
            ProductMiel productMiel = productMielService.createProduct(product);
            return ResponseEntity.ok(productMiel);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Une erreur c'est produite durant la création des données.");
        }
    }

    /**
     * Api @Get récuperation produit
     * @param id
     * @return ProductDto
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductMielDto> getProductMiel(@PathVariable Long id) {
        try {
            ProductMielDto product = productMielService.getProduct(id);
            return ResponseEntity.ok(product);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Une erreur c'est produite durant la récupération des données.");
        }
    }
}
