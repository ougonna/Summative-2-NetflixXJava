package com.company.Summative2UgonnaBrownTaylorAbdul.dao;

import com.company.Summative2UgonnaBrownTaylorAbdul.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookDaoJdbcTemplateImpl implements BookDao
{

    // Prepared statement strings
    private static final String INSERT_BOOK_SQL =
            "insert into book (isbn, publish_date, author_id, title, publisher_id, price) values (?, ?, ?, ?, ?, ?)";

    private static final String SELECT_BOOK_SQL =
            "select * from book where book_id = ?";

    private static final String SELECT_ALL_BOOKS_SQL =
            "select * from book";

    private static final String DELETE_BOOK_SQL =
            "delete from book where book_id = ?";

    private static final String UPDATE_BOOK_SQL =
            "update book set publish_date = ?, isbn = ?, author_id = ?, title = ?, publisher_id = ?, price = ? where book_id = ?";

    private static final String SELECT_BOOKS_BY_AUTHOR_SQL =
            "select * from book join author on book.author_id = author.author_id where author.first_name = ?";


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDaoJdbcTemplateImpl (JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }



    @Override
    @Transactional
    public Book addBook(Book book)
    {
        jdbcTemplate.update(INSERT_BOOK_SQL,
                //book.getPublishDate(),
                book.getIsbn(),
                book.getPublishDate().toString(),
                book.getAuthorID(),
                book.getTitle(),
                book.getPublisherID(),
                book.getPrice());

        int bookID = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        book.setBookID(bookID);
        return book;
    }

    @Override
    public Book getBook(int bookID)
    {
        try
        {
            return jdbcTemplate.queryForObject(SELECT_BOOK_SQL, this::mapRowToBook, bookID);
        } catch (EmptyResultDataAccessException e)
        {
            // if nothing is returned just catch the exception and return null
            return null;
        }
    }

    @Override
    public List<Book> getAllBooks()
    {
        return jdbcTemplate.query(SELECT_ALL_BOOKS_SQL, this::mapRowToBook);
    }

    @Override
    public void updateBook(Book book)
    {
        jdbcTemplate.update(UPDATE_BOOK_SQL,
                //book.getPublishDate(),
                Date.valueOf(book.getPublishDate()),
                book.getIsbn(),
                book.getAuthorID(),
                book.getTitle(),
                book.getPublisherID(),
                book.getPrice(),
                book.getBookID());

    }
    @Override
    public void deleteBook(int bookID)
    {
        jdbcTemplate.update(DELETE_BOOK_SQL, bookID);
    }

    @Override
    public List <Book> getBooksByAuthor(String author)
    {
        return jdbcTemplate.query(SELECT_BOOKS_BY_AUTHOR_SQL, this::mapRowToBook, author);
    }



    //row mapper
    private Book mapRowToBook(ResultSet rs, int rowNum) throws SQLException
    {
        Book book = new Book();
        book.setBookID(rs.getInt("book_id"));
        book.setIsbn(rs.getString("isbn"));
        book.setPrice(rs.getBigDecimal("price"));
        book.setPublisherID(rs.getInt("publisher_id"));
        book.setAuthorID(rs.getInt("author_id"));
        book.setPublishDate(rs.getDate("publish_date").toLocalDate());
        book.setTitle(rs.getString("title"));

        return book;
    }

}
