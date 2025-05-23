package com.beeapic.beeapicback.apiculture.interfaces;

import com.beeapic.beeapicback.apiculture.dto.ExtractionDto;

public interface ExtractionInterface {
    /**
     * Retourne une extraction
     * @param id
     * @return
     */
    ExtractionDto getExtraction(Long id);
}
