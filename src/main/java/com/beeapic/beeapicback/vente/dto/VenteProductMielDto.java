package com.beeapic.beeapicback.vente.dto;

import com.beeapic.beeapicback.entity.ProductMiel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VenteProductMielDto {
    private ProductMiel product;
    private Long quantity;
}
