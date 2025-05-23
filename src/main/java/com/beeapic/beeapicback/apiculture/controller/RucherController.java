package com.beeapic.beeapicback.apiculture.controller;

import com.beeapic.beeapicback.apiculture.dto.RucherDto;
import com.beeapic.beeapicback.entity.Rucher;
import com.beeapic.beeapicback.apiculture.service.RucherService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/apiculture/rucher")
public class RucherController {

    private final RucherService rucherService;

    public RucherController(RucherService rucherService) {
        this.rucherService = rucherService;
    }

    /**
     * Api @Post Création d'un rucher
     *
     * @param rucher
     * @return Rucher
     */
    @PostMapping("/create")
    public ResponseEntity<Rucher> createRucher(@Valid @RequestBody Rucher rucher) {
        try {
            Rucher rucherCreate = rucherService.createRucher(rucher);
            return ResponseEntity.ok(rucherCreate);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Une erreur c'est produite durant la création des données.");
        }
    }

    /**
     * Api @Get Récupération d'un rucher par son id
     *
     * @param id
     * @return RucherDto
     */
    @GetMapping("/{id}")
    public ResponseEntity<RucherDto> getRucher(@PathVariable Long id) {
        try {
            RucherDto rucher = rucherService.getRucher(id);
            return ResponseEntity.ok(rucher);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Une erreur c'est produite durant la récupération des données.");
        }
    }

    // récupérer tous les rucher d'un apiculteur

    // modifier un rucher

    // supprimer un rucher: qu'en est t'il des ruches ?
}
