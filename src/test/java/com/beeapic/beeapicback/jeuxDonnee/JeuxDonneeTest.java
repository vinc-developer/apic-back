package com.beeapic.beeapicback.jeuxDonnee;

import com.beeapic.beeapicback.apiculture.dto.ApiculteurDto;
import com.beeapic.beeapicback.apiculture.dto.RecolteDto;
import com.beeapic.beeapicback.apiculture.dto.RucheDto;
import com.beeapic.beeapicback.apiculture.dto.RucherDto;
import com.beeapic.beeapicback.commerce.dto.ProductDto;
import com.beeapic.beeapicback.entity.*;
import com.beeapic.beeapicback.tracabilite.dto.TracabiliteDto;
import com.beeapic.beeapicback.commerce.dto.CommandeAllProductDto;
import com.beeapic.beeapicback.commerce.dto.CommandeProductDto;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * CREATION DES OBJETS POUR LES TESTS UNITAIRES
 */
public class JeuxDonneeTest {

    public Ruche getNewRuche(Long id, String name, String beeType, String typeRuche) throws ParseException {
        Ruche newRuche = new Ruche();
        newRuche.setId(id);
        newRuche.setName(name);
        newRuche.setBeetype(beeType);
        newRuche.setTyperuche(typeRuche);
        newRuche.setRucher(getNewRucher(3L, "Rucher du petit bois", "Bois"));
        newRuche.setRecoltes(getListRecoltes());
        return newRuche;
    }

    public Rucher getNewRucher(Long id, String name, String environnement) throws ParseException {
        Rucher newRucher = new Rucher();
        newRucher.setId(id);
        newRucher.setName(name);
        newRucher.setEnvironnement(environnement);
        newRucher.setApiculteur(getNewApiculteur());
        newRucher.setAdresse(getNewAdresse());
        newRucher.setRuches(getListRuches());
        return newRucher;
    }

    public Recolte getNewRecolte(Long id, String name, LocalDate date, Long quantity, Long nbHausses) throws ParseException {
        Recolte newRecolte = new Recolte();
        newRecolte.setId(id);
        newRecolte.setNomMieler(name);
        newRecolte.setDateRecolte(date);
        newRecolte.setQteRecolte(quantity);
        newRecolte.setQteHausseRecolte(nbHausses);
        newRecolte.setRuche(getNewRuche(55L, "#55", "Noir", "Dadant"));
        newRecolte.setExtraction(getNewExtraction(1L, LocalDate.of(2024, 5, 20), "printemps", 100L, 6L));
        return newRecolte;
    }

    public Apiculteur getNewApiculteur() throws ParseException {
        Apiculteur newApiculteur = new Apiculteur();
        newApiculteur.setId(1L);
        newApiculteur.setFirstname("Alain");
        newApiculteur.setLastname("Terrieur");
        newApiculteur.setAdresse(getNewAdresse());
        newApiculteur.setSiret("123456a456");
        newApiculteur.setNapi("AP123456");
        newApiculteur.setEmail("test@test.com");
        newApiculteur.setTelephone("0606060606");
        newApiculteur.setRuchers(getListRuchers());
        return newApiculteur;
    }

    public Adresse getNewAdresse() {
        Adresse adresse = new Adresse();
        adresse.setId(65L);
        adresse.setRue("rue Victor Hugo");
        adresse.setCodepostal("44000");
        adresse.setVille("Nantes");
        adresse.setRegion("Bretagne");
        adresse.setPays("France");
        return adresse;
    }

    public List<Recolte> getListRecoltes() throws ParseException {
        List<Recolte> recolteList = new ArrayList<>();
        recolteList.add(getNewRecolte(9L, "Printemps", LocalDate.of(2023, 5, 20), 500L, 33L));
        recolteList.add(getNewRecolte(2L, "Ete", LocalDate.of(2023, 6, 20), 800L, 53L));
        recolteList.add(getNewRecolte(5L, "Sarrasin", LocalDate.of(2023, 7, 20), 200L, 13L));
        return recolteList;
    }

    public List<Ruche> getListRuches() throws ParseException {
        List<Ruche> rucheList = new ArrayList<>();
        rucheList.add(getNewRuche(55L, "#55", "Noir", "Dadant"));
        rucheList.add(getNewRuche(2L, "#2", "Buck", "Dadant"));
        rucheList.add(getNewRuche(15L, "#15", "Caucasienne", "Dadant"));
        return rucheList;
    }

    public List<Rucher> getListRuchers() throws ParseException {
        List<Rucher> rucherList = new ArrayList<>();
        rucherList.add(getNewRucher(3L, "Rucher du petit bois", "Bois"));
        rucherList.add(getNewRucher(1L, "Rucher Marais", "Marais"));
        rucherList.add(getNewRucher(9L, "Rucher Accacia", "Bois"));
        return rucherList;
    }

