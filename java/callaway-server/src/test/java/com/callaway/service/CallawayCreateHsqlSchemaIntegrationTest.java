package com.callaway.service;

import com.callaway.domain.User;
import com.callaway.dto.ScoreSubmission;
import junit.framework.JUnit4TestAdapter;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = false, transactionManager = "txManager")
@Transactional
@ContextConfiguration(locations = {"classpath:hsql-datasource-context.xml",
"classpath:application-context.xml", "classpath:common-application-context.xml"})
public class CallawayCreateHsqlSchemaIntegrationTest {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(new JUnit4TestAdapter(
                CallawayCreateHsqlSchemaIntegrationTest.class));
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
    public void enterSomeData() {
        for (int i = 0; i < 5; i++) {
            //RandomStringUtils.random(20)
            User u = new User("eemdsadfas" + i);
            callawayDao.save(u);
            for (int j = 0; j < 5; j++) {
                ScoreSubmission ss = new ScoreSubmission();
                ss.level = 1 + RandomUtils.nextInt(15);
                ss.value = (long) RandomUtils.nextInt(1000);
                callawayDao.saveScore(u.pk, ss, "127.0.0.1");
            }
        }
    }
}