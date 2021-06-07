package com.meli.socialMeli.repository;

import com.meli.socialMeli.domain.entity.Buyer;
import com.meli.socialMeli.domain.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SellerRepository extends JpaRepository<Seller, Integer> {

    @Query(value = "SELECT  COUNT(*)  FROM FOLLOWS WHERE SELLER_ID=:id", nativeQuery = true)
    Long countByBuyers(@Param("id") Integer id);
}
//SELECT  COUNT(*)  FROM FOLLOWS WHERE SELLER_ID = 1