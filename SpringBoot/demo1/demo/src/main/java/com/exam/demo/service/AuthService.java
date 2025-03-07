package com.exam.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.demo.dal.UserRepository;
import com.exam.demo.model.User;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    public User validateUser(String userId, String password) {
        Optional<User> userOpt = userRepository.findByUserIdAndPassword(userId, password);

        if (userOpt.isEmpty()) {
            System.out.println("Login failed: User not found or invalid password -> " + userId);
            return null;
        }

        return userOpt.get();
    }

    public boolean userExists(String userId) {
        return userRepository.findByUserId(userId).isPresent();
    }

    public User registerUser(User user) {
        return userRepository.save(user);
    }
}
