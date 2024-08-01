package com.sj.springboot.config;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class ProdBean {
    // Production-specific logic
    public ProdBean() {
        System.out.println("ProdBean is initialized");
    }
}
