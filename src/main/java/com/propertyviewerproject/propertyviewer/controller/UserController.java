package com.propertyviewerproject.propertyviewer.controller;

import com.propertyviewerproject.propertyviewer.entity.User;
import com.propertyviewerproject.propertyviewer.exception.ResourceExistsException;
import com.propertyviewerproject.propertyviewer.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User addUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/users/{id}")
    public User findUser(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }
}
