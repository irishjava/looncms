package com.callaway.signoff;

import com.callaway.domain.Score;
import com.callaway.dto.*;
import com.callaway.service.CallawayService;
import com.caucho.hessian.client.HessianProxyFactory;
import junit.framework.JUnit4TestAdapter;
import org.apache.log4j.Logger;
import org.junit.*;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;


import static org.junit.Assert.*;

@SuppressWarnings("unused")
public class HttpClientIntegrationTest {

    private static final Logger logger = Logger
            .getLogger(HttpClientIntegrationTest.class);

    //private static String url = "http://offerbuilder.local:8080/callaway-0.0.1-SNAPSHOT/callaway";


//	 private static String url = "http://localhost:8666/callaway/callaway";
    //private static String url = "http://localhost:8666/callaway";
    private static String url = "http://www.demon-drive.com/callaway/callaway";


//	private static String url = "http://localhost:8666/callaway-0.0.1-SNAPSHOT/callaway";

    private static CallawayService callawayService;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(new JUnit4TestAdapter(
                HttpClientIntegrationTest.class));
    }

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));

        HessianProxyFactory factory = new HessianProxyFactory();
        callawayService = (CallawayService) factory.create(
                CallawayService.class, url);
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

    /**
     * String nonce();
     *
     * @return
     */
    @Test
    public void testNonce() {
        String nonce = callawayService.nonce();
        System.err.println(nonce);
        InitializationResult initialize = callawayService
                .initialize(Credentials.generate(nonce, "8roon5999eoe"));
        assertTrue("successful initialization", initialize.success);

        ScoreSubmissionResult scoreSubmissionResult = callawayService.submit(new ScoreSubmission(1, 1l));
        assertFalse(scoreSubmissionResult.success);

        LoginResult login = callawayService.login(new Login(
                "noaccount@emakmafu.com"));
        assertFalse("shouldn't get a login", login.success);

        SubscriptionRequest r = new SubscriptionRequest();
        r.email = "bryan@amp-london.com";
        r.city = "dublin";
        r.country = "Eire";
        r.dob = "09/11/2003";
        r.firstName = "john";
        r.lastName = "doe";
	r.optIn = true;

        SubscriptionResult subscribe = callawayService.subscribe(r);
        assertTrue("subscribing succeeded", subscribe.success);

        login = callawayService.login(new Login(
                "bryan@amp-london .com"));
        assertTrue("should get a login", login.success);

        Invite i = new Invite();
        i.senderName = "bryan";
        i.senderEmail = "bryan@amp-london.com";
        i.message = "supper offer news";
        i.recipients = new String[]{"bryan@amp-london.com"};
        InviteResult inviteResult = callawayService.invite(i);
        assertTrue(inviteResult.success);
        scoreSubmissionResult = callawayService.submit(new ScoreSubmission(1, 1l));
        assertTrue(scoreSubmissionResult.success);
        Score[] scores = callawayService.listMyScores();
    }


    @Test
    public void testScoreBoardSorting() {
        String nonce = callawayService.nonce();
        System.err.println(nonce);
        InitializationResult initialize = callawayService
                .initialize(Credentials.generate(nonce, "8roon5999eoe"));
        assertTrue("successful initialization", initialize.success);
        ScoreBoard board = callawayService.listTopScores();
        long max = 0;
        for(ScoreBoardRow r: board.rows){
            if(max == 0){
                max = r.score;
                continue;
            }
            if(!(r.score <=max)){
                fail("sorting should be in descending order");
            }
        }
    }
}
