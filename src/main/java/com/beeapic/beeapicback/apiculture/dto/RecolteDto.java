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
    private String name;
    private LocalDate recoltedate;
    private Long recoltekg;
    private Long rucheId;
}
