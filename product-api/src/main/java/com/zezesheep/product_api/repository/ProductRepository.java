package com.zezesheep.product_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.zezesheep.product_api.model.Product;
import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query(value = "select p from product p "
    +" join category c on p.category.id = c.id "
    +" where c.id = :categoryId ")
    public List<Product> getProductByCategory(@Param("categoryId") long categoryId);
    
    public Product findByProductIdentifier(String productIdentifier);
}
