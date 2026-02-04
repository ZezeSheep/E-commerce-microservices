package com.zezesheep.shopping_api.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.zezesheep.shopping_api.model.Shop;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ShopDTO {

    @NotBlank
    private String userIdentifier;
    private Float total;
    private LocalDateTime date;
    @NotNull
    private List<ItemDTO> items;

    public static ShopDTO convert(Shop shop) {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setUserIdentifier(shop.getUserIdentifier());
        shopDTO.setTotal(shop.getTotal());
        shopDTO.setDate(shop.getDate());
        shopDTO.setItems(shop.getItems().stream().map(ItemDTO::convert).toList());
        return shopDTO;
    }
    
}
