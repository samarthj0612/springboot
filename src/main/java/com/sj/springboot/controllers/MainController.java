package com.sj.springboot.controllers;

import com.sj.springboot.dto.Mail;
import com.sj.springboot.dto.ApiResponse;
import com.sj.springboot.services.EmailService;
import com.sj.springboot.services.TransactionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    TransactionalService transactionalService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/")
    public String root(){
        System.out.println("ROOT API CALLED");
        this.transactionalService.requiredMethod();
        return "Server is successfully connected";
    }

    @PostMapping("/send-mail")
    public ApiResponse<Mail> sendMail(@RequestBody Mail mail) {
        System.out.println("SEND MAIL API CALLED");

        try {
            if (mail == null || mail.getRecipientsAddress() == null || mail.getRecipientsAddress().isEmpty()) {
                return new ApiResponse<Mail>(400, "Mail data is missing or recipient address is empty", null);
            }

            emailService.sendMail(mail);

            return new ApiResponse<Mail>(200, "Mail successfully sent", mail);
        } catch (Exception e) {
            return new ApiResponse<Mail>(500, "Internal server error", null);
        }
    }
}
