package com.callaway.service;

import com.callaway.dto.Invite;
import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.util.Assert;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class CallawayMailerImpl implements CallawayMailer, InitializingBean {

    private JavaMailSender mailSender;
    private SimpleMailMessage templateMessage;
    private VelocityEngine velocityEngine;

    private static final Logger logger = Logger
            .getLogger(CallawayMailerImpl.class);
    
    public void invite(final Invite i) {
	logger.info("sending an invite");
        for(final String recipient : i.recipients){
            MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(recipient);
                message.setReplyTo(i.senderEmail);
                message.setFrom("CallawayGolf@contact.callawaygolf.com");
                message.setSubject(i.senderName + " has challenged you to Callaway's Demon Drives game");
                Map model = new HashMap();
                model.put("senderName", i.senderName);
                String text = VelocityEngineUtils.mergeTemplateIntoString(
                        getVelocityEngine(), "registration-confirmation.vm", model);
                message.setText(text, true);
            }
        };
        this.getMailSender().send(preparator);
        }
    }



    public SimpleMailMessage getTemplateMessage() {
        return templateMessage;
    }

    public void setTemplateMessage(SimpleMailMessage templateMessage) {
        this.templateMessage = templateMessage;
    }

    public void afterPropertiesSet() throws Exception {
        Assert.notNull(this.getMailSender());
        Assert.notNull(this.getTemplateMessage());
        Assert.notNull(this.getVelocityEngine());
    }

    public VelocityEngine getVelocityEngine() {
        return velocityEngine;
    }

    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }

    public JavaMailSender getMailSender() {
        return mailSender;
    }

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
}
