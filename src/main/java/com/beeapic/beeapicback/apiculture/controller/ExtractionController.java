package com.beeapic.beeapicback.apiculture.controller;

import com.beeapic.beeapicback.apiculture.dto.ExtractionDto;
import com.beeapic.beeapicback.apiculture.service.ExtractionService;
import com.beeapic.beeapicback.entity.Extraction;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/apiculture/extraction")
public class ExtractionController {

    private final ExtractionService extractionService;

    public ExtractionController(ExtractionService extractionService) {
        this.extractionService = extractionService;
    }

    /**
     * Api @Post Création d'une extraction de miel
     *
     * @param extraction
     * @return Extraction
     */
    @PostMapping("/create")
    public ResponseEntity<Extraction> createExtraction(@Valid @RequestBody Extraction extraction) {
        try {
            return ResponseEntity.ok(extractionService.createExtraction(extraction));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Une erreur c'est produite durant la création des données.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExtractionDto> getExtraction(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(extractionService.getExtraction(id));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Une erreur c'est produite durant la récupération des données.");
        }
    }

}
