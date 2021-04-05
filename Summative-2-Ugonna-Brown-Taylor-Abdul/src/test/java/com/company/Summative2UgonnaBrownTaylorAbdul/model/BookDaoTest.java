package com.company.Summative2UgonnaBrownTaylorAbdul.model;

import com.company.Summative2UgonnaBrownTaylorAbdul.dao.AuthorDao;
import com.company.Summative2UgonnaBrownTaylorAbdul.dao.BookDao;
import com.company.Summative2UgonnaBrownTaylorAbdul.dao.PublisherDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.validator.PublicClassValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BookDaoTest {

    @Autowired
    AuthorDao authorDao;
    @Autowired
    BookDao bookDao;
    @Autowired
    PublisherDao publisherDao;


    @Before
    public void setUp() throws Exception{

        //clean out the db
        List<Author> aList = authorDao.getAllAuthors();
        for(Author a : aList){
            authorDao.deleteAuthor(a.getAuthorID());
        }

        List<Publisher> pList = publisherDao.getAllPublishers();
        for(Publisher p : pList){
            publisherDao.deletePublisher(p.getId());
        }

        List<Book> dList = bookDao.getAllBooks();

        for (Book d : dList){
            bookDao.deleteBook(d.getBookID());
        }

    }

    @Test
    public void addGetDeleteBook(){

        //Arrange
        Author author = new Author();
        author.setFirstName("UpdateName");
        author.setLastName("UpdateLast");
        author.setEmail("Updatelast@gmail.com");
        author.setPhone("99944455467");
        author.setCity("Manassas");
        author.setState("VA");
        author.setStreet("Update 700 park ave");
        author.setPostalCode("33450");
        author = authorDao.addAuthor(author);

        Publisher publisher = new Publisher();
        publisher.setName("PublisherTest");
        publisher.setEmail("publish@gmail.com");
        publisher.setStreet("Test street");
        publisher.setPhone("4473347877");
        publisher.setCity("Norfolk");
        publisher.setState("VA");
        publisher.setPostalCode("33456");
        publisher = publisherDao.addPublisher(publisher);

        Book book = new Book();
        book.setTitle("testBook");
        book.setIsbn("TEST6578");
        book.setPrice(new BigDecimal("21.95"));
        book.setPublishDate(LocalDate.of(2010, 8, 5));
        book.setAuthorID(author.getAuthorID());
        book.setPublisherID(publisher.getId());
        book = bookDao.addBook(book);

        Book book1 = bookDao.getBook(book.getBookID()); //creates another book
                                                        //gets id of book just added and assigns to book1

        //Assert
        assertEquals(book1, book);

        bookDao.deleteBook(book.getBookID()); //deletes book just added
        book1 = bookDao.getBook(book.getBookID()); //gets empty book which is null and assigns to book1

        //Assert
        assertNull(book1);
    }

    @Test(expected = DataIntegrityViolationException.class)  //throws an exception if for any reason the one to one mapping is broken (e.g if author gets deleted without deleting the book, an exception will be thrown)
    public void addWithRefIntegrityException(){

        Book book = new Book();
        book.setTitle("testBook");
        book.setIsbn("TEST6578");
        book.setPrice(new BigDecimal("21.95"));
        book.setPublishDate(LocalDate.of(2010, 8, 5));
        book.setAuthorID(22);
        book.setPublisherID(34);

        book = bookDao.addBook(book);

    }

    @Test
    public void getAllBooks() {

        //Need to create an author and publisher first
        Author author = new Author();
        author.setFirstName("UpdateName");
        author.setLastName("UpdateLast");
        author.setEmail("Updatelast@gmail.com");
        author.setPhone("99944455467");
        author.setCity("Manassas");
        author.setState("VA");
        author.setStreet("Update 700 park ave");
        author.setPostalCode("33450");
        author = authorDao.addAuthor(author);

        Publisher publisher = new Publisher();
        publisher.setName("PublisherTest");
        publisher.setEmail("publish@gmail.com");
        publisher.setStreet("Test street");
        publisher.setPhone("4473347877");
        publisher.setCity("Norfolk");
        publisher.setState("VA");
        publisher.setPostalCode("33456");
        publisher = publisherDao.addPublisher(publisher);

        //add first book
        Book book = new Book();
        book.setTitle("testBook");
        book.setIsbn("TEST6578");
        book.setPrice(new BigDecimal("21.95"));
        book.setPublishDate(LocalDate.of(2010, 8, 5));
        book.setAuthorID(author.getAuthorID());
        book.setPublisherID(publisher.getId());
        book = bookDao.addBook(book);

        //add second book
        book = new Book();
        book.setTitle("testBook20");
        book.setIsbn("TEST6578-22");
        book.setPrice(new BigDecimal("30.95"));
        book.setPublishDate(LocalDate.of(2012, 9, 9));
        book.setAuthorID(author.getAuthorID());
        book.setPublisherID(publisher.getId());
        book = bookDao.addBook(book);

        List<Book> bList = bookDao.getAllBooks();

        assertEquals(bList.size(), 2);
    }

    @Test
    public void getBookByAuthor(){

        //Need to create an author and publisher first
        Author author = new Author();
        author.setFirstName("UpdateName");
        author.setLastName("UpdateLast");
        author.setEmail("Updatelast@gmail.com");
        author.setPhone("99944455467");
        author.setCity("Manassas");
        author.setState("VA");
        author.setStreet("Update 700 park ave");
        author.setPostalCode("33450");
        author = authorDao.addAuthor(author);

        Publisher publisher = new Publisher();
        publisher.setName("PublisherTest");
        publisher.setEmail("publish@gmail.com");
        publisher.setStreet("Test street");
        publisher.setPhone("4473347877");
        publisher.setCity("Norfolk");
        publisher.setState("VA");
        publisher.setPostalCode("33456");
        publisher = publisherDao.addPublisher(publisher);

        //add first book
        Book book = new Book();
        book.setTitle("testBook");
        book.setIsbn("TEST6578");
        book.setPrice(new BigDecimal("21.95"));
        book.setPublishDate(LocalDate.of(2010, 8, 5));
        book.setAuthorID(author.getAuthorID());
        book.setPublisherID(publisher.getId());
        book = bookDao.addBook(book);

        //add second book
        book = new Book();
        book.setTitle("testBook20");
        book.setIsbn("TEST6578-22");
        book.setPrice(new BigDecimal("30.95"));
        book.setPublishDate(LocalDate.of(2012, 9, 9));
        book.setAuthorID(author.getAuthorID());
        book.setPublisherID(publisher.getId());
        book = bookDao.addBook(book);

        //Act
        bookDao.addBook(book);
       // List<Book> bList = bookDao.getBooksByAuthor("PublisherTest");

        //Assert
        //assertEquals(bList.size(), 2);

    }

    @Test
    public  void updateBook(){

        //Need to create an author and publisher first
        Author author = new Author();
        author.setFirstName("UpdateName");
        author.setLastName("UpdateLast");
        author.setEmail("Updatelast@gmail.com");
        author.setPhone("99944455467");
        author.setCity("Manassas");
        author.setState("VA");
        author.setStreet("Update 700 park ave");
        author.setPostalCode("33450");
        author = authorDao.addAuthor(author);

        Publisher publisher = new Publisher();
        publisher.setName("PublisherTest");
        publisher.setEmail("publish@gmail.com");
        publisher.setStreet("Test street");
        publisher.setPhone("4473347877");
        publisher.setCity("Norfolk");
        publisher.setState("VA");
        publisher.setPostalCode("33456");
        publisher = publisherDao.addPublisher(publisher);

        //add first book
        Book book = new Book();
        book.setTitle("testBook");
        book.setIsbn("TEST6578");
        book.setPrice(new BigDecimal("21.95"));
        book.setPublishDate(LocalDate.of(2010, 8, 5));
        book.setAuthorID(author.getAuthorID());
        book.setPublisherID(publisher.getId());
        book = bookDao.addBook(book);


        //new information to be updated
        book.setTitle("NEW TITLE");
        book.setPublishDate(LocalDate.of(2020, 12, 6));
        book.setPrice(new BigDecimal("50.47"));

        bookDao.updateBook(book);  //updates book

        Book book1 = bookDao.getBook(book.getBookID()); //creates new book and assigns updated book to it (book1)
        assertEquals(book1, book);

    }
}
