package com.company.Summative2UgonnaBrownTaylorAbdul.model;

import com.company.Summative2UgonnaBrownTaylorAbdul.dao.AuthorDao;
import com.company.Summative2UgonnaBrownTaylorAbdul.dao.BookDao;
import com.company.Summative2UgonnaBrownTaylorAbdul.dao.PublisherDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AuthorDaoTest {

    @Autowired
    AuthorDao authorDao;
    @Autowired
    BookDao bookDao;
    @Autowired
    PublisherDao publisherDao;


    @Before
    public void setUp() throws Exception {

        //clean out the db
        List<Author> aList = authorDao.getAllAuthors();
        for (Author a : aList) {
            authorDao.deleteAuthor(a.getAuthorID());
        }

        List<Publisher> pList = publisherDao.getAllPublishers();
        for (Publisher p : pList) {
            publisherDao.deletePublisher(p.getId());
        }

        List<Book> dList = bookDao.getAllBooks();

        for (Book d : dList) {
            bookDao.deleteBook(d.getBookID());
        }

    }

    @Test
    public void addGetDeleteAuthor(){

        //Arrange
        Author auth = new Author();
        auth.setFirstName("BookFirst");
        auth.setLastName("BookLast");
        auth.setEmail("last@gmail.com");
        auth.setPhone("3334445678");
        auth.setCity("Norfolk");
        auth.setState("VA");
        auth.setStreet("700 park ave");
        auth.setPostalCode("34509");

        //act
        auth = authorDao.addAuthor(auth);
        Author auth2 = authorDao.getAuthor(auth.getAuthorID());

        //Assert
        assertEquals(auth, auth2);

        //Act
        authorDao.deleteAuthor(auth.getAuthorID());
        auth2 = authorDao.getAuthor(auth.getAuthorID());

        //Assert
        assertNull(auth2);

    }

    @Test
    public void getALlAuthors(){

        //Arrange: arranging first Authors
        Author auth = new Author();
        auth.setFirstName("BookSecond");
        auth.setLastName("BookSecondLast");
        auth.setEmail("seclast@gmail.com");
        auth.setPhone("2224445678");
        auth.setCity("Norfolk");
        auth.setState("PA");
        auth.setStreet("700 park road");
        auth.setPostalCode("34456");

        //Act
        authorDao.addAuthor(auth);

        //Arrange: Arrange second motorcycle
        auth = new Author();
        auth.setFirstName("BookFirstLast");
        auth.setLastName("BookLastLast");
        auth.setEmail("lastlast@gmail.com");
        auth.setPhone("3335555678");
        auth.setCity("Washington");
        auth.setState("DC");
        auth.setStreet("700 road ave");
        auth.setPostalCode("34510");

        //Act
        authorDao.addAuthor(auth);
        List<Author> authorList = authorDao.getAllAuthors(); //gets all authors

        //Assert
        assertEquals(authorList.size(), 2);

    }



    @Test
    public void updateAuthors(){

        //Arrange
        Author auth = new Author();
        auth.setFirstName("UpdateName");
        auth.setLastName("UpdateLast");
        auth.setEmail("Updatelast@gmail.com");
        auth.setPhone("99944455467");
        auth.setCity("Manassas");
        auth.setState("VA");
        auth.setStreet("Update 700 park ave");
        auth.setPostalCode("33450");

        //Act
        auth = authorDao.addAuthor(auth);

        //Arrange
        auth = new Author();
        auth.setFirstName("Update2Name");
        auth.setLastName("Update2Last");
        auth.setEmail("Updatelast2@gmail.com");
        auth.setPhone("99927855467");
        auth.setCity("Norfolk");
        auth.setState("VA");
        auth.setStreet("Update2 700 park ave");
        auth.setPostalCode("32150");

        //Act
        authorDao.updateAuthor(auth); //updates db with new information
        Author auth2 = authorDao.getAuthor(auth.getAuthorID()); //creates another motorcycle
                                                          //gets id of the motorcycle created above and assign to auth2

        //Assert
        assertEquals(auth2, auth);
    }



}
