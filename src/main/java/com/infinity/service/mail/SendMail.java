/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infinity.service.mail;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;

/**
 *
 * @author t311372
 */
@Service
public class SendMail {

    private Session session;
    private MimeMessage message;

    public void send() throws MessagingException {

        this.setProps();
        Transport.send(message);

    }

    private void setProps() throws AddressException, MessagingException {

        //ouverture d'une session. la session gére les informations de configuration (nom d'utilisateur, mot de passe, hôte) nécessaires pour utiliser les fonctionnalités de JavaMail
        Properties props = new Properties();

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.phpnet.org");
        props.put("mail.smtp.user", "contact@infinity-web.fr");
       
        props.put("mail.smtp.port", "465");

        props.setProperty("mail.from", "contact@infinity-web.fr"); // @ expediteur
      
         session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("contact@infinity-web.fr", "kanekane32");
                    }
                });

       

            message = new MimeMessage(session);
            message.setFrom(new InternetAddress("contact@infinity-web.fr"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("canicatti.nicolas@gmail.com"));
            message.setSubject("Testing Subject");
            message.setText("Dear Mail Crawler,"
                    + "\n\n No spam to my email, please!");

            Transport.send(message);

            System.out.println("Done");

       
    }

            //Transport
}
