package com.beeapic.beeapicback.apiculture.interfaces;

import com.beeapic.beeapicback.apiculture.dto.RecolteDto;

public interface RecolteInterface {
    /**
     * Interface qui retourne une Recolte
     * @param id
     * @return RecolteDto
     */
    RecolteDto getRecolte(Long id);
}
