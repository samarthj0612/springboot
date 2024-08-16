package com.sj.springboot.dto;

public class Mail {
    private String recipientsAddress;
    private String subject;
    private String content;

    public Mail() {
    }

    public Mail(String recipientsAddress, String subject, String content) {
        this.recipientsAddress = recipientsAddress;
        this.subject = subject;
        this.content = content;
    }

    public String getRecipientsAddress() {
        return recipientsAddress;
    }

    public void setRecipientsAddress(String recipientsAddress) {
        this.recipientsAddress = recipientsAddress;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "recipientsAddress='" + recipientsAddress + '\'' +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
