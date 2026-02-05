package com.zezesheep.shopping_api.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zezesheep.shopping_api.converter.DTOConverter;
import com.zezesheep.shopping_api.model.Shop;
import com.zezesheep.shopping_api.repository.ShopRepository;
import com.zezesheep.shopping_client.dto.ItemDTO;
import com.zezesheep.shopping_client.dto.ProductDTO;
import com.zezesheep.shopping_client.dto.ShopDTO;
import com.zezesheep.shopping_client.dto.ShopReportDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;

    private final UserService userService;

    private final ProductService productService;

    public List<ShopDTO> getAll() {
        return shopRepository.findAll().stream().map(DTOConverter::convert).toList();
    }

    public List<ShopDTO> getByUser(String userIdentifier) {
        return shopRepository.findAllByUserIdentifier(userIdentifier).stream().map(DTOConverter::convert).toList();
    }

    public List<ShopDTO> getByDate(ShopDTO shopDTO){
        return shopRepository.findByDateGreaterThanEqual(shopDTO.getDate()).stream().map(DTOConverter::convert).toList();
    }

    public ShopDTO findById(Long productId){
        Optional<Shop> shop = shopRepository.findById(productId);
        return shop.map(DTOConverter::convert).orElse(null);
    }

    public ShopDTO save(ShopDTO shopDTO){
        if(userService.getUserByCpf(shopDTO.getUserIdentifier()) == null){
            return null;
        }

        if(!validateProducts(shopDTO.getItems())){
            return null;
        }

        shopDTO.setTotal(shopDTO.getItems().stream().map(x -> x.getPrice()).reduce((float) 0, Float::sum));
        Shop shop = Shop.convert(shopDTO);
        shop.setDate(LocalDateTime.now());
        return DTOConverter.convert(shopRepository.save(shop));
    }

    private boolean validateProducts(List<ItemDTO> items) {
        for(ItemDTO item : items){
            ProductDTO product = productService.getProductByIdentifier(item.getProductIdentifier());
            if(product == null){
                return false;
            }
            item.setPrice(product.getPreco());
        }
        return true;
    }

    public List<ShopDTO> getShopsByFilter(LocalDate dataInicio, LocalDate dataFim, Float valorMinimo){
        return shopRepository.getShopByFilters(dataInicio, dataFim, valorMinimo).stream().map(DTOConverter::convert).toList();
    }

    public ShopReportDTO getReportByDate(LocalDate dataInicio, LocalDate dataFim){
        return shopRepository.getReportByDate(dataInicio, dataFim);
    }
    
}
