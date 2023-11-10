package com.himangshu.springboot.repository;

import com.himangshu.springboot.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE "
            + "CONCAT(p.id, p.productName, p.price, p.manufacturer, p.raiting)"
            + "LIKE %?1%"
    )
    public Page<Product> findAll(String keyword, Pageable pageable);
}
