package com.beeapic.beeapicback.commerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommandeProductDto {
    private ProductDto product;
    private Long quantity;
}
