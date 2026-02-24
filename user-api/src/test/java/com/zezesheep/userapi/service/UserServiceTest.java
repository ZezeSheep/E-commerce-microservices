package com.zezesheep.userapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.zezesheep.shopping_client.dto.UserDTO;
import com.zezesheep.userapi.converter.DTOConverter;
import com.zezesheep.userapi.model.User;
import com.zezesheep.userapi.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void testListAllUsers(){
        List<User> users = new ArrayList<>();
        users.add(getUser(1l, "name 1", "123"));
        users.add(getUser(2l, "name 2", "456"));

        Mockito.when(userRepository.findAll()).thenReturn(users);

        List<UserDTO> result = userService.getAll();
        assert result.size() == 2;
    }

    @Test
    public void testSaveUser(){
        User userDB = getUser(1l, "name", "123");
        UserDTO userDTO = DTOConverter.convert(userDB);
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(userDB);
        UserDTO result = userService.save(userDTO);
        assert "name".equals(result.getNome());
        assert "123".equals(result.getCpf());
    }

    public static User getUser(Long id, String nome, String cpf){
        User user = new User();
        user.setId(id);
        user.setNome(nome);
        user.setCpf(cpf);
        user.setEndereco("endereco");
        user.setTelefone("5432");
        return user;
    }
    
}