    public Extraction getNewExtraction(Long id, LocalDate date, String nomMieler, Long qteExtraite, Long qteHausses) throws ParseException {
        var extraction = new Extraction();
        extraction.setId(id);
        extraction.setDateExtraction(date);
        extraction.setNomMieler(nomMieler);
        extraction.setQteExtraite(qteExtraite);
        extraction.setQteHausse(qteHausses);
        extraction.setRecoltes(getListRecoltes());
        extraction.setProducts(getListProducts());
        return extraction;
    }

    public Product getNewProduct(Long id, Double price, Long poids, Long quantityProduct, Long quantityVendu, String numeroLot, LocalDate ddm, LocalDate date) throws ParseException {
        Product newProduct = new Product();
        newProduct.setId(id);
        newProduct.setPrix(price);
        newProduct.setPoids(poids);
        newProduct.setQuantiteCreer(quantityProduct);
        newProduct.setQuantiteVendu(quantityVendu);
        newProduct.setNumeroLot(numeroLot);
        newProduct.setDDM(ddm);
        newProduct.setDateConditionnement(date);
        newProduct.setExtraction(getNewExtraction(1L, LocalDate.of(2024, 5, 20), "printemps", 100L, 6L));
        return newProduct;
    }

    public List<Product> getListProducts() throws ParseException {
        List<Product> products = new ArrayList<>();
        products.add(getNewProduct(1L, 8.90, 500L, 100L, 0L, "05052025", LocalDate.of(2024, 5, 20), LocalDate.of(2024, 5, 20)));
        products.add(getNewProduct(2L, 4.90, 2500L, 1000L, 0L, "05052025-2", LocalDate.of(2024, 5, 20), LocalDate.of(2024, 5, 20)));
        products.add(getNewProduct(3L, 13.90, 1500L, 500L, 0L, "05052025-3", LocalDate.of(2024, 5, 20), LocalDate.of(2024, 5, 20)));
        return products;
    }

    public Commande getNewCommande(Long id, String numeroCommande, Double prix, LocalDate date) {
        var commande = new Commande();
        commande.setId(id);
        commande.setNumeroCommande(numeroCommande);
        commande.setPrixTotal(prix);
        commande.setDateCommande(date);
        commande.setStatusCommande(getNewStatus(1L, "En Cours"));
        commande.setClient(getNewClient(55L, "Alex", "Terrieur", "alex@terrieur.com", "0606060606"));
        return commande;
    }

    public StatusCommande getNewStatus(Long id, String status) {
        var statusCommande = new StatusCommande();
        statusCommande.setId(id);
        statusCommande.setNom(status);
        return statusCommande;
    }

    public Client getNewClient(Long id, String firstname, String lastname, String email, String telephone) {
        var client = new Client();
        client.setId(id);
        client.setFirstname(firstname);
        client.setLastname(lastname);
        client.setEmail(email);
        client.setTelephone(telephone);
        client.setAdresse(getNewAdresse());
        return client;
    }

    public List<CommandeProducts> getListCommandeProducts() throws ParseException {
        List<CommandeProducts> listProductCommande = new ArrayList<>();

        var product1 = new CommandeProducts();
        product1.setId(new CommandeProductsKey());
        product1.setProduct(getNewProduct(1L, 8.90, 500L, 100L, 0L, "05052025", LocalDate.of(2024, 5, 20), LocalDate.of(2024, 5, 20)));
        product1.setCommande(getNewCommande(100L, "A20250405", 17.8, LocalDate.of(2025, 05, 04)));
        product1.setQuantity(2L);
        listProductCommande.add(product1);

        var product2 = new CommandeProducts();
        product2.setId(new CommandeProductsKey());
        product2.setProduct(getNewProduct(1L, 4.50, 500L, 100L, 0L, "05052025", LocalDate.of(2024, 5, 20), LocalDate.of(2024, 5, 20)));
        product2.setCommande(getNewCommande(100L, "A20250405", 17.8, LocalDate.of(2025, 05, 04)));
        product2.setQuantity(1L);
        listProductCommande.add(product1);

        return listProductCommande;
    }

    public ProductDto getProductMiel(Long id, String nomProduit, Double price, Long poids, Long quantityProduct, Long quantityVendu, String numeroLot, LocalDate ddm, LocalDate date, Long recolteId) {
        ProductDto newProduct = new ProductDto();
        newProduct.setId(id);
        newProduct.setNomProduit(nomProduit);
        newProduct.setPrix(price);
        newProduct.setPoids(poids);
        newProduct.setQuantiteCreer(quantityProduct);
        newProduct.setQuantiteVendu(quantityVendu);
        newProduct.setNumeroLot(numeroLot);
        newProduct.setDDM(ddm);
        newProduct.setDateConditionnement(date);
        newProduct.setExtractionId(recolteId);
        return newProduct;
    }

