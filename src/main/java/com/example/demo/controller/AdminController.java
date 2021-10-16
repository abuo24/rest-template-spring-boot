package com.example.demo.controller;// Author - Orifjon Yunusjonov
// t.me/coderr24

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@CrossOrigin(maxAge = 3600, origins = "*")
public class AdminController {

}
