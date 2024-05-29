package com.beeapic.beeapicback.apiculture.controller;

import com.beeapic.beeapicback.apiculture.dto.RucherDto;
import com.beeapic.beeapicback.entity.Rucher;
import com.beeapic.beeapicback.apiculture.service.RucherService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apiculture/rucher")
public class RucherController {

    private final RucherService rucherService;

    public RucherController(RucherService rucherService) {
        this.rucherService = rucherService;
    }

    /**
     * Api @Post Création d'un rucher
     * @param rucher
     * @return Rucher
     */
    @PostMapping("/create")
    public Rucher createRucher(@Valid @RequestBody Rucher rucher) {
        return rucherService.createRucher(rucher);
    }

    /**
     * Api @Get Récupération d'un rucher par son id
     * @param id
     * @return RucherDto
     */
    @GetMapping("/{id}")
    public RucherDto getRucher(@PathVariable Long id) {
        return rucherService.getRucher(id);
    }
}
