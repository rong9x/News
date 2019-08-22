package com.codegym.news.service;

import com.codegym.news.model.User;

public interface UserService {

    void save(User user);

    User findById(Long id);

    User findFirstByEmail(String email);
}
