package com.cubautoqa.server.controller;

import com.cubautoqa.server.component.RestTemplateService;
import com.cubautoqa.server.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/external")
public class ExternalApiController {
    @Autowired
    private RestTemplateService restTemplateService;

    @GetMapping("/users")
    public List<User> getExternalUsers() {
        String url = "https://jsonplaceholder.typicode.com/users";
        User[] users = restTemplateService.get(url, User[].class);
        return Arrays.asList(users);
    }

    @PostMapping("/create-user")
    public User createExternalUser(@RequestBody User user) {
        String url = "https://jsonplaceholder.typicode.com/users";
        return restTemplateService.post(url, user, User.class);
    }

}
