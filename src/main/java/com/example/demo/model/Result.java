package com.example.demo.model;// Author - Orifjon Yunusjonov
// t.me/coderr24

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Result {
    private boolean success;
    private String message;
}
