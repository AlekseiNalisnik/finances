package com.application.finances.controller;

import com.application.finances.entity.User;
import com.application.finances.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/finances/")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/secured/user/profile")
    @ResponseStatus(HttpStatus.OK)
    public User getUser(Principal principal) {
        return userService.findByUsername(principal.getName())
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
//
//    @GetMapping("/users")
//    @ResponseStatus(HttpStatus.OK)
//    public List<User> getUsers() {
//        return userService.findAllUsers();
//    }
//
//    @GetMapping("/user/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public User getUser(@PathVariable Long id) {
//        return userService.findUserById(id);
//    }
//
//    @GetMapping("/user-delete/{id}")
//    public ResponseEntity deleteUser(@PathVariable Long id) {
//        userService.deleteUser(id);
//        return ResponseEntity.ok(HttpStatus.OK);
//    }
//
//    @PostMapping("/user-create")
//    public ResponseEntity createUser(User user) {
//        userService.createUser(user);
//        return ResponseEntity.ok(HttpStatus.OK);
//    }
}
