package com.meli.socialMeli.repository;

import com.meli.socialMeli.domain.entity.Product;
import com.meli.socialMeli.domain.entity.Publication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
