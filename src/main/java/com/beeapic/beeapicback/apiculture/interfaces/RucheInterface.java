package com.beeapic.beeapicback.apiculture.interfaces;

import com.beeapic.beeapicback.apiculture.dto.RucheDto;

public interface RucheInterface {
    /**
     * Interface qui permet de retourner une ruche
     * @param id
     * @return RucheDto
     */
    RucheDto getRuche(Long id);
}
