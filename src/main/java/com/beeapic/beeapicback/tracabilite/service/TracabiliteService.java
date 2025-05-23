package com.beeapic.beeapicback.tracabilite.service;

import com.beeapic.beeapicback.apiculture.dto.*;
import com.beeapic.beeapicback.apiculture.interfaces.*;
import com.beeapic.beeapicback.commerce.dto.ProductDto;
import com.beeapic.beeapicback.exception.ResourceNotFoundException;
import com.beeapic.beeapicback.tracabilite.dto.TracabiliteDto;
import com.beeapic.beeapicback.commerce.interfaces.ProductInterface;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TracabiliteService {

    private final ProductInterface productInterface;
    private final ApiculteurInterface apiculteurInterface;
    private final RecolteInterface recolteInterface;
    private final RucheInterface rucheInterface;
    private final RucherInterface rucherInterface;
    private final ExtractionInterface extractionInterface;

    public TracabiliteService(ProductInterface productInterface, ApiculteurInterface apiculteurInterface,
                              RecolteInterface recolteInterface, RucheInterface rucheInterface,
                              RucherInterface rucherInterface, ExtractionInterface extractionInterface) {
        this.productInterface = productInterface;
        this.apiculteurInterface = apiculteurInterface;
        this.recolteInterface = recolteInterface;
        this.rucheInterface = rucheInterface;
        this.rucherInterface = rucherInterface;
        this.extractionInterface = extractionInterface;
    }

    /**
     * Retourne la tracabilité d'un produit en faisasnt appel aux interfaces
     *
     * @param id d'un produit
     * @return TracabiliteDto
     */
    public TracabiliteDto getTracabilites(Long id) {
        ProductDto product = new ProductDto();
        ExtractionDto extraction = new ExtractionDto();
        List<RecolteDto> recoltes = new ArrayList<>();
        List<RucheDto> ruches = new ArrayList<>();
        List<RucherDto> ruchers = new ArrayList<>();
        ApiculteurDto apiculteur = new ApiculteurDto();

        try {
            product = productInterface.getProduct(id);
            if (product == null) {
                throw new ResourceNotFoundException("Produit non trouvé !");
            }

            extraction = extractionInterface.getExtraction(product.getExtractionId());

            extraction.getRecoltes().forEach(r -> recoltes.add(recolteInterface.getRecolte(r.getId())));
            recoltes.forEach(r -> ruches.add(rucheInterface.getRuche(r.getRucheId())));
            ruches.forEach(r -> ruchers.add(rucherInterface.getRucher(r.getRucherId())));

            apiculteur = apiculteurInterface.getApiculteur(ruchers.get(0).getApiculteurId());
            if (apiculteur == null) {
                throw new ResourceNotFoundException("Apiculteur non trouvé !");
            }
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
        }

        TracabiliteDto tracabiliteDto = new TracabiliteDto();
        tracabiliteDto.setProduct(product);
        tracabiliteDto.setRecoltes(recoltes);
        tracabiliteDto.setExtraction(extraction);
        tracabiliteDto.setRuches(ruches);
        tracabiliteDto.setRuchers(ruchers);
        tracabiliteDto.setApiculteur(apiculteur);

        return tracabiliteDto;
    }
}
