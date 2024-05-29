package com.beeapic.beeapicback.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VenteProductNougat {

    @EmbeddedId
    VenteProductNougatKey id;

    @ManyToOne
    @MapsId("venteId")
    @JoinColumn(name = "vente_id")
    private Vente vente;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_nougat_id")
    private ProductNougat product;

    private Long quantity;
}
