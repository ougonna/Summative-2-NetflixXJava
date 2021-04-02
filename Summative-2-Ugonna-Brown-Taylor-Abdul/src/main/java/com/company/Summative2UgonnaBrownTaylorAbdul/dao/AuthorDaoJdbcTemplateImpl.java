package com.company.Summative2UgonnaBrownTaylorAbdul.dao;

import com.company.Summative2UgonnaBrownTaylorAbdul.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorDaoJdbcTemplateImpl implements AuthorDao{

    // Prepared statement strings
    private static final String INSERT_AUTHOR_SQL =
            "insert into author (first_name, last_name, street, city, state, postal_code,phone,email) values (?, ?, ?, ?, ?, ?, ? ,?)";

    private static final String SELECT_AUTHOR_SQL =
            "select * from author where id = ?";

    private static final String SELECT_ALL_AUTHOR_SQL =
            "select * from author";

    private static final String DELETE_AUTHOR_SQL =
            "delete from author where id = ?";

    private static final String UPDATE_AUTHOR_SQL =
            "update car set make = ?, model = ?, year = ?, color = ? where id = ?";


    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthorDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    public Author addAuthor(Author author){
        return author;
    }

    public Author getAuthor (int id){
        return null;
    }

    public List<Author> getAllAuthors(){
        return null;
    }

    public void updateAuthor(Author author){

    }

    public void deleteAuthor(int id){

    }
}
