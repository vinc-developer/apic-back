package com.beeapic.beeapicback.commerce.controller;

import com.beeapic.beeapicback.commerce.dto.ProductDto;
import com.beeapic.beeapicback.commerce.service.ProductService;
import com.beeapic.beeapicback.entity.Product;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/produit")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Api @Post Création d'un produit
     *
     * @param product
     * @return Product
     */
    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        try {
            Product productMiel = productService.createProduct(product);
            return ResponseEntity.ok(productMiel);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Une erreur c'est produite durant la création des données.");
        }
    }

    /**
     * Api @Get récuperation produit
     *
     * @param id
     * @return ProductDto
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductMiel(@PathVariable Long id) {
        try {
            ProductDto product = productService.getProduct(id);
            return ResponseEntity.ok(product);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Une erreur c'est produite durant la récupération des données.");
        }
    }

    // modifier un produit

    // supprimer un produit si pas de vente

    // récupérer tout les produits d'une récolte

    // récuperer tout les produits d'un apiculteur avec parametre de nom de recolte et date

    // récuperer tout les produit d'un rucher avec parametre de nom de recolte et date
}
