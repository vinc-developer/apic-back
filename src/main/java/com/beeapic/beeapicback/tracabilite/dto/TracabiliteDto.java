package com.beeapic.beeapicback.tracabilite.dto;

import com.beeapic.beeapicback.apiculture.dto.ApiculteurDto;
import com.beeapic.beeapicback.apiculture.dto.RecolteDto;
import com.beeapic.beeapicback.apiculture.dto.RucheDto;
import com.beeapic.beeapicback.apiculture.dto.RucherDto;
import com.beeapic.beeapicback.vente.dto.ProductMielDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TracabiliteDto {
    private ProductMielDto product;
    private RecolteDto recolte;
    private RucheDto ruche;
    private RucherDto rucher;
    private ApiculteurDto apiculteur;
}
