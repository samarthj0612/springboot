package com.sj.springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionalService {

    @Autowired
    private OuterService outerService;

    @Transactional(propagation = Propagation.REQUIRED)
    public void requiredMethod() {
        // This method will join an existing transaction or create a new one if none exists
        System.out.println("TransactionalService | requireMethod called!");
        outerService.outerMethod();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void requiresNewMethod() {
        // This method will always start a new transaction
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void supportsMethod() {
        // This method will join an existing transaction if one exists, otherwise execute non-transactionally
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void notSupportedMethod() {
        // This method will always execute non-transactionally
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void mandatoryMethod() {
        // This method requires an existing transaction
    }

    @Transactional(propagation = Propagation.NEVER)
    public void neverMethod() {
        // This method must execute non-transactionally
    }

    @Transactional(propagation = Propagation.NESTED)
    public void nestedMethod() {
        // This method will start a nested transaction if an existing transaction is present
    }
}

