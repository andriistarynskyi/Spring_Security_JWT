package com.example.spring_boot_jwt.service;

import com.example.spring_boot_jwt.model.Book;
import com.example.spring_boot_jwt.repository.IBookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class BookService implements IBookService {
    private final IBookRepository bookRepository;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book getById(String id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book with id" + id + " was not found"));
    }

    @Override
    public Book update(String id, Book bookDetails) {
        Book book = getById(id);
        book.setTitle(bookDetails.getTitle());
        book.setAbout(bookDetails.getAbout());
        save(book);
        return null;
    }

    @Override
    public void delete(String id) {
        bookRepository.delete(getById(id));
    }

    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void deleteAll() {
        bookRepository.deleteAll();
    }

    @Override
    public void saveAll(List<Book> books) {
        bookRepository.saveAll(books);
    }
}
