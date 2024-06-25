package com.beeapic.beeapicback.vente.controller;

import com.beeapic.beeapicback.entity.Vente;
import com.beeapic.beeapicback.vente.dto.VenteAllProductDto;
import com.beeapic.beeapicback.vente.dto.VenteProductMielDto;
import com.beeapic.beeapicback.vente.service.VenteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/vente")
public class VenteController {

    private final VenteService venteService;

    public VenteController(VenteService venteService) {
        this.venteService = venteService;
    }

    /**
     * Api @Post création d'une vente
     * @param listVentes
     * @return Vente
     */
    @PostMapping("/create")
    public ResponseEntity<Vente> createVente(@Valid List<VenteProductMielDto> listVentes) {
        try {
            Vente vente = venteService.createVente(listVentes);
            return ResponseEntity.ok(vente);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Une erreur c'est produite durant la création des données.");
        }
    }

    /**
     * Api @Get de la récupération d'une vente
     * @param id
     * @return VenteAllProductDto
     */
    @GetMapping("/{id}")
    public ResponseEntity<VenteAllProductDto> getVenteAllProduct(@PathVariable Long id) {
        try {
            VenteAllProductDto ventes = venteService.getVenteAllProduct(id);
            return ResponseEntity.ok(ventes);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Une erreur c'est produite durant la récupération des données.");
        }
    }
}
