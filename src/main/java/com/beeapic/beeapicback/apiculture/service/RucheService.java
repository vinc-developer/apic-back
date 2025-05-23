package com.beeapic.beeapicback.apiculture.service;

import com.beeapic.beeapicback.apiculture.dto.RucheDto;
import com.beeapic.beeapicback.apiculture.interfaces.RucheInterface;
import com.beeapic.beeapicback.entity.Ruche;
import com.beeapic.beeapicback.apiculture.repository.RucheRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RucheService implements RucheInterface {

    private final RucheRepository rucheRepository;

    public RucheService(RucheRepository rucheRepository) {
        this.rucheRepository = rucheRepository;
    }

    /**
     * persiste une ruche
     *
     * @param ruche
     * @return Ruche
     */
    public Ruche createRuche(Ruche ruche) {
        return rucheRepository.save(ruche);
    }

    /**
     * récupere et retourner un objet ruche mapper
     *
     * @param id
     * @return RucheDto
     */
    public RucheDto getRuche(Long id) {
        Ruche ruche = rucheRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return mapperRuche(ruche);
    }

    /**
     * mapper pour retourner un objet unique
     *
     * @param ruche
     * @return RucheDto
     */
    private RucheDto mapperRuche(Ruche ruche) {
        if (ruche == null) return null;
        return new RucheDto(ruche.getId(), ruche.getName(), ruche.getTyperuche(), ruche.getBeetype(), ruche.getRucher().getId());
    }
}
