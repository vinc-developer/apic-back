package com.beeapic.beeapicback.vente.controller;

import com.beeapic.beeapicback.entity.Vente;
import com.beeapic.beeapicback.vente.dto.VenteAllProductDto;
import com.beeapic.beeapicback.vente.dto.VenteProductMielDto;
import com.beeapic.beeapicback.vente.service.VenteService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vente")
public class VenteController {

    private final VenteService venteService;

    public VenteController(VenteService venteService) {
        this.venteService = venteService;
    }

    /**
     * Api @Post création d'une vente
     * @param listVentes
     * @return Vente
     */
    @PostMapping("/create")
    public Vente createVente(@Valid List<VenteProductMielDto> listVentes) {
        return venteService.createVente(listVentes);
    }

    /**
     * Api @Get de la récupération d'une vente
     * @param id
     * @return VenteAllProductDto
     */
    @GetMapping("/{id}")
    public VenteAllProductDto getVenteAllProduct(@PathVariable Long id){
        return venteService.getVenteAllProduct(id);
    }
}
