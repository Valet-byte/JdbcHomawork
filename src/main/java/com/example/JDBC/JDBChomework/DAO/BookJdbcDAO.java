package com.example.JDBC.JDBChomework.DAO;

import com.example.JDBC.JDBChomework.mappers.AuthorMapper;
import com.example.JDBC.JDBChomework.mappers.GenreMapper;
import com.example.JDBC.JDBChomework.models.Author;
import com.example.JDBC.JDBChomework.models.Book;
import com.example.JDBC.JDBChomework.models.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class BookJdbcDAO {

    private final NamedParameterJdbcTemplate jdbc;

    public Book findById(long id){
        final Map<String, Object> map = new HashMap<>(1);
        map.put("id", id);
        Map<String, Object> res = jdbc.queryForMap("select * from book where id = :id", map);

        Book b = (Book) jdbc.query("select * from bok where id = :id", map,
                (rs, num) -> new Book(rs.getLong("id"), rs.getString("name"), rs.getLong("author_id"), rs.getLong("genre_id")));

        Author author = jdbc.queryForObject("select * from author where id = :AUTHOR_ID", res, new AuthorMapper());

        Genre genre = jdbc.queryForObject("select * from genre where id = :GENRE_ID", res, new GenreMapper());

        return new Book( (long) res.get("ID"), (String) res.get("NAME"), author, genre);
    }

    public long insertBook(String bookName,
                           String authorName,
                           String genre){
        final MapSqlParameterSource source = new MapSqlParameterSource();

        source.addValue("bookName", bookName);
        source.addValue("authorName", authorName);
        source.addValue("genre", genre);

        KeyHolder key = new GeneratedKeyHolder();

        jdbc.update("insert into genre (name) values :genre", source, key);
        source.addValue("genre_id", Objects.requireNonNull(key.getKey()).longValue());
        jdbc.update("insert into author (name) values :authorName", source, key);
        source.addValue("author_id", key.getKey().longValue());

        jdbc.update("insert into book (name, author_id, genre_id) values " +
                "(:bookName, :author_id, :genre_id)", source, key);

        return key.getKey().longValue();
    }

    public List<Book> findAll() {
        return jdbc.query("select * from book", (rs, rowNum) ->
                new Book(rs.getLong("id"), rs.getString("name"), rs.getLong("author_id"), rs.getLong("genre_id")));
    }

    public List<Book> findПожалуйстаByGenre(String genre) {
        final MapSqlParameterSource source = new MapSqlParameterSource();

        source.addValue("genre_name", genre);

        Genre genre1 = jdbc.queryForObject("select * from genre where name = :genre_name", source, new GenreMapper());
        source.addValue("genre_id", genre1.getID());

        return jdbc.query("select * from book where genre_id = :genre_id", source, (rs, rowNum) ->
                new Book(rs.getLong("id"), rs.getString("name"), rs.getLong("author_id"), rs.getLong("genre_id")));
    }

    public List<Book> findByAuthorName(String name) {
        final MapSqlParameterSource source = new MapSqlParameterSource();

        source.addValue("name", name);

        Author author = jdbc.queryForObject("select * from author where name = :name", source, new AuthorMapper());
        source.addValue("author_id", Objects.requireNonNull(author).getID());

        return jdbc.query("select * from book where author_id = :author_id", source, (rs, rowNum) ->
                new Book(rs.getLong("id"), rs.getString("name"), rs.getLong("author_id"), rs.getLong("genre_id")));
    }

    public long insertBook(Book book) {
        final MapSqlParameterSource source = new MapSqlParameterSource();

        source.addValue("bookName", book.getName());
        source.addValue("authorName", book.getAuthor().getName());
        source.addValue("genre", book.getGenre().getName());

        KeyHolder key = new GeneratedKeyHolder();

        jdbc.update("if not EXISTS(select * from genre where name = :genre) " +
                "begin " +
                " insert into genre (name) values :genre " +
                "end else begin " +
                "select id where name = :genre" +
                "end", source, key);
        source.addValue("genre_id", Objects.requireNonNull(key.getKey()).longValue());
        jdbc.update("insert into author (name) values :authorName", source, key);
        source.addValue("author_id", key.getKey().longValue());

        jdbc.update("insert into book (name, author_id, genre_id) values " +
                "(:bookName, :author_id, :genre_id)", source, key);

        return key.getKey().longValue();
    }

    public List<Book> findByName(String name) {
        final MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("name", name);
        return jdbc.query("select * from book where name = :name", source, (rs, rowNum) ->
                new Book(rs.getLong("id"), rs.getString("name"), rs.getLong("author_id"), rs.getLong("genre_id")));
    }

    public Book updateNameBookById(String name, long id) {

        final MapSqlParameterSource source = new MapSqlParameterSource();

        source.addValue("id", id);
        source.addValue("name", name);

        jdbc.update("update book " +
                "set name = :name where id = :id", source);

        return findById(id);
    }
}
