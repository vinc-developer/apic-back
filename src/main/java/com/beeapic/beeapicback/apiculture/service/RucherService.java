package com.beeapic.beeapicback.apiculture.service;

import com.beeapic.beeapicback.apiculture.dto.RucherDto;
import com.beeapic.beeapicback.apiculture.interfaces.RucherInterface;
import com.beeapic.beeapicback.entity.Rucher;
import com.beeapic.beeapicback.apiculture.repository.RucherRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RucherService implements RucherInterface {

    private final RucherRepository rucherRepository;

    public RucherService(RucherRepository rucherRepository) {
        this.rucherRepository = rucherRepository;
    }

    /**
     * persiste un rucher
     * @param rucher
     * @return Rucher
     */
    public Rucher createRucher(Rucher rucher) {
        return rucherRepository.save(rucher);
    }

    /**
     * Recupere et map un rucher
     * @param id
     * @return RucherDto
     */
    public RucherDto getRucher(Long id) {
        Rucher rucher = rucherRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return mapperRucher(rucher);
    }

    /**
     * permet de modifier un objet en dto
     * @param rucher
     * @return RucherDto
     */
    private RucherDto mapperRucher(Rucher rucher) {
        if(rucher == null) return null;
        return new RucherDto(rucher.getId(), rucher.getName(), rucher.getEnvironnement(), rucher.getAdresse(), rucher.getApiculteur().getId());
    }
}
