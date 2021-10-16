package com.example.demo.exceptions;// Author - Orifjon Yunusjonov
// t.me/coderr24


import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@Data
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException{
    private String message;

    public BadRequestException(String message) {
        super(message);
    }
}
