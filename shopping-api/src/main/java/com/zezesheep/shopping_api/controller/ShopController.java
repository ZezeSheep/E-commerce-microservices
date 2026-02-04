package com.zezesheep.shopping_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.zezesheep.shopping_api.dto.ShopDTO;
import com.zezesheep.shopping_api.service.ShopService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;



@RestController
@RequiredArgsConstructor
@RequestMapping("/shopping")
public class ShopController {

    private final ShopService shopService;

    @GetMapping
    public List<ShopDTO> getshops() {
        return shopService.getAll();
    }

    @GetMapping("/shopByUser/{userIdentifier}")
    public List<ShopDTO> getShopByUser(@PathVariable String userIdentifier) {
        return shopService.getByUser(userIdentifier);
    }

    @GetMapping("/shopByDate")
    public List<ShopDTO> getShopByDate(@RequestBody ShopDTO shopDTO) {
        return shopService.getByDate(shopDTO);
    }

    @GetMapping("/{id}")
    public ShopDTO getShopById(@PathVariable Long id) {
        return shopService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ShopDTO newShop(@Valid @RequestBody ShopDTO shopDTO) {
        return shopService.save(shopDTO);
    }
    


    
    
}
