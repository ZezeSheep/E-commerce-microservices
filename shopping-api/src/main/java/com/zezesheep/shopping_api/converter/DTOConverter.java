package com.zezesheep.shopping_api.converter;

import com.zezesheep.shopping_api.model.Item;
import com.zezesheep.shopping_api.model.Shop;
import com.zezesheep.shopping_client.dto.ItemDTO;
import com.zezesheep.shopping_client.dto.ShopDTO;

public class DTOConverter {

    public static ShopDTO convert(Shop shop) {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setUserIdentifier(shop.getUserIdentifier());
        shopDTO.setTotal(shop.getTotal());
        shopDTO.setDate(shop.getDate());
        shopDTO.setItems(shop.getItems().stream().map(DTOConverter::convert).toList());
        return shopDTO;
    }

    public static ItemDTO convert(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setProductIdentifier(item.getProductIdentifier());
        itemDTO.setPrice(item.getPrice());
        return itemDTO;
    }
    
}
