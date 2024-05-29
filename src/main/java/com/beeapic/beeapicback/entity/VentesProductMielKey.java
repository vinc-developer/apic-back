package com.beeapic.beeapicback.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VentesProductMielKey implements Serializable {

    @Column(name = "vente_id")
    Long venteId;

    @Column(name = "product_miel_id")
    Long productId;
}
