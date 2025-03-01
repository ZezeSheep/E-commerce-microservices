package com.zezesheep.product_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zezesheep.product_api.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}
