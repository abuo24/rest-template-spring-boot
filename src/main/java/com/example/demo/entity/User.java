package com.example.demo.entity;// Author - Orifjon Yunusjonov
// t.me/coderr24

import com.example.demo.entity.abstractEntity.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User extends AbstractEntity {

    //    phoneNumber
    @Column(unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(columnDefinition = "TEXT")
    private String fullname;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;


    public User(String username, String password, String fullname) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
    }

}
