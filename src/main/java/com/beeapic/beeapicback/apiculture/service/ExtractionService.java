package com.beeapic.beeapicback.apiculture.service;

import com.beeapic.beeapicback.apiculture.dto.ExtractionDto;
import com.beeapic.beeapicback.apiculture.interfaces.ExtractionInterface;
import com.beeapic.beeapicback.apiculture.repository.ExtractionRepository;
import com.beeapic.beeapicback.entity.Extraction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ExtractionService implements ExtractionInterface {

    private final ExtractionRepository extractionRepository;

    public ExtractionService(ExtractionRepository extractionRepository) {
        this.extractionRepository = extractionRepository;
    }

    /**
     * @param extraction
     * @return ExtractionDto
     */
    public Extraction createExtraction(Extraction extraction) {
        return extractionRepository.save(extraction);
    }

    /**
     * @param id
     * @return ExtractionDto
     */
    public ExtractionDto getExtraction(Long id) {
        Extraction extraction = extractionRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return mapperExtraction(extraction);
    }

    /**
     * @param extraction
     * @return
     */
    private ExtractionDto mapperExtraction(Extraction extraction) {
        if (extraction == null) return null;
        return new ExtractionDto(
                extraction.getId(),
                extraction.getDateExtraction(),
                extraction.getNomMieler(),
                extraction.getQteExtraite(),
                extraction.getQteHausse(),
                extraction.getRecoltes()
        );
    }
}
