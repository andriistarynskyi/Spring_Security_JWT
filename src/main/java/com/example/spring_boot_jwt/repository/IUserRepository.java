package com.example.spring_boot_jwt.repository;


import com.example.spring_boot_jwt.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IUserRepository extends MongoRepository<User, String> {
    User findByLogin(String login);
}