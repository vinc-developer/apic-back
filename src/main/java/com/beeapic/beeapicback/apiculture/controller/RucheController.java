package com.beeapic.beeapicback.apiculture.controller;

import com.beeapic.beeapicback.apiculture.dto.RucheDto;
import com.beeapic.beeapicback.entity.Ruche;
import com.beeapic.beeapicback.apiculture.service.RucheService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/apiculture/ruche")
public class RucheController {

    private final RucheService rucheService;

    @Autowired
    public RucheController(RucheService rucheService) {
        this.rucheService = rucheService;
    }

    /**
     * Api @Post Création d'un ruche
     * @param ruche
     * @return Ruche
     */
    @PostMapping("/create")
    public ResponseEntity<Ruche> createRuche(@Valid @RequestBody Ruche ruche) {
        try {
            Ruche createRuche = rucheService.createRuche(ruche);
            return ResponseEntity.ok(createRuche);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Une erreur c'est produite durant la création des données.");
        }
    }

    /**
     * Api @Get Récupération d'une ruche
     * @param id
     * @return RucheDto
     */
    @GetMapping("/{id}")
    public ResponseEntity<RucheDto> getRuche(@PathVariable Long id) {
        try {
            RucheDto ruche = rucheService.getRuche(id);
            return ResponseEntity.ok(ruche);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Une erreur c'est produite durant la récupération des données.");
        }
    }
}
