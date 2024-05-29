package com.beeapic.beeapicback.apiculture.repository;

import com.beeapic.beeapicback.entity.Ruche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RucheRepository extends JpaRepository<Ruche, Long> {
}
