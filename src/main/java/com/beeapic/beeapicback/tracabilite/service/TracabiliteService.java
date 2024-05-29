package com.beeapic.beeapicback.tracabilite.service;

import com.beeapic.beeapicback.apiculture.dto.ApiculteurDto;
import com.beeapic.beeapicback.apiculture.dto.RecolteDto;
import com.beeapic.beeapicback.apiculture.dto.RucheDto;
import com.beeapic.beeapicback.apiculture.dto.RucherDto;
import com.beeapic.beeapicback.apiculture.interfaces.ApiculteurInterface;
import com.beeapic.beeapicback.apiculture.interfaces.RecolteInterface;
import com.beeapic.beeapicback.apiculture.interfaces.RucheInterface;
import com.beeapic.beeapicback.apiculture.interfaces.RucherInterface;
import com.beeapic.beeapicback.exception.ResourceNotFoundException;
import com.beeapic.beeapicback.tracabilite.dto.TracabiliteDto;
import com.beeapic.beeapicback.vente.dto.ProductMielDto;
import com.beeapic.beeapicback.vente.interfaces.ProductInterface;
import org.springframework.stereotype.Service;

@Service
public class TracabiliteService {

    private final ProductInterface productInterface;
    private final ApiculteurInterface apiculteurInterface;
    private final RecolteInterface recolteInterface;
    private final RucheInterface rucheInterface;
    private final RucherInterface rucherInterface;

    public TracabiliteService(ProductInterface productInterface, ApiculteurInterface apiculteurInterface,
                              RecolteInterface recolteInterface, RucheInterface rucheInterface,
                              RucherInterface rucherInterface) {
        this.productInterface = productInterface;
        this.apiculteurInterface = apiculteurInterface;
        this.recolteInterface = recolteInterface;
        this.rucheInterface = rucheInterface;
        this.rucherInterface = rucherInterface;
    }

    /**
     * Retourne la tracabilité d'un produit en faisasnt appel aux interfaces
     * @param id d'un produit
     * @return TracabiliteDto
     */
    public TracabiliteDto getTracabilites(Long id) {
        ProductMielDto product = new ProductMielDto();
        RecolteDto recolte = new RecolteDto();
        RucheDto ruche = new RucheDto();
        RucherDto rucher = new RucherDto();
        ApiculteurDto apiculteur = new ApiculteurDto();

        try {
            product = productInterface.getProduct(id);
            if (product == null) {
                throw new ResourceNotFoundException("Produit non trouvé !");
            }

            recolte = recolteInterface.getRecolte(product.getRecolteId());
            if (recolte == null) {
                throw new ResourceNotFoundException("Recolte non trouvé !");
            }

            ruche = rucheInterface.getRuche(recolte.getRucheId());
            if (ruche == null) {
                throw new ResourceNotFoundException("Ruche non trouvé !");
            }

            rucher = rucherInterface.getRucher(ruche.getRucherId());
            if (rucher == null) {
                throw new ResourceNotFoundException("Rucher non trouvé !");
            }

            apiculteur = apiculteurInterface.getApiculteur(rucher.getApiculteurId());
            if (apiculteur == null) {
                throw new ResourceNotFoundException("Apiculteur non trouvé !");
            }
        } catch (ResourceNotFoundException e) {
            // Gérer d'autres exceptions générales
            e.printStackTrace();
        }

        TracabiliteDto tracabiliteDto = new TracabiliteDto();
        tracabiliteDto.setProduct(product);
        tracabiliteDto.setRecolte(recolte);
        tracabiliteDto.setRuche(ruche);
        tracabiliteDto.setRucher(rucher);
        tracabiliteDto.setApiculteur(apiculteur);

        return tracabiliteDto;
    }
}
