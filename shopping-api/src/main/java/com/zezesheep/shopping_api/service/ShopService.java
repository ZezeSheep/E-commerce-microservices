package com.zezesheep.shopping_api.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zezesheep.shopping_api.dto.ShopDTO;
import com.zezesheep.shopping_api.dto.ShopReportDTO;
import com.zezesheep.shopping_api.model.Shop;
import com.zezesheep.shopping_api.repository.ShopRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;

    public List<ShopDTO> getAll() {
        return shopRepository.findAll().stream().map(ShopDTO::convert).toList();
    }

    public List<ShopDTO> getByUser(String userIdentifier) {
        return shopRepository.findAllByUserIdentifier(userIdentifier).stream().map(ShopDTO::convert).toList();
    }

    public List<ShopDTO> getByDate(ShopDTO shopDTO){
        return shopRepository.findByDateGreaterThanEqual(shopDTO.getDate()).stream().map(ShopDTO::convert).toList();
    }

    public ShopDTO findById(Long productId){
        Optional<Shop> shop = shopRepository.findById(productId);
        return shop.map(ShopDTO::convert).orElse(null);
    }

    public ShopDTO save(ShopDTO shopDTO){
        shopDTO.setTotal(shopDTO.getItems().stream().map(x -> x.getPrice()).reduce((float) 0, Float::sum));
        Shop shop = Shop.convert(shopDTO);
        shop.setDate(LocalDateTime.now());
        return ShopDTO.convert(shopRepository.save(shop));
    }

    public List<ShopDTO> getShopsByFilter(LocalDate dataInicio, LocalDate dataFim, Float valorMinimo){
        return shopRepository.getShopByFilters(dataInicio, dataFim, valorMinimo).stream().map(ShopDTO::convert).toList();
    }

    public ShopReportDTO getReportByDate(LocalDate dataInicio, LocalDate dataFim){
        return shopRepository.getReportByDate(dataInicio, dataFim);
    }
    
}
