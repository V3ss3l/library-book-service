package com.library.librarybookservice.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private String genre;
    private String isbn;
    private String description;
    private Date publishDate;
    private Integer quantity;
}
