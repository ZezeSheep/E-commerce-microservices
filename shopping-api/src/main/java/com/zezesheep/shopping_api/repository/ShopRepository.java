package com.zezesheep.shopping_api.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zezesheep.shopping_api.model.Shop;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {

    public List<Shop> findAllByUserIdentifier(String userIdentifier);

    public List<Shop> findAllByTotalGreaterThan(Float total);

    public List<Shop> findByDateGreaterThanEqual(LocalDateTime date);
    
}
