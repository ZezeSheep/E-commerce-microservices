package com.zezesheep.userapi.converter;

import com.zezesheep.shopping_client.dto.UserDTO;
import com.zezesheep.userapi.model.User;

public class DTOConverter {

    public static UserDTO convert(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setNome(user.getNome());
        userDTO.setCpf(user.getCpf());
        userDTO.setTelefone(user.getTelefone());
        userDTO.setEndereco(user.getEndereco());
        userDTO.setEmail(user.getEmail());
        userDTO.setDataCadastro(user.getDataCadastro());
        return userDTO;
    }
    
}
