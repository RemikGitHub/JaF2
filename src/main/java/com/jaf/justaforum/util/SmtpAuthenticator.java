package com.jaf.justaforum.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SmtpAuthenticator extends Authenticator {
    
	 //konstruktor klasy SmtpAuthenticator
	 public SmtpAuthenticator() {
        super();
    }

    //zwraca obiekt klasy PasswordAuthentication o podanych danych logowania do skrzynki
    @Override
    public PasswordAuthentication getPasswordAuthentication() {
        String username = "justaforummail@gmail.com";
        String password = "zaq1@WSXX";

        return new PasswordAuthentication(username, password);

    }
}
