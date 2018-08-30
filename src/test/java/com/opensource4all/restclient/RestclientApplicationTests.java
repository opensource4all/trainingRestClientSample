package com.opensource4all.restclient;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class RestClientApplicationTests {

    @Autowired
    JdbcTemplate jdbcTemplate;

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    @Test
    public void contextLoads() {
        logger.info(" In contextLoads with jdbcTemplate..." + jdbcTemplate.toString());
    }

    @Test
    public void batchInsertDemoJdbcTemplate() {  // just using the ? ? parameters in prepared stmt
        String insertSql = "insert into joke(id,joke) values (?,? )";
        ArrayList<Joke> jokeArrayList = new ArrayList<>();
        jokeArrayList.add(new Joke(1, "this is first joke"));
        jokeArrayList.add(new Joke(2, "this is second joke"));
        jokeArrayList.add(new Joke(3, "this is third joke"));

        jdbcTemplate.batchUpdate(insertSql, new BatchPreparedStatementSetterJoke(jokeArrayList,jokeArrayList.size()));
        Integer jokeCount = jdbcTemplate.queryForObject("Select count(*) from joke", Integer.class);
        assertEquals(jokeCount.intValue(), jokeArrayList.size());
    }

    @Test
    public void batchInsertDemoNamedJdbcTemplate() { // using :var, :var form
        String insertSql = "insert into joke(id,joke) values (:id,:value )";
        ArrayList<Joke> jokeArrayList = new ArrayList<>();
        jokeArrayList.add(new Joke(1, "this is first joke"));
        jokeArrayList.add(new Joke(2, "this is second joke"));
        jokeArrayList.add(new Joke(3, "this is third joke"));
        jdbcTemplate.batchUpdate(insertSql, new BatchPreparedNamedStatementSetterJoke(jokeArrayList,jokeArrayList.size()));
        Integer jokeCount = jdbcTemplate.queryForObject("Select count(*) from joke", Integer.class);
        assertEquals(jokeCount.intValue(), jokeArrayList.size());
    }
    private class BatchPreparedStatementSetterJoke implements BatchPreparedStatementSetter {


        Logger logger = LoggerFactory.getLogger(this.getClass().getName());

        private ArrayList<Joke> jokeArrayList;
        private int batchSize;

        public BatchPreparedStatementSetterJoke(ArrayList<Joke> jokeArrayList, int batchSize) {
            this.jokeArrayList = jokeArrayList;
            this.batchSize = batchSize;
        }
        @Override
        public void setValues(PreparedStatement ps, int i) throws SQLException {
            logger.info(" in setValues with i=" + i);
            Joke joke  = jokeArrayList.get(i);
            logger.info(" ***** in setValues with joke=" + joke );
            ps.setLong(1, joke.getId());
            ps.setString(2, joke.getJoke());
        }
        @Override
        public int getBatchSize() {
            return batchSize;
        }
    }

    private class BatchPreparedNamedStatementSetterJoke implements BatchPreparedStatementSetter {

        Logger logger = LoggerFactory.getLogger(this.getClass().getName());

        private ArrayList<Joke> jokeArrayList;
        private int batchSize;

        public BatchPreparedNamedStatementSetterJoke(ArrayList<Joke> jokeArrayList, int batchSize) {
            this.jokeArrayList = jokeArrayList;
            this.batchSize = batchSize;
        }
        @Override
        public void setValues(PreparedStatement ps, int i) throws SQLException {
            Joke joke  = jokeArrayList.get(i);
            logger.info(" ***** in {} named setValues with joke= {} " ,i, joke );
            ps.setLong(1, joke.getId());
            ps.setString(2, joke.getJoke());
        }
        @Override
        public int getBatchSize() {
            return batchSize;
        }
    }

    private class Joke {
        private int id;
        private String joke;

        public Joke(int id, String joke) {
            this.id = id;
            this.joke = joke;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getJoke() {
            return joke;
        }

        public void setJoke(String joke) {
            this.joke = joke;
        }

        @Override
        public String toString() {
            return "Joke{" +
                    "id=" + id +
                    ", joke='" + joke + '\'' +
                    '}';
        }
    }
}
