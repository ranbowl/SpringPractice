package com.springpractice.api.controller;

import com.springpractice.api.model.email.EmailRequest;
import com.springpractice.api.service.EmailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    private static final Logger logger = LogManager.getLogger(EmailController.class);

    @PostMapping("/")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest emailRequest) {
        logger.info("Sending email -> {} to -> {}", emailRequest.getSubject(), emailRequest.getTo());
        emailService.sendSimpleEmail(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getContext());
        return ResponseEntity.ok(new StringBuilder().append("Email subject ").append(emailRequest.getSubject()).append(" sent to ")
        .append(emailRequest.getTo()).toString());
    }
}
