package com.example.spring_boot_jwt.service;

import com.example.spring_boot_jwt.model.Book;

import java.util.List;

public interface IBookService {
    List<Book> findAll();

    Book getById(String id);

    Book update(String id, Book bookDetails);

    void delete(String id);

    void save(Book book);

    void deleteAll();

    void saveAll(List<Book> books);
}
