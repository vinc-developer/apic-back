package com.beeapic.beeapicback.vente.dto;

import com.beeapic.beeapicback.entity.Vente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VenteAllProductDto {
    private Vente vente;
    private List<VenteProductMielDto> listProduct;
}
