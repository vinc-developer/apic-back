package com.beeapic.beeapicback.apiculture.service;

import com.beeapic.beeapicback.apiculture.dto.RecolteDto;
import com.beeapic.beeapicback.apiculture.interfaces.RecolteInterface;
import com.beeapic.beeapicback.entity.Recolte;
import com.beeapic.beeapicback.apiculture.repository.RecolteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RecolteService implements RecolteInterface {

    private final RecolteRepository recolteRepository;

    public RecolteService(RecolteRepository recolteRepository) {
        this.recolteRepository = recolteRepository;
    }

    /**
     * Persiste une recolte
     * @param recolte
     * @return Recolte
     */
    public Recolte createRecolte(Recolte recolte) {
        return recolteRepository.save(recolte);
    }

    /**
     * Map et retourne une recolte
     * @param id
     * @return RecolteDto
     */
    public RecolteDto getRecolte(Long id) {
        Recolte recolte = recolteRepository.findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return mapperRecolte(recolte);
    }

    /**
     * mapper pour retourner un dto
     * @param recolte
     * @return RecolteDto
     */
    private RecolteDto mapperRecolte(Recolte recolte) {
        if(recolte == null) return null;
        return new RecolteDto(recolte.getId(), recolte.getName(), recolte.getRecoltedate(), recolte.getRecoltekg(), recolte.getRuche().getId());
    }
}
