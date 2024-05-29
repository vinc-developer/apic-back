package com.beeapic.beeapicback.apiculture.interfaces;

import com.beeapic.beeapicback.apiculture.dto.ApiculteurDto;

public interface ApiculteurInterface {
    /**
     * Interface pour récuperer les informations d'un apiculteur
      * @param id
     * @return ApiculteurDto
     */
    ApiculteurDto getApiculteur(Long id);
}
