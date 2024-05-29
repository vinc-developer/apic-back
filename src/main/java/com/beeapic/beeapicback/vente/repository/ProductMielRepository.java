package com.beeapic.beeapicback.vente.repository;

import com.beeapic.beeapicback.entity.ProductMiel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMielRepository extends JpaRepository<ProductMiel, Long> {
}
