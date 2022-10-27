package com.example.backend_se104.service;

import com.example.backend_se104.entity.model.User;
import com.example.backend_se104.entity.month_user;
import com.example.backend_se104.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void removeUserByUserId(String userId) {
        User user = userRepository.findUserByUserId(userId);
        userRepository.delete(user);
    }

    public User findUserName(String username) {
        return userRepository.findByNameUser(username);
    }

    public User findUserByUserId(String userId) {
        return userRepository.findUserByUserId(userId);
    }

    public List<month_user> getUserAndMonnth() {
        return userRepository.getUserAndMonnth();
    }

}
