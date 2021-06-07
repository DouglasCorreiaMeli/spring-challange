package com.meli.socialMeli.repository;

import com.meli.socialMeli.domain.entity.Publication;
import com.meli.socialMeli.domain.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PublicationRepository extends JpaRepository<Publication, Integer> {

    @Query(value = "SELECT  COUNT(*) FROM PUBLICATION INNER JOIN SELLER_PUBLICATIONS ON PUBLICATION.ID = SELLER_PUBLICATIONS.PUBLICATIONS_ID AND SELLER_ID=:id  WHERE  HASPROMO=TRUE", nativeQuery = true)
    Long countPromoProducts(@Param("id") Integer id);

    @Query(value = "SELECT  * FROM PUBLICATION INNER JOIN SELLER_PUBLICATIONS ON PUBLICATION.ID = SELLER_PUBLICATIONS.PUBLICATIONS_ID AND SELLER_ID=:id  WHERE  HASPROMO=TRUE", nativeQuery = true)
    List <Publication> listPromoProducts(@Param("id") Integer id);

    @Modifying
    @Query(value = "INSERT INTO  SELLER_PUBLICATIONS (SELLER_ID, PUBLICATIONS_ID) values (:sellerId,:pubId)", nativeQuery = true)
    void insertSellerPub(@Param("sellerId") Integer sellerId, @Param("pubId") Integer pubId);
}
