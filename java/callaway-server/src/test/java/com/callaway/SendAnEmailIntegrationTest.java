package com.callaway;

import com.callaway.dto.Invite;
import com.callaway.service.CallawayMailerImpl;
import junit.framework.JUnit4TestAdapter;
import org.apache.log4j.Logger;
import org.junit.*;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class SendAnEmailIntegrationTest {

    private static final Logger logger = Logger
            .getLogger(SendAnEmailIntegrationTest.class);

    private static CallawayMailerImpl callawayMailer;

    private static JavaMailSender mailSender;


    private static SimpleMailMessage templateMessage;
    private static VelocityEngineFactoryBean velocityEngine;


    public static void main(String[] args) {
        junit.textui.TestRunner.run(new JUnit4TestAdapter(
                SendAnEmailIntegrationTest.class));
    }

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        Map<String, String> props = new HashMap<String, String>();
        props.put("resource.loader", "class");
        props.put("class.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader"
        );

        velocityEngine = new VelocityEngineFactoryBean();
        //velocityEngine.setResourceLoaderPath("classpath:/");
        velocityEngine.setVelocityPropertiesMap(props);

        mailSender = new JavaMailSenderImpl();
        ((JavaMailSenderImpl) mailSender).setHost("127.0.0.1");
        ((JavaMailSenderImpl) mailSender).setPort(25);

        templateMessage = new SimpleMailMessage();
        templateMessage.setFrom("bryan");
        templateMessage.setSubject("ebookers status change");

        callawayMailer = new CallawayMailerImpl();
        callawayMailer.setMailSender(mailSender);
        callawayMailer.setTemplateMessage(templateMessage);
        callawayMailer.setVelocityEngine(velocityEngine.createVelocityEngine());
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testMailer() {
        //for (int i = 0; i < 1000; i++) {
        Invite inv = new Invite();
        inv.senderEmail = "bryan@localhost";
        inv.senderName = "Bryan";        
        inv.recipients = new String[]{"bryan@localhost"};
        inv.message = "this email is testing ebookers";
        callawayMailer.invite(inv);
        //}
    }
}