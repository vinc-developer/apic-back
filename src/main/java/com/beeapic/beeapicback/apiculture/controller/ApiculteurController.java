package com.beeapic.beeapicback.apiculture.controller;

import com.beeapic.beeapicback.apiculture.dto.ApiculteurDto;
import com.beeapic.beeapicback.entity.Apiculteur;
import com.beeapic.beeapicback.apiculture.service.ApiculteurService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/apiculture/apiculteur")
public class ApiculteurController {

    private final ApiculteurService apiculteurService;

    public ApiculteurController(ApiculteurService apiculteurService) {
        this.apiculteurService = apiculteurService;
    }

    /**
     * Api @Get Recupère un apiculteur par son id
     * @param id
     * @return ApiculteurDto
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiculteurDto> getApiculteur(@PathVariable Long id) {
        try {
            ApiculteurDto api = apiculteurService.getApiculteur(id);
            return ResponseEntity.ok(api);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Une erreur c'est produite durant la récupération des données.");
        }
    }

    /**
     * Api @Post Création d'un apiculteur
     * @param apiculteur
     * @return Apiculteur
     */
    @PostMapping("/create")
    public ResponseEntity<Apiculteur> createApiculteur(@Valid @RequestBody Apiculteur apiculteur) {
        try {
            Apiculteur apiculteurCreate = apiculteurService.createApiculteur(apiculteur);
            return ResponseEntity.ok(apiculteurCreate);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Une erreur c'est produite durant la création des données.");
        }
    }
}
