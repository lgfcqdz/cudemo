package com.cubautoqa.server.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cubautoqa.server.entity.User;
import com.cubautoqa.server.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.getById(id));
    }
    @GetMapping("/page")
    public Page<User> getUsersPage(@RequestParam(defaultValue = "1") Integer current,
                                   @RequestParam(defaultValue = "10") Integer size) {
        return userService.page(new Page<>(current, size));
    }
    @PostMapping
    public Boolean saveUser(@RequestBody User user) {
        return userService.save(user);
    }
    @PostMapping("/importUsers")
    public ResponseEntity login(@RequestParam("filePath") String filePath) {
        if (StringUtils.isBlank(filePath)) {
            return ResponseEntity.notFound().build();
        }
        try {
            userService.importUsers(filePath);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

//    @PostMapping("/findUser")
//    public ResponseEntity<User> getUserByName(@RequestBody User user) {
//        User userRecord = userService.getOne(user.getUsername());
//        log.debug("userRecord：" + userRecord);
//        if (userRecord != null) {
//            return ResponseEntity.ok(userRecord); // 200 OK
//        } else {
//            return ResponseEntity.notFound().build(); // 404 Not Found
//        }
//    }
}
