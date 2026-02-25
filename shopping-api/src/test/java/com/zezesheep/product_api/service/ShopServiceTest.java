package com.zezesheep.product_api.service;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.zezesheep.shopping_api.model.Shop;
import com.zezesheep.shopping_api.repository.ShopRepository;
import com.zezesheep.shopping_api.service.ProductService;
import com.zezesheep.shopping_api.service.ShopService;
import com.zezesheep.shopping_api.service.UserService;
import com.zezesheep.shopping_client.dto.ItemDTO;
import com.zezesheep.shopping_client.dto.ProductDTO;
import com.zezesheep.shopping_client.dto.ShopDTO;
import com.zezesheep.shopping_client.dto.UserDTO;

@ExtendWith(MockitoExtension.class)
public class ShopServiceTest {

    @InjectMocks
    private ShopService shopService;

    @Mock 
    private ShopRepository shopRepository;

    @Mock 
    private ProductService productService;

    @Mock 
    private UserService userService;

    @Test
    public void test_saveShop(){
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setProductIdentifier("123");
        itemDTO.setPrice(100F);

        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setUserIdentifier("123");
        shopDTO.setItems(Arrays.asList(itemDTO));
        shopDTO.setTotal(100F);

        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductIdentifier("123");
        productDTO.setPreco(100F);

        Mockito.when(userService.getUserByCpf("123", "123")).thenReturn(new UserDTO());
        Mockito.when(productService.getProductByIdentifier("123")).thenReturn(productDTO);
        Mockito.when(shopRepository.save(Mockito.any())).thenReturn(Shop.convert(shopDTO));

        shopDTO = shopService.save(shopDTO, "123");
        assert shopDTO.getTotal() == 100F;
        assert 1 == shopDTO.getItems().size();
        Mockito.verify(shopRepository, Mockito.times(1)).save(Mockito.any());
        
    }


    
}
