package com.beeapic.beeapicback.commerce.service;

import com.beeapic.beeapicback.entity.Commande;
import com.beeapic.beeapicback.commerce.dto.CommandeAllProductDto;
import com.beeapic.beeapicback.commerce.dto.CommandeProductDto;
import com.beeapic.beeapicback.commerce.repository.CommandeProductsRepository;
import com.beeapic.beeapicback.commerce.repository.CommandeRepository;
import com.beeapic.beeapicback.entity.CommandeProducts;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class CommandeService {

    private final CommandeRepository commandeRepository;
    private final CommandeProductsRepository commandeProductsRepository;
    private final ProductService productService;

    public CommandeService(CommandeRepository commandeRepository, CommandeProductsRepository commandeProductsRepository, ProductService productService) {
        this.commandeRepository = commandeRepository;
        this.commandeProductsRepository = commandeProductsRepository;
        this.productService = productService;
    }

    /**
     * Création et persistance d'une commande
     *
     * @param listProducts
     * @return Commande
     */
    public Commande createCommande(List<CommandeProducts> listProducts) {
        Commande commande = commandeRepository.save(new Commande());
        LocalDate date = LocalDate.now();
        commande.setDateCommande(date);
        commande.setNumeroCommande("API" + "/" + date.getYear() + date.getMonth() + date.getDayOfMonth());
        commande.setPrixTotal(0.0);

        commandeRepository.save(commande);

        AtomicReference<BigDecimal> totalPrice = new AtomicReference<>(BigDecimal.ZERO);

        listProducts.forEach((CommandeProducts productDto) -> {
            // création de la quantité de produit vendu
            CommandeProducts commandeProduct = new CommandeProducts();
            commandeProduct.setCommande(commande);
            commandeProduct.setProduct(productDto.getProduct());
            commandeProduct.setQuantity(productDto.getQuantity());
            commandeProductsRepository.save(commandeProduct);

            // calcule du prix total de la vente
            BigDecimal prix = BigDecimal.valueOf(productDto.getProduct().getPrix());
            BigDecimal quantite = BigDecimal.valueOf(productDto.getQuantity());
            BigDecimal total = prix.multiply(quantite);
            totalPrice.updateAndGet(v -> v.add(total));
        });

        commande.setPrixTotal(totalPrice.get().doubleValue());
        //commande.setStatusCommande();

        return commandeRepository.save(commande);
    }

    /**
     * Récupere d'une commande et la liste des produits associés
     *
     * @param id
     * @return CommandeAllProductDto
     */
    public CommandeAllProductDto getCommandeAvecProduits(Long id) {
        CommandeAllProductDto commandeAvecProduct = new CommandeAllProductDto();
        Commande commande = commandeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));
        commandeAvecProduct.setCommande(commande);
        List<CommandeProducts> listProduit = commandeProductsRepository.findAllByCommande(commande);
        List<CommandeProductDto> listProduitDto = new ArrayList<>();
        listProduit.forEach(p -> listProduitDto.add(new CommandeProductDto(this.productService.mapperProduct(p.getProduct()), p.getQuantity())));
        commandeAvecProduct.setListProduct(listProduitDto);
        return commandeAvecProduct;
    }
}
