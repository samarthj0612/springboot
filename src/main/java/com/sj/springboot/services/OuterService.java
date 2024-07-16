package com.sj.springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OuterService {

    @Autowired
    private InnerService innerService;

    @Transactional(propagation = Propagation.REQUIRED)
    public void outerMethod() {
        // This method will join an existing transaction or create a new one if none exists
        System.out.println("OuterService | outerMethod called!");
        innerService.innerMethod();
    }
}
