package com.example.demo.exceptions;// Author - Orifjon Yunusjonov
// t.me/coderr24


import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@Data
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ResourceNotFoundException  extends RuntimeException{
    private String message;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
