package com.beeapic.beeapicback.apiculture.service;

import com.beeapic.beeapicback.apiculture.repository.AdresseRepository;
import com.beeapic.beeapicback.entity.Adresse;
import org.springframework.stereotype.Service;

@Service
public class AdresseService {

    private final AdresseRepository adresseRepository;

    public AdresseService(AdresseRepository adresseRepository) {
        this.adresseRepository = adresseRepository;
    }

    /**
     * Fonction qui permet de persister une adresse avec appel au repo
     *
     * @param adresse
     * @return Adresse
     */
    public Adresse createAdresse(Adresse adresse) {
        return adresseRepository.save(adresse);
    }
}
