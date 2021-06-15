package com.example.spring_boot_jwt.controller;

import com.example.spring_boot_jwt.model.Book;
import com.example.spring_boot_jwt.service.IBookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Slf4j
public class BookController {
    private final IBookService bookService;

    @GetMapping("/")
    public ResponseEntity<List<Book>> getAll() {
        try {
            List<Book> books = bookService.findAll();
            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable String id) {
        try {
            Book book = bookService.getById(id);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable String id, @RequestBody Book bookDetails) {
        try {
            Book book = bookService.getById(id);
            bookService.update(id, bookDetails);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteBook(@PathVariable String id) {
        try {
            Map<String,Boolean> response = new HashMap<>();
            bookService.delete(id);
            response.put("Deleted", Boolean.TRUE);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}