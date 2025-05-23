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
public class CommandeProductsKey implements Serializable {

    @Column(name = "commande_id")
    Long commandeId;

    @Column(name = "product_id")
    Long productId;
}
