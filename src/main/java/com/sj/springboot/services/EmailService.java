package com.sj.springboot.services;

import com.sj.springboot.dto.Mail;

public interface EmailService {
    void sendMail(Mail mail);
}
