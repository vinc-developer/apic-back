package com.beeapic.beeapicback.apiculture.controller;

import com.beeapic.beeapicback.apiculture.dto.RecolteDto;
import com.beeapic.beeapicback.entity.Recolte;
import com.beeapic.beeapicback.apiculture.service.RecolteService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apiculture/recolte")
public class RecolteController {

    private final RecolteService recolteService;

    public RecolteController(RecolteService recolteService) {
        this.recolteService = recolteService;
    }

    /**
     * Api @Get Création d'une récolte de miel
     * @param recolte
     * @return Recolte
     */
    @PostMapping("/create")
    public Recolte createRecolte(@Valid @RequestBody Recolte recolte) {
        return recolteService.createRecolte(recolte);
    }

    /**
     * Api @Post Récupération d'une récolte de miel
     * @param id
     * @return RecolteDto
     */
    @GetMapping("/{id}")
    public RecolteDto getRecolte(@PathVariable Long id) {
        return recolteService.getRecolte(id);
    }
}
