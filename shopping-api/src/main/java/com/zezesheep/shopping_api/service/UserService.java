package com.zezesheep.shopping_api.service;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.zezesheep.shopping_client.dto.UserDTO;
import com.zezesheep.shopping_client.exception.UserNotFoundException;

@Service
public class UserService {

    private final String userAPIUrl = "http://localhost:8080";
    
    public UserDTO getUserByCpf(String cpf, String key){
        try{
            WebClient webClient = WebClient.builder().baseUrl(userAPIUrl).build();
            return webClient.get().uri("/user/" +cpf + "/cpf?key=" + key).retrieve().bodyToMono(UserDTO.class).block();
        }
        catch(Exception e){
            throw new UserNotFoundException();
        }
    }
}
