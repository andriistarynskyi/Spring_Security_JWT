package com.example.spring_boot_jwt.service;

import com.example.spring_boot_jwt.model.Book;
import com.example.spring_boot_jwt.model.Role;
import com.example.spring_boot_jwt.model.User;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Component
public class DbDataSeeder implements CommandLineRunner {
    private final IBookService bookService;
    private final IUserService userService;

    @Override
    public void run(String... args) {
        this.bookService.deleteAll();
        loadBooksData();
        this.userService.deleteAll();
        loadUsersData();
    }

    private void loadBooksData() {
        if (bookService.findAll().isEmpty()) {
            List<Book> books = Arrays.asList(
                    new Book("The Little Prince.", "Book is about prince"),
                    new Book("Don Quixote.", "Book about Don Quixote."),
                    new Book("The Broker.", "Crime, Thriller & Adventure")
            );
            bookService.saveAll(books);
        }
    }

    private void loadUsersData() {
        if (userService.findAll().isEmpty()) {
            List<User> users = Arrays.asList(
                    new User("Alex_Kingston",
                            "alkingtons",
                            "qwerty",
                            Arrays.asList(Role.ADMINISTRATOR)),
                    new User("Peter_Kensington",
                            "peterkensington",
                            "askdjh", Arrays.asList(Role.CUSTOMER))
            );
            userService.saveAll(users);
        }
    }
}