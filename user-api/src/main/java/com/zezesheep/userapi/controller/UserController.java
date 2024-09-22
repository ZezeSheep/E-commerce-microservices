package com.zezesheep.userapi.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zezesheep.userapi.dto.UserDTO;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/user")
public class UserController {

    public static List<UserDTO> usuarios = new ArrayList<>();

    @PostConstruct
    public void initiateList(){
        UserDTO eduardo = new UserDTO();
        eduardo.setNome("Eduardo");
        eduardo.setCpf("123");
        eduardo.setEndereco("Rua a");
        eduardo.setEmail("eduardo@gmail.com");
        eduardo.setTelefone("1234-3454");
        eduardo.setDataCadastro(LocalDateTime.now());

        UserDTO luiz = new UserDTO();
        luiz.setNome("Luiz");
        luiz.setCpf("456");
        luiz.setEndereco("Rua b");
        luiz.setEmail("luiz@gmail.com");
        luiz.setTelefone("1234-3454");
        luiz.setDataCadastro(LocalDateTime.now());

        UserDTO bruna = new UserDTO();
        bruna.setNome("Bruna");
        bruna.setCpf("678");
        bruna.setEndereco("Rua c");
        bruna.setEmail("bruna@gmail.com");
        bruna.setTelefone("1234-3454");
        bruna.setDataCadastro(LocalDateTime.now());

        usuarios.add(eduardo);
        usuarios.add(luiz);
        usuarios.add(bruna);
    }

    @GetMapping
    public List<UserDTO> getUsers(){
        return usuarios;
    }


    
}
