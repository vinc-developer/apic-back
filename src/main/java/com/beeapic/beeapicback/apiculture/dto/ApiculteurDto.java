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
public class ApiculteurDto {
    private Long id;
    private String lastname;
    private String firstname;
    private String siret;
    private String napi;
    private String email;
    private String telephone;
    private String password;
    private Adresse adresse;

}
