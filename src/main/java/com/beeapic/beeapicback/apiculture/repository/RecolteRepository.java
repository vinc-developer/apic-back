package com.beeapic.beeapicback.apiculture.repository;

import com.beeapic.beeapicback.entity.Recolte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecolteRepository extends JpaRepository<Recolte, Long> {
}
