package com.beeapic.beeapicback.apiculture.repository;

import com.beeapic.beeapicback.entity.Apiculteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiculteurRepository extends JpaRepository<Apiculteur, Long> {

}
