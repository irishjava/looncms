package com.callaway.service;

import junit.framework.JUnit4TestAdapter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:common-application-context.xml",
        "classpath:hsql-datasource-context.xml", "classpath:application-context.xml",
        "classpath:scheduled-tasks.xml"
})
public class SchedulingIntegrationTest {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(new JUnit4TestAdapter(
                SchedulingIntegrationTest.class));
    }

    @Test
    public void bootTheContext() {
    }
}