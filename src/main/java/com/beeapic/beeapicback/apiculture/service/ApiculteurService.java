package com.beeapic.beeapicback.apiculture.service;

import com.beeapic.beeapicback.apiculture.dto.ApiculteurDto;
import com.beeapic.beeapicback.apiculture.interfaces.ApiculteurInterface;
import com.beeapic.beeapicback.entity.Apiculteur;
import com.beeapic.beeapicback.apiculture.repository.ApiculteurRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ApiculteurService implements ApiculteurInterface {

    private final ApiculteurRepository apiculteurRepository;

    public ApiculteurService(ApiculteurRepository apiculteurRepository) {
        this.apiculteurRepository = apiculteurRepository;
    }

    /**
     * Map et retourne un apiculteur
     *
     * @param id
     * @return ApiculteurDto
     */
    public ApiculteurDto getApiculteur(Long id) {
        Apiculteur apiculteur = apiculteurRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return mapperApiculteur(apiculteur);
    }

    /**
     * Persiste un apiculteur avec le repo
     *
     * @param apiculteur
     * @return Apiculteur
     */
    public Apiculteur createApiculteur(Apiculteur apiculteur) {
        return apiculteurRepository.save(apiculteur);
    }

    /**
     * Mapper pour retourner un objet DTO
     *
     * @param apiculteur
     * @return ApiculteurDto
     */
    private ApiculteurDto mapperApiculteur(Apiculteur apiculteur) {
        if (apiculteur == null) return null;
        return new ApiculteurDto(apiculteur.getId(), apiculteur.getLastname(), apiculteur.getFirstname(),
                apiculteur.getSiret(), apiculteur.getNapi(), apiculteur.getEmail(), apiculteur.getTelephone(), apiculteur.getAdresse());
    }
}
