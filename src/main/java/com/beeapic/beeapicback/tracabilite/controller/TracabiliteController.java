package com.beeapic.beeapicback.tracabilite.controller;

import com.beeapic.beeapicback.tracabilite.dto.TracabiliteDto;
import com.beeapic.beeapicback.tracabilite.service.TracabiliteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/tracabilite")
public class TracabiliteController {

    private final TracabiliteService tracabiliteService;

    public TracabiliteController(TracabiliteService tracabiliteService) {
        this.tracabiliteService = tracabiliteService;
    }

    /**
     * Api @Get qui retourne la tracabilite d'un produit
     * @param id
     * @return TracabiliteDto
     */
    @GetMapping("/{id}")
    public ResponseEntity<TracabiliteDto> getTracabilite(@PathVariable Long id) {
        try {
            TracabiliteDto tracabiliteDto = tracabiliteService.getTracabilites(id);
            return ResponseEntity.ok(tracabiliteDto);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Une erreur c'est produite durant la récupération des données.");
        }
    }
}
