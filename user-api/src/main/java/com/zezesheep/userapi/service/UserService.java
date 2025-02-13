package com.zezesheep.userapi.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.zezesheep.userapi.dto.UserDTO;
import com.zezesheep.userapi.model.User;
import com.zezesheep.userapi.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public List<UserDTO> getAll(){
        List<User> listUsuarios = userRepository.findAll();
        return listUsuarios.stream().map(UserDTO::convert).collect(Collectors.toList());
    }

    public UserDTO findById(Long userId){
        User usuario = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return UserDTO.convert(usuario);
    }

    public UserDTO save(UserDTO userDTO){
        userDTO.setDataCadastro(LocalDateTime.now());
        User user = userRepository.save(User.convert(userDTO));
        return UserDTO.convert(user);
    }

    public UserDTO delete(long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
        return UserDTO.convert(user);
    }

    public UserDTO findByCpf(String cpf){
        User user = userRepository.findByCpf(cpf);
        return user == null ? null : UserDTO.convert(user);
    }

    public List<UserDTO> queryByName(String name){
        List<User> usuarios = userRepository.queryByNomeLike(name);
        return usuarios.stream().map(UserDTO::convert).collect(Collectors.toList());
    }

    public UserDTO editUser(long userId, UserDTO userDTO){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        if(userDTO.getEmail() != null && !userDTO.getEmail().equals(user.getEmail())){
            user.setEmail(user.getEmail());
        }
        if(userDTO.getTelefone() != null && !userDTO.getTelefone().equals(user.getTelefone())){
            user.setTelefone(user.getTelefone());
        }
        if(userDTO.getEndereco() != null && !userDTO.getEndereco().equals(user.getEndereco())){
            user.setEndereco(user.getEndereco());
        }

        user = userRepository.save(user);
        return UserDTO.convert(user);
    }

    public Page<UserDTO> getAllPage(Pageable page){
        Page<User> users = userRepository.findAll(page);
        return users.map(UserDTO::convert);
    }
    
}
