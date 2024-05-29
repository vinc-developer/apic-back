package com.beeapic.beeapicback.apiculture.interfaces;

import com.beeapic.beeapicback.apiculture.dto.RucherDto;

public interface RucherInterface {
    /**
     * Interface qui retourne un Rucher
     * @param id
     * @return RucherDto
     */
    RucherDto getRucher(Long id);
}
