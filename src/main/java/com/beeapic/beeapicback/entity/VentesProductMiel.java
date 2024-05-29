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
public class VentesProductMiel {

    @EmbeddedId
    VentesProductMielKey id;

    @ManyToOne
    @MapsId("venteId")
    @JoinColumn(name = "vente_id")
    private Vente vente;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_miel_id")
    private ProductMiel product;

    private Long quantity;
}
