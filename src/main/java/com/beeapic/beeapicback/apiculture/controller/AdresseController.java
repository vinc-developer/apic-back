package com.beeapic.beeapicback.apiculture.controller;

import com.beeapic.beeapicback.apiculture.service.AdresseService;
import com.beeapic.beeapicback.entity.Adresse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Adresse createAdresse(@Valid @RequestBody Adresse adresse){
        return adresseService.createAdresse(adresse);
    }
}
