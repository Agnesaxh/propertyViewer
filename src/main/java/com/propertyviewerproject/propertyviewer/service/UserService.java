package com.propertyviewerproject.propertyviewer.service;


import com.propertyviewerproject.propertyviewer.entity.User;
import com.propertyviewerproject.propertyviewer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
        System.out.println("User deleted!");
    }

    public User updateUser(User user) {
        User existingUser = userRepository.findById(user.getId()).orElse(null);
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setBuildings(user.getBuildings());
        return userRepository.save(existingUser);
    }
}
