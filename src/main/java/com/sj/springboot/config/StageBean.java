package com.sj.springboot.config;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("stage")
public class StageBean {
    // Staging-specific logic
    public StageBean() {
        System.out.println("StageBean is initialized");
    }
}
