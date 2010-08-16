package com.callaway.service;

import EDU.oswego.cs.dl.util.concurrent.ThreadedExecutor;
import com.callaway.domain.Recommendation;
import com.callaway.domain.Score;
import com.callaway.domain.User;
import com.callaway.dto.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import static org.springframework.util.Assert.notNull;

public class CallawayServiceImpl implements CallawayService {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger
            .getLogger(CallawayServiceImpl.class);

    private CallawayDao dao;
    private SessionHolder session;
    private CallawayMailer mailer;
    private CallawaySecurity security;

    public CallawayServiceImpl() {
    }

    public String nonce() {
        return security.nonce();
    }

    public InitializationResult initialize(Credentials c) {
        if ("".equals(c.getToken()) || c.getToken() == null) {
            return new InitializationResult(
                    "null or empty token sent to service");
        }
        if (!(c.getToken().equals(DigestUtils.md5Hex(nonce()
                + security.clientSecret())))) {
            return new InitializationResult("security error, logged...");
        }
        getSession().setInitialized(true);
        return new InitializationResult();
    }

    public InviteResult invite(final Invite i) {
        logger.info(i);
        for (String recipient : i.recipients) {
                    Recommendation r = new Recommendation();
            r.remoteIp = session.getIp();
                    r.senderEmail = i.senderEmail;
                    r.senderName = i.senderName;
                    
                    r.recipientEmail = recipient;
                    dao.save(r);
                }
       try {
            new ThreadedExecutor().execute(new Runnable() {
                    public void run() {
                        mailer.invite(i);
                    }
                });
        } catch (InterruptedException e) {
            logger.error(e);
        }
        return InviteResult.success();
    }

    public Score[] listMyScores() {
        try {
            String user = getSession().getUser();
            notNull(user, "listMyScores:session must contain a user");
            return getDao().getTopScoresForUser(user);
        } catch (Throwable t) {
            logger.error(t);
            return new Score[]{};
        }
    }

    public ScoreBoard listTopScores() {
        return getDao().listTopScores(25);
    }

    public LoginResult login(Login l) {
        String u = dao.findUser(l.email);
        if (u == null) {
            return LoginResult.failure();
        }
        session.setUser(u);
        return LoginResult.success();
    }

    public ScoreSubmissionResult submit(ScoreSubmission ss) {
        String user = session.getUser();
        if(user == null){
            return ScoreSubmissionResult.failure("ERROR:User must be logged in");
        }
        dao.saveScore(user, ss, session.getIp());
        return ScoreSubmissionResult.success();
    }

    public SubscriptionResult subscribe(SubscriptionRequest s) {
        User u = new User(s);
        u.remoteIp = session.getIp();
        String ret = dao.save(u);
        session.setUser(ret);
        return SubscriptionResult.success();
    }

    public CallawayDao getDao() {
        return dao;
    }

    public void setDao(CallawayDao dao) {
        this.dao = dao;
    }

    public SessionHolder getSession() {
        return session;
    }

    public void setSession(SessionHolder session) {
        this.session = session;
    }

    public CallawayMailer getMailer() {
        return mailer;
    }

    public void setMailer(CallawayMailer mailer) {
        this.mailer = mailer;
    }

    public CallawaySecurity getSecurity() {
        return security;
    }

    public void setSecurity(CallawaySecurity security) {
        this.security = security;
    }

   
}
