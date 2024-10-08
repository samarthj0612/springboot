package com.sj.springboot.services.impl;

import com.sj.springboot.dto.Mail;
import com.sj.springboot.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    private final String DEFAULT_SENDER_MAIL_ADDRESS = "samarth.dev@gmail.com";

    @Override
    public void sendMail(Mail mail) {
        System.out.println("Email: " + mail.toString());

        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(mail.getRecipientsAddress());
            message.setSubject(mail.getSubject());
            message.setText(mail.getContent());
            message.setFrom(DEFAULT_SENDER_MAIL_ADDRESS);

            mailSender.send(message);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
