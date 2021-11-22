package com.example.JDBC.JDBChomework.models;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@Table(name = "GENRE")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @Column(name = "name")
    private String name;

    public Genre(long ID, String genre) {
        this.ID = ID;
        this.name = genre;
    }

    public Genre(String name) {
        this.name = name;
    }

    public Genre() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return Objects.equals(name, genre.name);
    }
}
