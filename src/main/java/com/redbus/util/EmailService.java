package com.redbus.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component //or @Service //--> object of this class will be managed by the SpringBoot
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;


    //to Send an Email  without an Attachment
    public void sendEmail(String to, String subject, String body) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        javaMailSender.send(message);
    }

    //Attach the PDF to the email
    public void sendEmailWithAttachment(String to , String subject , String body , byte[] attachment , String attachmentFileName){ //5 fields

        try {

            MimeMessage message = javaMailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message  , true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body);

            //attach the PDF to the mail
            helper.addAttachment(attachmentFileName , new ByteArrayResource(attachment));//////////////////******

            javaMailSender.send(message);

        }catch (MessagingException e){
            e.printStackTrace();
        }
    }

}
