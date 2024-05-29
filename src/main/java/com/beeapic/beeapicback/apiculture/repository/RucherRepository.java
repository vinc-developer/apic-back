package com.beeapic.beeapicback.apiculture.repository;

import com.beeapic.beeapicback.entity.Rucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RucherRepository extends JpaRepository<Rucher, Long> {
}
