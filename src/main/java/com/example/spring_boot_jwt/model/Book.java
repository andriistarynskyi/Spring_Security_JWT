package com.example.spring_boot_jwt.model;

import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "books")
@Data
public class Book {
    @Id
    private String id;
    private String title;
    private String about;

    public Book(String title, String about) {
        this.title = title;
        this.about = about;
    }

    public Book() {
    }

    public Book(String id, String title, String about) {
        this.id = id;
        this.title = title;
        this.about = about;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id.equals(book.id) && title.equals(book.title) && about.equals(book.about);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, about);
    }
}