package com.beeapic.beeapicback.apiculture.dto;

import com.beeapic.beeapicback.entity.Recolte;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ExtractionDto {
    private Long id;
    private LocalDate dateExtraction;
    private String nomMieler;
    private Long qteExtraite;
    private Long qteHausse;
    private List<Recolte> recoltes;
}
