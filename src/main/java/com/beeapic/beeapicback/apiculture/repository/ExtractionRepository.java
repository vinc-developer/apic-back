package com.beeapic.beeapicback.apiculture.repository;

import com.beeapic.beeapicback.entity.Extraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtractionRepository extends JpaRepository<Extraction, Long> {
}
