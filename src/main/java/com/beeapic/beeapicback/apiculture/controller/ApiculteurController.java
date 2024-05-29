package com.beeapic.beeapicback.apiculture.controller;

import com.beeapic.beeapicback.apiculture.dto.ApiculteurDto;
import com.beeapic.beeapicback.entity.Apiculteur;
import com.beeapic.beeapicback.apiculture.service.ApiculteurService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

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
    public ApiculteurDto getApiculteur(@PathVariable Long id) {
        return apiculteurService.getApiculteur(id);
    }

    /**
     * Api @Post Création d'un apiculteur
     * @param apiculteur
     * @return Apiculteur
     */
    @PostMapping("/create")
    public Apiculteur createApiculteur(@Valid @RequestBody Apiculteur apiculteur) {
        return apiculteurService.createApiculteur(apiculteur);
    }
}
