package com.example.spring_boot_jwt.repository;

import com.example.spring_boot_jwt.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IBookRepository extends MongoRepository<Book, String> {

}
