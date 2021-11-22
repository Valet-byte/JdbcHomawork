package com.example.JDBC.JDBChomework.models;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.internal.IgnoreForbiddenApisErrors;
import org.springframework.data.jpa.repository.EntityGraph;

import javax.persistence.*;

@Data
@Entity
@Table(name = "book")
@NamedEntityGraph(name = "entity_graph",
attributeNodes = {@NamedAttributeNode("author"),
                  @NamedAttributeNode("genre")})

public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    @Column(name = "name")
    private String name;

    @ManyToOne(targetEntity = Author.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne(targetEntity = Genre.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @Column(name = "author_id", insertable =false, updatable = false)
    private long authorId;
    @Column(name = "genre_id", insertable =false, updatable = false)
    private long genreId;

    public Book(long ID, String name, Author author, Genre genre) {
        this.ID = ID;
        this.name = name;
        this.author = author;
        this.genre = genre;
    }

    public Book(String name, Author author, Genre genre) {
        this.name = name;
        this.author = author;
        this.genre = genre;
    }

    public Book(long ID, String name, Author author, Genre genre, long authorId, long genreId) {
        this.ID = ID;
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.authorId = authorId;
        this.genreId = genreId;
    }



    public Book(long ID, String name, long authorId, long genreId) {
        this.ID = ID;
        this.name = name;
        this.authorId = authorId;
        this.genreId = genreId;
    }

    public Book() {
    }


}
