package com.sj.springboot.controllers;

import com.sj.springboot.services.TransactionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    TransactionalService transactionalService;

    @GetMapping("/")
    public String root(){
        System.out.println("ROOT API CALLED");
        this.transactionalService.requiredMethod();
        return "Server is successfully connected";
    }
}
