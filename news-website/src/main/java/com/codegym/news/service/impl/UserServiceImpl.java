package com.codegym.news.service.impl;

import com.codegym.news.model.User;
import com.codegym.news.repository.UserRepository;
import com.codegym.news.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User findFirstByEmail(String email) {
        return userRepository.findFirstByEmail(email);
    }
}
