package com.beeapic.beeapicback.commerce.repository;

import com.beeapic.beeapicback.entity.Commande;
import com.beeapic.beeapicback.entity.CommandeProducts;
import com.beeapic.beeapicback.entity.CommandeProductsKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandeProductsRepository extends JpaRepository<CommandeProducts, CommandeProductsKey> {
    List<CommandeProducts> findAllByCommande(Commande commande);
}
