package com.callaway.service;

import com.callaway.dto.Invite;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.util.Assert;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ScheduledServiceImpl extends QuartzJobBean implements InitializingBean {

    private CallawayMailer mailer;
    private CallawayDao dao;
    private String recsRecipient;
    private String recsSubject;
    private String signsRecipient;
    private String signsSubject;
    private String mailHost;
    private String from;

     private static final Logger logger = Logger
            .getLogger(ScheduledServiceImpl.class);



    public void doSendRecommendationsMail() {
        logger.info("sending recommendations");
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(getMailHost());
        MimeMessage message = sender.createMimeMessage();
        // use the true flag to indicate you need a multipart message
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(recsRecipient);
            helper.setSubject(recsSubject);
            helper.setText(recsSubject);
            //This should be sending the csv file dumpted into temp directory.
            FileSystemResource file = new FileSystemResource(
                    dao.generateRecentRecommendationsReport()
            );
            helper.addAttachment("recommendations.csv", file);
            sender.send(message);
            file.getFile().delete();

        } catch (MessagingException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    public void doSendSubscribersMail() {
        logger.info("sending subscriptions");
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(getMailHost());
        MimeMessage message = sender.createMimeMessage();
        // use the true flag to indicate you need a multipart message
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setSubject(signsSubject);
            helper.setTo(signsRecipient);
            helper.setText(signsSubject);
            helper.setFrom(from);
            //This should be sending the csv file dumpted into temp directory.
            FileSystemResource file = new FileSystemResource(
                    dao.generateRecentSubscribersReport()
            );
            helper.addAttachment("subscribers.csv", file);
            sender.send(message);
        } catch (MessagingException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    public CallawayMailer getMailer() {
        return mailer;
    }

    public void setMailer(CallawayMailer mailer) {
        this.mailer = mailer;
    }


    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //Don't do anything, for now....
    }

    public void setDao(CallawayDao dao) {
        this.dao = dao;
    }

    public CallawayDao getDao() {
        return dao;
    }

    public void setRecsRecipient(String recsRecipient) {
        this.recsRecipient = recsRecipient;
    }

    public void setRecsSubject(String recsSubject) {
        this.recsSubject = recsSubject;
    }

    public void setSignsRecipient(String signsRecipient) {
        this.signsRecipient = signsRecipient;
    }

    public void setSignsSubject(String signsSubject) {
        this.signsSubject = signsSubject;
    }

    public void setMailHost(String mailHost) {
        this.mailHost = mailHost;
    }

    public void afterPropertiesSet() throws Exception {
        Assert.notNull(dao, "dao");
        Assert.notNull(getRecsRecipient(), "recsRecipient");
        Assert.notNull(getRecsSubject(), "recsSubject");
        Assert.notNull(getSignsRecipient(), "signsRecipient");
        Assert.notNull(getSignsSubject(), "signsSubject");
        Assert.notNull(getMailHost(), "mailHost");
    }

    public String getRecsRecipient() {
        return recsRecipient;
    }

    public String getRecsSubject() {
        return recsSubject;
    }

    public String getSignsRecipient() {
        return signsRecipient;
    }

    public String getSignsSubject() {
        return signsSubject;
    }

    public String getMailHost() {
        return mailHost;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}