package com.jaf.justaforum.util;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailUtil {

    //konfiguruje skrzynke po czym wysyła emaila z tokenem do potwierdzenia rejestracji
    public static void sendActivationEmail(String mailRecipient, String token) {

        String from = "justaforummail@gmail.com";
        final String username = "justaforummail@gmail.com";
        final String password = "zaq1@WSXX";
        String link = "<a href=\"http://localhost:8080/confirm?token=" + token + "\"> Click here to active</a>";

        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", username);
        props.put("mail.smtp.password", password);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        try {
            SmtpAuthenticator authentication = new SmtpAuthenticator();
            javax.mail.Message message = new MimeMessage(Session.getDefaultInstance(props, authentication));

            //set From email field
            message.setFrom(new InternetAddress(from));

            //set To email field
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailRecipient));

            //set email subject field
            message.setSubject("Confirm your account");

            //set the content of the email message
            message.setContent("<h2>JaF - Just a Forum</h2><p>If you want to activate your account, click on the link. " + link + "</p>", "text/html");

            //send the email message
            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}