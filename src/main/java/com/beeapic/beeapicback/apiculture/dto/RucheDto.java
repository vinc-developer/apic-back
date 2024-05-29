package com.beeapic.beeapicback.apiculture.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RucheDto {
    private Long id;
    private String name;
    private String typeruche;
    private String beetype;
    private Long rucherId;
}
