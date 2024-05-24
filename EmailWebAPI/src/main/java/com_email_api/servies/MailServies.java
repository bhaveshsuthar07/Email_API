package com_email_api.servies;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;

@Service
@PropertySource("classpath:mail.properties")
public class MailServies {

    @Autowired
    private Environment env;


    public boolean mail_Text(String to,String subject,String message){
        boolean flag = false;

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","465");
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth","true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(env.getProperty("EMAIL"),env.getProperty("PASSWORD"));
            }
        });

        session.getDebug();

        MimeMessage mimeMessage = new MimeMessage(session);

        try {
            mimeMessage.setFrom(env.getProperty("SEND_FROM"));
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(message);

            Transport.send(mimeMessage);

            System.out.println("Email has been send successfully.....");
            flag= true;

        }catch (Exception e){
            e.printStackTrace();
        }

        return flag;
    }

    public boolean  mail_Multipart(String to,String subject,String message,File file){
        boolean flag= false;

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","465");
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth","true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(env.getProperty("EMAIL"),env.getProperty("PASSWORD"));
            }
        });

        session.getDebug();

        MimeMessage mimeMessage = new MimeMessage(session);

        try {
            mimeMessage.setFrom(env.getProperty("SEND_FROM"));
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(message);


            MimeMultipart mimeMultipart = new MimeMultipart();

            MimeBodyPart textBodyPart= new MimeBodyPart();

            MimeBodyPart fileBodyPart= new MimeBodyPart();

            try {

                textBodyPart.setText(message);

                fileBodyPart.attachFile(String.valueOf(file));

                mimeMultipart.addBodyPart(textBodyPart);
                mimeMultipart.addBodyPart(fileBodyPart);

            }catch (Exception e){
                e.printStackTrace();
            }

            mimeMessage.setContent(mimeMultipart);

            Transport.send(mimeMessage);

            System.out.println("Email has been send successfully.....");
            flag= true;

        }catch (Exception e){
            e.printStackTrace();
        }

        return flag;
    }

}
