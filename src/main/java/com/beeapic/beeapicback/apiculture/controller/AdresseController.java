package com.beeapic.beeapicback.apiculture.controller;

import com.beeapic.beeapicback.apiculture.service.AdresseService;
import com.beeapic.beeapicback.entity.Adresse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/apiculture/adresse")
public class AdresseController {

    private final AdresseService adresseService;

    public AdresseController(AdresseService adresseService) {
        this.adresseService = adresseService;
    }

    /**
     * Api @Post Création d'un adresse
     * @param adresse
     * @return Adresse
     */
    @PostMapping("/create")
    public ResponseEntity<Adresse> createAdresse(@Valid @RequestBody Adresse adresse){
        try {
            Adresse address = adresseService.createAdresse(adresse);
            return ResponseEntity.ok(address);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Une erreur c'est produite durant la création des données.");
        }
    }
}
