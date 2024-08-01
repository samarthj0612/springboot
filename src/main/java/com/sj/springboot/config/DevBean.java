package com.sj.springboot.config;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DevBean {
    // Development-specific logic
    public DevBean() {
        System.out.println("DevBean is initialized");
    }
}
