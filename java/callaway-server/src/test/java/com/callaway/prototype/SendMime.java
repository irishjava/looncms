package com.callaway.prototype;
/*
 * Copyright (c) Ian F. Darwin, http://www.darwinsys.com/, 1996-2002.
 * All rights reserved. Software written by Ian F. Darwin and others.
 * $Id: LICENSE,v 1.8 2004/02/09 03:33:38 ian Exp $
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS ``AS IS''
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS
 * BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * Java, the Duke mascot, and all variants of Sun's Java "steaming coffee
 * cup" logo are trademarks of Sun Microsystems. Sun's, and James Gosling's,
 * pioneering role in inventing and promulgating (and standardizing) the Java
 * language and environment is gratefully acknowledged.
 *
 * The pioneering role of Dennis Ritchie and Bjarne Stroustrup, of AT&T, for
 * inventing predecessor languages C and C++ is also gratefully acknowledged.
 */

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.Properties;

/**
 * SendMime -- send a multi-part MIME email message.
 *
 * @author Ian F. Darwin
 * @version $Id: SendMime.java,v 1.8 2003/05/31 21:18:35 ian Exp $
 */
public class SendMime {

    /**
     * The message recipient.
     */
    protected String message_recip = "bryan@amp-london.com";

    /* What's it all about, Alfie? */
    protected String message_subject = "Re: your mail";

    /**
     * The message CC recipient.
     */
    protected String message_cc = "bryan@amp-london.com";

    /**
     * The text/plain message body
     */
    protected String message_body = "I am unable to attend to your message, as I am busy sunning "
            + "myself on the beach in Maui, where it is warm and peaceful. "
            + "Perhaps when I return I'll get around to reading your mail. "
            + "Or perhaps not.";

    /* The text/html data. */
    protected String html_data = "<HTML><HEAD><TITLE>My Goodness</TITLE></HEAD>"
            + "<BODY><P>You <EM>do</EM> look a little "
            + "<font color=green>GREEN</FONT>"
            + "around the edges..."
            + "</BODY></HTML>";

    /**
     * The JavaMail session object
     */
    protected Session session;

    /**
     * The JavaMail message object
     */
    protected Message mesg;

    /**
     * Do the work: send the mail to the SMTP server.
     */
    public void doSend() throws IOException, MessagingException {
        // Create the Session object
        Properties properties = new Properties();
        properties.put("", "");
        properties.put("", "");
        session = Session.getDefaultInstance(properties);
        session.setDebug(true); // Verbose!

        try {
            // create a message
            mesg = new MimeMessage(session);

            // From Address - this should come from a Properties...
            mesg.setFrom(new InternetAddress("bryan@amp-london.com"));

            // TO Address
            InternetAddress toAddress = new InternetAddress(message_recip);
            mesg.addRecipient(Message.RecipientType.TO, toAddress);

            // CC Address
            InternetAddress ccAddress = new InternetAddress(message_cc);
            mesg.addRecipient(Message.RecipientType.CC, ccAddress);

            // The Subject
            mesg.setSubject(message_subject);

            // Now the message body.
            Multipart mp = new MimeMultipart();

            BodyPart textPart = new MimeBodyPart();
            textPart.setText(message_body); // sets type to "text/plain"

            BodyPart pixPart = new MimeBodyPart();
            pixPart.setContent(html_data, "text/html");

            // Collect the Parts into the MultiPart
            mp.addBodyPart(textPart);
            mp.addBodyPart(pixPart);

            // Put the MultiPart into the Message
            mesg.setContent(mp);

            // Finally, send the message!
            Transport.send(mesg);

        } catch (MessagingException ex) {
            System.err.println(ex);
            ex.printStackTrace(System.err);
        }
    }

    /**
     * Simple test case driver
     */
    public static void main(String[] av) throws Exception {
        SendMime sm = new SendMime();
        sm.doSend();
    }
}
