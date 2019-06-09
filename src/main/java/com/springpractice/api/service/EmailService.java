package com.springpractice.api.service;

public interface EmailService {
    public void sendSimpleEmail(String to, String subject, String message);
}
