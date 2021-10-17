package com.example.demo.controller;// Author - Orifjon Yunusjonov
// t.me/coderr24

import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.json.SendSms;
import com.example.demo.security.SmsConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.User;
import com.example.demo.model.Result;
import com.example.demo.payload.LoginPayload;
import com.example.demo.payload.UserPayload;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.security.SecurityUtils;
import com.example.demo.service.UserService;
import org.springframework.web.client.RestTemplate;

import java.lang.module.ResolutionException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(maxAge = 3600L)
public class AuthController {
    private final UserRepository userRepository;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final SecurityUtils securityUtils;
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginPayload payload) {
        User user = userRepository.findByUsername(payload.getUsername()).orElseThrow(() -> new RuntimeException("user not found"));
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(payload.getUsername(), payload.getPassword()));
        String token = jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
        if (token == null) {
            return new ResponseEntity(new Result(false, "something went wrong"), HttpStatus.BAD_REQUEST);
        }
        Map<String, Object> map = new HashMap();
        map.put("token", token);
        map.put("username", user.getUsername());
        map.put("success", true);
        return ResponseEntity.ok(map);
    }
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserPayload userPayload) {
        return ResponseEntity.ok(userService.save(userPayload));
    }

    @PostMapping("/forgot/{username}")
    public ResponseEntity forgot(@PathVariable String username) {
        User user = userRepository.findByUsername(username).orElseThrow(()-> new ResolutionException("user not found"));
        if (user==null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(SmsConstant.getToken());
        String url = new String("https://notify.eskiz.uz/api/message/sms/send");
        Map<String, String> params = new HashMap<String, String>();
        params.put("mobile_phone", "998932099924");
        int rand = new Random().nextInt(99999)+100000;
        params.put("message", String.valueOf(rand +" bu sms test uchun"));
        params.put("from", "4546");
        params.put("callback_url", "http://0000.uz/test.php");
        HttpEntity<?> request = new HttpEntity<>(params, headers);
        ResponseEntity<SendSms> response = restTemplate.postForEntity(url,request,SendSms.class);
        if (response.getStatusCode()==HttpStatus.OK){
            String urlRes = "https://notify.eskiz.uz/api/message/sms/status/"+response.getBody().getId();
            request = new HttpEntity<>(headers);
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    urlRes,
                    HttpMethod.GET,
                    request,
                    String.class,
                    1
            );
            if (response.getStatusCode()==HttpStatus.OK){
                return ResponseEntity.ok("sas");
            } else {

                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

}
