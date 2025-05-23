package com.beeapic.beeapicback.apiculture.controller;

import com.beeapic.beeapicback.apiculture.dto.RecolteDto;
import com.beeapic.beeapicback.entity.Recolte;
import com.beeapic.beeapicback.apiculture.service.RecolteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/apiculture/recolte")
public class RecolteController {

    private final RecolteService recolteService;

    public RecolteController(RecolteService recolteService) {
        this.recolteService = recolteService;
    }

    /**
     * Api @Get Création d'une récolte de miel
     *
     * @param recolte
     * @return Recolte
     */
    @PostMapping("/create")
    public ResponseEntity<Recolte> createRecolte(@Valid @RequestBody Recolte recolte) {
        try {
            Recolte recolteCreate = recolteService.createRecolte(recolte);
            return ResponseEntity.ok(recolteCreate);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Une erreur c'est produite durant la création des données.");
        }
    }

    /**
     * Api @Post Récupération d'une récolte de miel
     *
     * @param id
     * @return RecolteDto
     */
    @GetMapping("/{id}")
    public ResponseEntity<RecolteDto> getRecolte(@PathVariable Long id) {
        try {
            RecolteDto recolte = recolteService.getRecolte(id);
            return ResponseEntity.ok(recolte);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Une erreur c'est produite durant la récupération des données.");
        }
    }

    // récupération de toute les récolte d'une ruche

    // récupération de toutes les récoltes d'un rucher

    // récupération de toutes les récoltes d'un apiculteur avec param de date.

    // modifier une récolte

    // supprimer une recolte
}
