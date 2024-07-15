package com.sj.springboot.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Main {
    @GetMapping
    public static void main(){
        System.out.println("It's working");
    }
}
