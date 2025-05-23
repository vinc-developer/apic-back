package com.beeapic.beeapicback.commerce.dto;

import com.beeapic.beeapicback.entity.Commande;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommandeAllProductDto {
    private Commande commande;
    private List<CommandeProductDto> listProduct;
}
