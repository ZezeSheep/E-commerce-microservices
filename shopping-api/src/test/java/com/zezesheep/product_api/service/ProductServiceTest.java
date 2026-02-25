package com.zezesheep.product_api.service;

import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zezesheep.shopping_api.service.ProductService;
import com.zezesheep.shopping_client.dto.ProductDTO;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;


@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    public static MockWebServer mockBackEnd;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    public void setup() throws IOException {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();

        String baseUrl = String.format("http://localhost:%s", mockBackEnd.getPort());
        ReflectionTestUtils.setField(productService, "productApiURL", baseUrl);
    }

    @AfterEach
    public void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }
    // TO DO: entender erro do teste
    // @Test 
    // public void test_getProductByIdentifier() throws Exception {
    //     ProductDTO productDTO = new ProductDTO();
    //     productDTO.setProductIdentifier("prod-identifier");
    //     productDTO.setPreco(100F);

    //     ObjectMapper objectMapper = new ObjectMapper();
    //     mockBackEnd.enqueue(new MockResponse().setBody(objectMapper.writeValueAsString(productDTO)).addHeader("Content-Type", "application/json"));

    //     productDTO = productService.getProductByIdentifier("prod-identifier");
    //     assert productDTO.getProductIdentifier().equals("prod-identifier");
    //     assert productDTO.getPreco() == 100F;
        
    // }
    
}
