package com.company.Summative2UgonnaBrownTaylorAbdul.dao;

import com.company.Summative2UgonnaBrownTaylorAbdul.model.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PublisherDayJdbcTemplateImpl implements PublisherDao{

    private JdbcTemplate jdbcTemplate;

    //Prepared statements strings

    private static final String INSERT_PUBLISHER_SQL =
            "insert into publisher (name, street, city, state, postal_code, phone, email) values (?, ?, ?, ?, ?, ?, ?)";

    private static final String SELECT_PUBLISHER_SQL =
            "select * from publisher where publisher_id = ?";

    private static final String SELECT_ALL_PUBLISHER_SQL =
            "select * from publisher";

    private static final String UPDATE_PUBLISHER_SQL =
            "update track set name = ?, street = ?, city = ?, state = ?, postal_code = ?, phone = ?, email = ?  where publisher_id = ?";

    private static final String DELETE_PUBLISHER_SQL =
            "delete from track where publisher_id =  ?";

    @Autowired
    public PublisherDayJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

    @Override
    public Publisher addPublisher(Publisher publisher){
        return null;
    }

    @Override
    public Publisher getPublisher(int id){
        return null;
    }

    @Override
    public List<Publisher> getAllPublishers(){
        return null;
    }

    @Override
    public void updatePublisher(Publisher publisher){

    }

    @Override
    public void deletePublisher(int id){

    }
}
