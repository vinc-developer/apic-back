package com.beeapic.beeapicback.commerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDto {
    private Long id;
    private String nomProduit;
    private Double prix;
    private Long poids;
    private Long quantiteCreer;
    private Long quantiteVendu;
    private String numeroLot;
    private LocalDate DDM;
    private LocalDate dateConditionnement;
    private Long extractionId;
}
