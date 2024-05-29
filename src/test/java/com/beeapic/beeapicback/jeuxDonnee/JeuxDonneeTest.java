package com.beeapic.beeapicback.jeuxDonnee;

import com.beeapic.beeapicback.apiculture.dto.ApiculteurDto;
import com.beeapic.beeapicback.apiculture.dto.RecolteDto;
import com.beeapic.beeapicback.apiculture.dto.RucheDto;
import com.beeapic.beeapicback.apiculture.dto.RucherDto;
import com.beeapic.beeapicback.entity.*;
import com.beeapic.beeapicback.tracabilite.dto.TracabiliteDto;
import com.beeapic.beeapicback.vente.dto.ProductMielDto;

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
        newRuche.setRucher( getNewRucher(3L, "Rucher du petit bois", "Bois") );
        return newRuche;
    }

    public Rucher getNewRucher(Long id, String name, String environnement) throws ParseException {
        Rucher newRucher = new Rucher();
        newRucher.setId(id);
        newRucher.setName(name);
        newRucher.setEnvironnement(environnement);
        newRucher.setApiculteur( getNewApiculteur() );
        newRucher.setAdresse( getNewAdresse() );
        return newRucher;
    }

    public Recolte getNewRecolte(Long id, String name, LocalDate date, Long quantity) throws ParseException {
        Recolte newRecolte = new Recolte();
        newRecolte.setId(id);
        newRecolte.setName(name);
        newRecolte.setRecoltedate(date);
        newRecolte.setRecoltekg(quantity);
        newRecolte.setRuche( getNewRuche(55L, "#55", "Noir", "Dadant") );
        return newRecolte;
    }

    public Apiculteur getNewApiculteur() {
        Apiculteur newApiculteur = new Apiculteur();
        newApiculteur.setId(1L);
        newApiculteur.setFirstname("Alain");
        newApiculteur.setLastname("Terrieur");
        newApiculteur.setAdresse( getNewAdresse() );
        newApiculteur.setSiret("123456a456");
        newApiculteur.setNapi("AP123456");
        newApiculteur.setEmail("test@test.com");
        newApiculteur.setTelephone("0606060606");
        newApiculteur.setPassword("Azerty123");
        return newApiculteur;
    }

    public ProductMiel getNewProduct(Long id, Double price, Long poids, Long quantityProduct, Long quantityVendu) throws ParseException {
        ProductMiel newProduct = new ProductMiel();
        newProduct.setId(id);
        newProduct.setPrice(price);
        newProduct.setPoids(poids);
        newProduct.setQuantitetotalpot(quantityProduct);
        newProduct.setQuantitevendupot(quantityVendu);
        newProduct.setRecolte( getNewRecolte(9L, "Printemps", LocalDate.of(2023, 5, 20), 500L) );
        return newProduct;
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

    public List<Recolte> getListRecolte() throws ParseException {
        List<Recolte> recolteList = new ArrayList<>();
        recolteList.add( getNewRecolte(9L, "Printemps", LocalDate.of(2023, 5, 20), 500L) );
        recolteList.add( getNewRecolte(2L, "Ete", LocalDate.of(2023, 6, 20), 800L) );
        recolteList.add( getNewRecolte(5L, "Sarrasin", LocalDate.of(2023, 7, 20), 200L) );
        return recolteList;
    }

    public List<Ruche> getListRuche() throws ParseException {
        List<Ruche> rucheList = new ArrayList<>();
        rucheList.add( getNewRuche(55L, "#55", "Noir", "Dadant") );
        rucheList.add( getNewRuche(2L, "#2", "Buck", "Dadant") );
        rucheList.add( getNewRuche(15L, "#15", "Caucasienne", "Dadant") );
        return  rucheList;
    }

    public List<Rucher> getListRucher() throws ParseException {
        List<Rucher> rucherList = new ArrayList<>();
        rucherList.add( getNewRucher(3L, "Rucher du petit bois", "Bois") );
        rucherList.add( getNewRucher(1L, "Rucher Marais", "Marais") );
        rucherList.add( getNewRucher(9L, "Rucher Accacia", "Bois") );
        return rucherList;
    }

    public List<Product> getListProduct() throws ParseException {
        List<Product> productList = new ArrayList<>();
        productList.add( getNewProduct(79L, 7.50, 500L, 1000L, 780L) );
        productList.add( getNewProduct(15L, 13.00, 1000L, 500L, 280L) );
        productList.add( getNewProduct(53L, 4.50, 250L, 1500L, 550L) );
        return productList;
    }

    public TracabiliteDto getTracabiliteDto(Long id) {
        TracabiliteDto tracabiliteDto = new TracabiliteDto();
        tracabiliteDto.setProduct(new ProductMielDto(id, 7.50, 500L, 1000L, 780L, 9L));
        tracabiliteDto.setRecolte(new RecolteDto(9L, "Printemps", LocalDate.of(2023, 5, 20), 1000L, 55L));
        tracabiliteDto.setRuche(new RucheDto(55L, "#55", "Dadant", "Noir", 3L));
        tracabiliteDto.setRucher(new RucherDto(3L, "Rucher du petit bois", "Bois", new Adresse(), 1L));
        tracabiliteDto.setApiculteur( new ApiculteurDto() );
        return tracabiliteDto;
    }
}
