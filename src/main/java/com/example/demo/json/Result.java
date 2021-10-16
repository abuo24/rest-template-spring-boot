package com.example.demo.json;// Author - Orifjon Yunusjonov 
// t.me/coderr24
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Result {
    private String message;
    private Datas data;
}
