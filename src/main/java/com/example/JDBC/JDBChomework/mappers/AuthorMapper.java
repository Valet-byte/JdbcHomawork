package com.example.JDBC.JDBChomework.mappers;

import com.example.JDBC.JDBChomework.models.Author;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorMapper implements RowMapper<Author> {
    @Override
    public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
        Author author = new Author(rs.getLong("id"), rs.getString("name"));
        return author;
    }
}
