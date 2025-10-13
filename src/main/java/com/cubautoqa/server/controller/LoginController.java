package com.cubautoqa.server.controller;

import com.cubautoqa.server.service.UserService;
import com.cubautoqa.server.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    private UserService userService;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> loginRequest) {
        String username = loginRequest.get("username");
        String password = loginRequest.get("password");

        Map<String, Object> response = new HashMap<>();

        // 验证用户
        User user = userService.getById(1);
        if (user == null || !user.getPassword().equals(password)) {
            response.put("success", false);
            response.put("message", "用户名或密码错误");
            return response;
        }

        // 生成JWT token
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 24小时
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();

        response.put("success", true);
        response.put("token", token);
        return response;
    }

}
