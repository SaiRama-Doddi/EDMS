package com.edms.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendStationMasterCredentials(String toEmail, String password) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Your Station Master Account Credentials");
        message.setText("Hello,\n\nYour Station Master account has been created.\n\n"
                + "Email: " + toEmail + "\n"
                + "Password: " + password + "\n\n"
                + "Please change your password after login.\n\nThank you!");

        mailSender.send(message);
    }
    public void sendDraftsmanCredentials(String toEmail, String password) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Your Draftsman Master Account Credentials");
        message.setText("Hello,\n\nYour Draftsman account has been created.\n\n"
                + "Email: " + toEmail + "\n"
                + "Password: " + password + "\n\n"
                + "Please change your password after login.\n\nThank you!");

        mailSender.send(message);
    }
}
