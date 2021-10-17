package com.example.demo.json;// Author - Orifjon Yunusjonov 
// t.me/coderr24
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SendSms {
    private Long id;
    private String message;
    private String status;
}
