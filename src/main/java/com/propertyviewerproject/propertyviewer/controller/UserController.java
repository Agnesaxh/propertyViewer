package com.propertyviewerproject.propertyviewer.controller;

import com.propertyviewerproject.propertyviewer.entity.User;
import com.propertyviewerproject.propertyviewer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/findUser")
    public User findUser(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable User user) {
        userService.deleteUser(user);
    }

    @PutMapping("/updateUser")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }
}
