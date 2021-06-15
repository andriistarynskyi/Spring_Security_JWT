package com.example.spring_boot_jwt.service;

import com.example.spring_boot_jwt.model.User;

import java.util.List;

public interface IUserService {
    void save(User user);

    void saveAll(List<User> users);

    void deleteAll();

    List<User> findAll();

    User findByLogin(String login);
}