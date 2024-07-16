package com.sj.springboot.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InnerService {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void innerMethod() {
        // This method will run in a new transaction
        System.out.println("InnerService | innerMethod called!");
    }
}
