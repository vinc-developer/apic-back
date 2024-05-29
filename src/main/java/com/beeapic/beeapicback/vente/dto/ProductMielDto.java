package com.beeapic.beeapicback.vente.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductMielDto {
    private Long id;
    private Double price;
    private Long poids;
    private Long quantitetotalpot;
    private Long quantitevendupot;
    private Long recolteId;
}
