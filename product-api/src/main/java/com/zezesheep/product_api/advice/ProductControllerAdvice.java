package com.zezesheep.product_api.advice;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.zezesheep.shopping_client.dto.ErrorDTO;
import com.zezesheep.shopping_client.exception.CategoryNotFoundException;
import com.zezesheep.shopping_client.exception.ProductNotFoundException;

@ControllerAdvice(basePackages = "com.zezesheep.product_api.controller")
public class ProductControllerAdvice {

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

    
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CategoryNotFoundException.class)
    public ErrorDTO handleCategoryNotFoundException(CategoryNotFoundException e) {
        ErrorDTO error = new ErrorDTO();
        error.setMessage("Categoria não encontrada");
        error.setStatus(404);
        error.setTimestamp(LocalDateTime.now());
        return error;
    }

    //MethodArgumentNotValidException
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorDTO handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ErrorDTO error = new ErrorDTO();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        StringBuilder sb = new StringBuilder("Valores inválidos para os campos:");
        for (FieldError fieldError : fieldErrors) {
            sb.append(' ').append(fieldError.getField()).append(": ").append(fieldError.getDefaultMessage());
        }
        error.setMessage(sb.toString());
        error.setTimestamp(LocalDateTime.now());
        return error;
    }
    
}
