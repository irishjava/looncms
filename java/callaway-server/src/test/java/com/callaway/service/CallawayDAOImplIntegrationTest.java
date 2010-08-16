package com.callaway.service;

import com.callaway.domain.Score;
import com.callaway.domain.User;
import com.callaway.dto.ScoreBoard;
import com.callaway.dto.ScoreSubmission;
import junit.framework.JUnit4TestAdapter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "txManager")
@Transactional(propagation = Propagation.REQUIRED)
@ContextConfiguration(locations = {
        "classpath:common-application-context.xml",
        "classpath:hsql-datasource-context.xml",
        "classpath:application-context.xml"})
public class CallawayDAOImplIntegrationTest {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(new JUnit4TestAdapter(
                CallawayDAOImplIntegrationTest.class));
    }

    @Autowired(required = true)
    @Qualifier(value = "callawayDao")
    private CallawayDao callawayDao;


    @SuppressWarnings("unused")
    @Autowired(required = true)
    private JpaTransactionManager txManager;

    @Autowired(required = true)
    EntityManagerFactory entityManagerFactory;

    @SuppressWarnings("unused")
    @Autowired(required = true)
    private DataSource datasource;

    public void setCallawayDao(CallawayDao callawayDao) {
        this.callawayDao = callawayDao;
    }

    @Before
    public void onSetUpInTransaction() throws Exception {
    }

    public CallawayDao getCallawayDao() {
        return callawayDao;
    }

    @Test
    public void testFindUser() {
        User u = new User();
        u.city = "dublin";
        String email = "good@gooddomain.com";
        u.email = email;
        callawayDao.save(u);
        String findUser = callawayDao.findUser(email);
        assertNotNull("cant find user", findUser);
        u.city = "warwick";
        callawayDao.save(u);
        String findUser2 = callawayDao.findUser(email);
        assertNotNull("cant find user", findUser2);
    }

    @Test
    public void testGetTopScoresForUser() {
        User u = new User();
        u.city = "dublin";
        String email = "good@gooddomain.com";
        u.email = email;
        String u_pk = callawayDao.save(u);

        ScoreSubmission ss = new ScoreSubmission();
        ss.level = 12;
        ss.value = 333l;
        callawayDao.saveScore(u_pk, ss, "127.0.0.1");

        ss = new ScoreSubmission();
        ss.level = 12;
        ss.value = 334l;
        callawayDao.saveScore(u_pk, ss, "127.0.0.1");
        Score[] topScoresForUser = callawayDao.getTopScoresForUser(u_pk);
        assertTrue(topScoresForUser.length > 0);
        assertTrue(topScoresForUser[0].score == 334l);
    }

    @Test
    public void testGetUser() {
    }

    @Test

    public void testListTopScores() {
        User u = new User();
        u.city = "dublin";
        u.email = "good@gooddomain.com";
        String u_pk = callawayDao.save(u);
        ScoreSubmission ss = new ScoreSubmission();
        ss.level = 12;
        ss.value = 333l;
        callawayDao.saveScore(u_pk, ss, "127.0.0.1");
        ss = new ScoreSubmission();
        ss.level = 12;
        ss.value = 334l;
        callawayDao.saveScore(u_pk, ss, "127.0.0.1");
        u = new User();
        u.city = "dublin";
        u.email = "good1@gooddomain.com";
        u_pk = callawayDao.save(u);
        ss = new ScoreSubmission();
        ss.level = 12;
        ss.value = 333l;
        callawayDao.saveScore(u_pk, ss, "127.0.0.1");
        ss = new ScoreSubmission();
        ss.level = 12;
        ss.value = 334l;
        callawayDao.saveScore(u_pk, ss, "127.0.0.1");
        ScoreBoard board = callawayDao.listTopScores(3);
        assertTrue("scoreboard should be 3 long", board.rows.size() == 3);
    }

    @Test
    public void testSaveUser() {
    }

    @Test
    public void testSaveInvite() {
    }
}