    public RucheDto getRucheDto(Long id, String name, String beeType, String typeRuche, Long idRucher) {
        RucheDto newRuche = new RucheDto();
        newRuche.setId(id);
        newRuche.setName(name);
        newRuche.setBeetype(beeType);
        newRuche.setTyperuche(typeRuche);
        newRuche.setRucherId(idRucher);
        return newRuche;
    }

    public RucherDto getRucherDto(Long id, String name, String environnement, Long apiculteurId) {
        RucherDto newRucher = new RucherDto();
        newRucher.setId(id);
        newRucher.setName(name);
        newRucher.setEnvironnement(environnement);
        newRucher.setApiculteurId(apiculteurId);
        newRucher.setAdresse(getNewAdresse());
        return newRucher;
    }

    public RecolteDto getRecolteDto(Long id, String name, LocalDate date, Long quantity, Long nbHausses, Long rucheId, Long extractionId) {
        RecolteDto newRecolte = new RecolteDto();
        newRecolte.setId(id);
        newRecolte.setNomMieler(name);
        newRecolte.setDateRecolte(date);
        newRecolte.setQteRecolte(quantity);
        newRecolte.setQteHausseRecolte(nbHausses);
        newRecolte.setRucheId(rucheId);
        newRecolte.setExtractionId(extractionId);
        return newRecolte;
    }

    public ApiculteurDto getApiculteurDto(Long id) {
        ApiculteurDto newApiculteur = new ApiculteurDto();
        newApiculteur.setId(id);
        newApiculteur.setFirstname("Alain");
        newApiculteur.setLastname("Terrieur");
        newApiculteur.setAdresse(getNewAdresse());
        newApiculteur.setSiret("123456a456");
        newApiculteur.setNapi("AP123456");
        newApiculteur.setEmail("test@test.com");
        newApiculteur.setTelephone("0606060606");
        return newApiculteur;
    }

       /*public CommandeAllProductDto getVenteAllProduct() throws ParseException {
        CommandeAllProductDto commandeAllProductDto = new CommandeAllProductDto();
        commandeAllProductDto.setVente(getNewVente(2L, 19.50));
        commandeAllProductDto.setListProduct(getListVenteProductMielDto());
        return commandeAllProductDto;
    }

    public List<CommandeProductDto> getListVenteProductMielDto() throws ParseException {
        List<CommandeProductDto> listProduct = new ArrayList<>();

        CommandeProductDto product1 = new CommandeProductDto();
        product1.setProduct(getNewProduct(79L, 7.50, 500L, 1000L, 780L));
        product1.setQuantity(2L);
        listProduct.add(product1);

        CommandeProductDto product2 = new CommandeProductDto();
        product2.setQuantity(1L);
        product2.setProduct(getNewProduct(53L, 4.50, 250L, 1500L, 550L));
        listProduct.add(product2);

        return listProduct;
    }
    */

    public List<RecolteDto> getListRecoltesDTO() {
        List<RecolteDto> recolteList = new ArrayList<>();
        recolteList.add(getRecolteDto(9L, "Printemps", LocalDate.of(2023, 5, 20), 500L, 33L, 1L, 2L));
        recolteList.add(getRecolteDto(2L, "Ete", LocalDate.of(2023, 6, 20), 800L, 53L, 2L, 2L));
        recolteList.add(getRecolteDto(5L, "Sarrasin", LocalDate.of(2023, 7, 20), 200L, 13L, 8L, 2L));
        return recolteList;
    }

    public List<RucheDto> getListRuchesDTO() {
        List<RucheDto> rucheList = new ArrayList<>();
        rucheList.add(getRucheDto(55L, "#55", "Noir", "Dadant", 1L));
        rucheList.add(getRucheDto(2L, "#2", "Buck", "Dadant", 1L));
        rucheList.add(getRucheDto(15L, "#15", "Caucasienne", "Dadant", 2L));
        return rucheList;
    }

    public List<RucherDto> getListRuchersDTO() {
        List<RucherDto> rucherList = new ArrayList<>();
        rucherList.add(getRucherDto(3L, "Rucher du petit bois", "Bois", 1L));
        rucherList.add(getRucherDto(1L, "Rucher Marais", "Marais", 1L));
        rucherList.add(getRucherDto(9L, "Rucher Accacia", "Bois", 1L));
        return rucherList;
    }


    public TracabiliteDto getTracabiliteDto(Long id) {
        TracabiliteDto tracabiliteDto = new TracabiliteDto();
        tracabiliteDto.setProduct(getProductMiel(id, "miel printemps 2025", 5.40, 250L, 200L, 0L, "25052025", LocalDate.of(2027, 05, 25), LocalDate.of(2025, 05, 25), 1L));
        tracabiliteDto.setRecoltes(getListRecoltesDTO());
        tracabiliteDto.setRuches(getListRuchesDTO());
        tracabiliteDto.setRuchers(getListRuchersDTO());
        tracabiliteDto.setApiculteur(new ApiculteurDto());
        return tracabiliteDto;
    }
}
