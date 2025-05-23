package com.beeapic.beeapicback.tracabilite.dto;

import com.beeapic.beeapicback.apiculture.dto.*;
import com.beeapic.beeapicback.commerce.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TracabiliteDto {
    private ProductDto product;
    private ExtractionDto extraction;
    private List<RecolteDto> recoltes;
    private List<RucheDto> ruches;
    private List<RucherDto> ruchers;
    private ApiculteurDto apiculteur;
}
