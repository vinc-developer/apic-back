package com.beeapic.beeapicback.apiculture.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RecolteDto {
    private Long id;
    private String nomMieler;
    private LocalDate dateRecolte;
    private Long qteRecolte;
    private Long qteHausseRecolte;
    private Long rucheId;
    private Long extractionId;
}
