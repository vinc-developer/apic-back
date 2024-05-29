package com.beeapic.beeapicback.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@DiscriminatorValue("MIEL")
public class ProductMiel extends Product {
    @NotNull
    private Long quantitetotalpot;

    private Long quantitevendupot;
}
