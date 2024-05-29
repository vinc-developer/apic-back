package com.beeapic.beeapicback.vente.repository;

import com.beeapic.beeapicback.entity.Vente;
import com.beeapic.beeapicback.entity.VentesProductMiel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VenteProductRepository extends JpaRepository<VentesProductMiel, Long> {
    List<VentesProductMiel> findAllByVente(Vente vente);
}
