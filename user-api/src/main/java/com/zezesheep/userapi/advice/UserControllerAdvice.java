package com.zezesheep.userapi.advice;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.zezesheep.shopping_client.dto.ErrorDTO;
import com.zezesheep.shopping_client.exception.UserNotFoundException;

@ControllerAdvice(basePackages = "com.zezesheep.userapi.controller")
public class UserControllerAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorDTO handleUserNotFoundException(UserNotFoundException e){
        ErrorDTO error = new ErrorDTO();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage("Usuário não encontrado");
        error.setTimestamp(LocalDateTime.now());
        return error;
    }
    
}
