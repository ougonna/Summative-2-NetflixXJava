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
public class PublisherDaoTest {

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
    public void addGetDeletePublisher(){

        Publisher publisher = new Publisher();
        publisher.setName("PublisherTest");
        publisher.setEmail("publish@gmail.com");
        publisher.setStreet("Test street");
        publisher.setPhone("4473347877");
        publisher.setCity("Norfolk");
        publisher.setState("VA");
        publisher.setPostalCode("33456");
        publisher = publisherDao.addPublisher(publisher); //adds new publisher

        Publisher publisher1 = publisherDao.getPublisher(publisher.getId()); //creates a new publisher and assigns current publisher to it

        //Assert
        assertEquals(publisher1, publisher);

        publisherDao.deletePublisher(publisher.getId()); //deletes publisher which was just added

        publisher1 = publisherDao.getPublisher(publisher.getId()); //assigns publisher which should be null after deletion to publisher1

        //Assert
        assertNull(publisher1);
    }

    @Test
    public void getAllPublishers(){

        //Adds first publisher
        Publisher publisher = new Publisher();
        publisher.setName("PublisherTest");
        publisher.setEmail("publish@gmail.com");
        publisher.setStreet("Test street");
        publisher.setPhone("4473347877");
        publisher.setCity("Norfolk");
        publisher.setState("VA");
        publisher.setPostalCode("33456");
        publisher = publisherDao.addPublisher(publisher);

        publisher = new Publisher();
        publisher.setName("PublisherTest2");
        publisher.setEmail("publish2@gmail.com");
        publisher.setStreet("Test2 street");
        publisher.setPhone("4411147877");
        publisher.setCity("Manassas");
        publisher.setState("VA");
        publisher.setPostalCode("22456");
        publisher = publisherDao.addPublisher(publisher);

        List<Publisher> pList = publisherDao.getAllPublishers(); //gets all publishers and adds to new list

        //Assert
        assertEquals(pList.size(), 2);
    }

    @Test
    public void updatePublisher(){

        Publisher publisher = new Publisher();
        publisher.setName("PublisherTest");
        publisher.setEmail("publish@gmail.com");
        publisher.setStreet("Test street");
        publisher.setPhone("4473347877");
        publisher.setCity("Norfolk");
        publisher.setState("VA");
        publisher.setPostalCode("33456");
        publisher = publisherDao.addPublisher(publisher);

        publisher.setName("NewPublisher");
        publisher.setEmail("newpublisheremail@gmail.com");
        publisher.setPhone("3345786547");

        publisherDao.updatePublisher(publisher);

        Publisher publisher1 = publisherDao.getPublisher(publisher.getId());

        //Assert
        assertEquals(publisher1, publisher);
    }

}
