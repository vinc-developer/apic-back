package com.beeapic.beeapicback.apiculture.dto;

import com.beeapic.beeapicback.entity.Adresse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RucherDto {
    private Long id;
    private String name;
    private String environnement;
    private Adresse adresse;
    private Long apiculteurId;
}
