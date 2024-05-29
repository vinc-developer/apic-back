package com.beeapic.beeapicback.vente.service;

import com.beeapic.beeapicback.entity.Vente;
import com.beeapic.beeapicback.entity.VentesProductMiel;
import com.beeapic.beeapicback.vente.dto.VenteAllProductDto;
import com.beeapic.beeapicback.vente.dto.VenteProductMielDto;
import com.beeapic.beeapicback.vente.repository.VenteProductRepository;
import com.beeapic.beeapicback.vente.repository.VenteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class VenteService {

    private final VenteRepository venteRepository;
    private final VenteProductRepository venteProductRepository;
    private final ProductMielService productMielService;


    public VenteService(VenteRepository venteRepository, VenteProductRepository venteProductRepository, ProductMielService productMielService) {
        this.venteRepository = venteRepository;
        this.venteProductRepository = venteProductRepository;
        this.productMielService = productMielService;
    }

    /**
     * Création et persistance d'une vente
     * @param listProducts
     * @return Vente
     */
    public Vente createVente(List<VenteProductMielDto> listProducts) {
        Vente vente = venteRepository.save(new Vente());
        Double[] totalPrice = {0.0};

        listProducts.forEach((VenteProductMielDto productDto) -> {

            // création de la quantité de produit vendu
            VentesProductMiel ventesProductMiel = new VentesProductMiel();
            ventesProductMiel.setVente(vente);
            ventesProductMiel.setProduct(productDto.getProduct());
            ventesProductMiel.setQuantity(productDto.getQuantity());
            venteProductRepository.save(ventesProductMiel);

            // incrémenter le nombre de produit vendu et save
            productDto.getProduct().setQuantitevendupot(productDto.getProduct().getQuantitevendupot() + productDto.getQuantity());
            productMielService.createProduct(productDto.getProduct());

            // calcule du prix total de la vente
            totalPrice[0] = totalPrice[0] + productDto.getProduct().getPrice() * productDto.getQuantity();
        });

        vente.setTotalprice(totalPrice[0]);

        return venteRepository.save(vente);
    }

    /**
     * Récupere une vente et la liste des produits associés
     * @param id
     * @return VenteAllProductDto
     */
    public VenteAllProductDto getVenteAllProduct(Long id) {
        VenteAllProductDto venteProduct = new VenteAllProductDto();
        Vente vente = venteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        venteProduct.setVente(vente);

        List<VentesProductMiel> venteProducts = venteProductRepository.findAllByVente(vente);
        venteProducts.forEach((VentesProductMiel venteP) -> {
            VenteProductMielDto venteProductMielDto = new VenteProductMielDto();
            venteProductMielDto.setQuantity(venteP.getQuantity());
            venteProductMielDto.setProduct(venteP.getProduct());

            venteProduct.setListProduct(new ArrayList<>());
            venteProduct.getListProduct().add(venteProductMielDto);
        });

        return venteProduct;
    }
}
