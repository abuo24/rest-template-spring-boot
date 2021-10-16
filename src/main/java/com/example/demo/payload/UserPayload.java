package com.example.demo.payload;// Author - Orifjon Yunusjonov
// t.me/coderr24

import lombok.Data;

import java.util.UUID;

@Data
public class UserPayload {
    private String username;

    private String password;
    private String fullname;
    private UUID groupId;

    public UserPayload(String username, String password, String fullname) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
    }
}
