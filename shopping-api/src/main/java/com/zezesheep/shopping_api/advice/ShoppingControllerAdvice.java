package com.zezesheep.shopping_api.advice;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.zezesheep.shopping_client.dto.ErrorDTO;
import com.zezesheep.shopping_client.exception.ProductNotFoundException;
import com.zezesheep.shopping_client.exception.UserNotFoundException;

@ControllerAdvice(basePackages = "com.zezesheep.shopping_api.controller")
public class ShoppingControllerAdvice {

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

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public ErrorDTO handleProductNotFoundException(ProductNotFoundException e) {
        ErrorDTO error = new ErrorDTO();
        error.setMessage("Produto não encontrado");
        error.setStatus(404);
        error.setTimestamp(LocalDateTime.now());
        return error;
    }
    
}
