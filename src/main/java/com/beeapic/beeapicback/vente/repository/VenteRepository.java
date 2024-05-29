package com.beeapic.beeapicback.vente.repository;

import com.beeapic.beeapicback.entity.Vente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VenteRepository extends JpaRepository<Vente, Long> {
}
