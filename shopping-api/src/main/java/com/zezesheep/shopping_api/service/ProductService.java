package com.zezesheep.shopping_api.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.zezesheep.shopping_client.dto.ProductDTO;

@Service
public class ProductService {

    private final String productAPIUrl = "http://localhost:8081";

    public ProductDTO getProductByIdentifier(String productIdentifier) {
        try{
            WebClient webClient = WebClient.builder().baseUrl(productAPIUrl).build();
            return webClient.get().uri("/product/{productIdentifier}", productIdentifier).retrieve().bodyToMono(ProductDTO.class).block();
        }
        catch(Exception e){
            throw new RuntimeException("Product not found", e);
        }
    }
    
}
