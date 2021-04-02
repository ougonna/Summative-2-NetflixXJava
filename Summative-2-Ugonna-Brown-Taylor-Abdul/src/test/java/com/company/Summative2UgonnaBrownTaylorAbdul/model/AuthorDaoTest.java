package com.company.Summative2UgonnaBrownTaylorAbdul.model;

import com.company.Summative2UgonnaBrownTaylorAbdul.dao.AuthorDao;
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
    protected AuthorDao dao;

    @Before
    public void setUp() throws Exception{

        //clean out database
        List<Author> aList = dao.getAllAuthors();

        for (Author a : aList){
            dao.deleteAuthor(a.getAuthorID());
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
        auth = dao.addAuthor(auth);
        Author auth2 = dao.getAuthor(auth.getAuthorID());

        //Assert
        assertEquals(auth, auth2);

        //Act
        dao.deleteAuthor(auth.getAuthorID());
        auth2 = dao.getAuthor(auth.getAuthorID());

        //Assert
        assertNull(auth2);

    }


}
