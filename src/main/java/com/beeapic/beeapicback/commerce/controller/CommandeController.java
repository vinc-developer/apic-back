package com.beeapic.beeapicback.commerce.controller;

import com.beeapic.beeapicback.entity.Commande;
import com.beeapic.beeapicback.commerce.dto.CommandeAllProductDto;
import com.beeapic.beeapicback.commerce.dto.CommandeProductDto;
import com.beeapic.beeapicback.commerce.service.CommandeService;
import com.beeapic.beeapicback.entity.CommandeProducts;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/commande")
public class CommandeController {

    private final CommandeService commandeService;

    public CommandeController(CommandeService commandeService) {
        this.commandeService = commandeService;
    }

    /**
     * Api @Post création d'une vente
     *
     * @param listProduitVendu
     * @return Commande
     */
    @PostMapping("/create")
    public ResponseEntity<Commande> createCommande(@Valid @RequestBody List<CommandeProducts> listProduitVendu) {
        try {
            Commande commande = commandeService.createCommande(listProduitVendu);
            return ResponseEntity.ok(commande);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Une erreur c'est produite durant la création des données.");
        }
    }

    /**
     * Api @Get de la récupération d'une vente
     *
     * @param id
     * @return CommandeAllProductDto
     */
    @GetMapping("/{id}")
    public ResponseEntity<CommandeAllProductDto> getVenteAllProduct(@PathVariable Long id) {
        try {
            CommandeAllProductDto commandes = commandeService.getCommandeAvecProduits(id);
            return ResponseEntity.ok(commandes);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Une erreur c'est produite durant la récupération des données.");
        }
    }

    // récupérer toutes les ventes d'un apiculteur, parametre de date

    // modifier une vente

    // supprimer une vente
}
