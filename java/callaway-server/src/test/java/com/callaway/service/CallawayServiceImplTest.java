package com.callaway.service;

import com.callaway.domain.Score;
import com.callaway.dto.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.security.MessageDigest;

import static org.junit.Assert.*;

public class CallawayServiceImplTest {

    static CallawayServiceImpl service;
    static MessageDigest digest;
    static CallawaySecurity security;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        service = new CallawayServiceImpl();
        service.setDao(new DummyCallawayDAO());
        
        service.setMailer(new DummyCallawayMailer());
        digest = java.security.MessageDigest.getInstance("md5");
        service.setSession(new DummySessionHolder());
        service.setSecurity(new CallawaySecurityImpl());
    }

    @Before
    public void setUp() throws Exception {
        // Setup credentials..
        Credentials c = Credentials.generate(service.nonce(), "8roon5999eoe");
        // Get the initialization result..
        InitializationResult initialize = service.initialize(c);
        assertTrue("Initialization succeeded", initialize.success);
        // Now the service should be in an ok state..
        Login l = new Login();
        l.email = "goodaccount@asfd.com";
        service.login(l);
    }

    /**
     * <b>User Login Use Case</b>
     * <ol>
     * <li>
     * The User transmits a Login object to the service.</li>
     * <li>
     * The service checks if his email address exists.</li>
     * <li>If it exists
     * <ol>
     * <li>
     * An authentication token is created in the Server Side Session and a Token
     * is returned indicating success.</li>
     * </ol>
     * </li>
     * <li>
     * If it does not exist
     * <ol>
     * <li>The Authentication Token is not placed in the Server Session.</li>
     * <li>A Token is returned indicating failure.</li>a
     * </ol>
     * </li> </ul>
     * </ol>
     */
    @Test
    public void testLogin() {
        Login l = new Login("goodaccount@asfd.com");
        LoginResult r1 = service.login(l);
        assertTrue(r1.success);

        Login l2 = new Login("badaccount@asfd.com");
        LoginResult r2 = service.login(l2);
        assertFalse(r2.success);
    }

    /**
     * <i>Test Initialize Use Case</i>
     * <ol>
     * <li>Client requests daily nonce.</li>
     * <li>Client concatenates the secret token and the nonce, and md5's the
     * result.</li>
     * <li>Client transmits the result to the server.</li>
     * </ol>
     */
    @Test
    public void testInitialize() {
        //
        String dailyNonce = service.nonce();
        Credentials c = Credentials.generate(dailyNonce, "secret");
        InitializationResult initialize = service.initialize(c);
        assertTrue(initialize.success);
        //
        dailyNonce = service.nonce();
        c = Credentials.generate(dailyNonce, "pwibble");
        initialize = service.initialize(c);
        assertFalse(initialize.success);
    }

    /**
     * <i>Test Invitation Use Case</i>
     * <ol>
     * <li>The Client has presented it's credentials</li>
     * <li>An Invite object is sent to the Server</li>
     * <li>The Server enters the Invite into the database</li>
     * <li>The Server sends emails to the recipients</li>
     * <li>The Server sends an Invitation Email to the Recipients</li>
     * </ol>
     */
    @Test
    public void testInvite() {
        Invite i = new Invite();
        i.senderEmail = "ted@amp-london.com";
        i.senderName = "Ted";
        i.recipients = new String[]{"a@a.com", "b@b.com", "c@c.com"};
        i.message = "dafdasds";
        InviteResult invite = service.invite(i);
        assertTrue(invite.success);
    }

    /**
     * <i>Test List My Scores Use Case</i>
     * <ol>
     * <li>The Client has presented it's credentials</li>
     * <li>The User is Logged in.</li>
     * <li>The User calls, listMyScores.</li>
     * <li>The Server populates an array of Score objects, each containing a
     * level and value. Only the highest value (per level). Is returned (or
     * stored).</li>
     * </ol>
     */
    @Test
    public void testMyScores() {
        Score[] scores = service.listMyScores();
        for (Score score : scores) {
            if (score.level == 1 && score.score > 1) {
                return;
            }
        }
        fail("did not find my score");
    }

    /**
     * <i>Test List Top Scores</i>
     * <ol>
     * <li>The Client has presented it's credentials</li>
     * <li>User does not need to be Logged in.</li>
     * <li>User requests a listing of top scores</li>
     * <li>A listing of top scores is returned to the client</li>
     * </ol>
     */
    @Test
    public void testListTopScores() {
        ScoreBoard listTopScores = service.listTopScores();
        assertNotNull(listTopScores);
    }

    /**
     * <i>Submit a Personal Scores</i>
     * <ol>
     * <li>The Client has presented it's credentials</li>
     * <li>User is Logged in.</li>
     * <li>User completes a Level of the Game.</li>
     * <li>A ScoreSubmission object is transmitted from the Client to the
     * Server, it contains the value (score) and level in which it was attained.
     * </li>
     * <li>If the score is (per that level) the highest which the user has thus
     * far attained, then it is stored, otherwise it is discarded.</li>
     * </ol>
     */

    @Test
    public void testSubmit() {
        ScoreSubmission s = new ScoreSubmission();
        s.level = 1;
        s.value = 1l;
        service.submit(s);
    }

    /**
     * <i>Subscribe to the service</i>
     * <ol>
     * <li>The Client has presented it's credentials</li>
     * <li>The User fills out the Subscription form</li>
     * <li>A Subscribe object is transmitted to the Server.</li>
     * <li>The Subscribe object is copied into a User object and persisted.</li>
     * <li>Now the User may Login.</li>
     * <p/>
     * </ol>
     */
    @Test
    public void testSubscribe() {
		SubscriptionRequest s = new SubscriptionRequest();
		s.optIn = true;
		service.subscribe(s);
	}
} 
