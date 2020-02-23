package com.netcrackerpractice.startup_social_network.service.impl;

import com.netcrackerpractice.startup_social_network.entity.User;
import com.netcrackerpractice.startup_social_network.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public SimpleMailMessage constructResetTokenEmail(String token, User user) {
        String url = "http://localhost:4200/changePassword?id=" +
                user.getId() + "&token=" + token;
        String message = "To reset your password, click this link: \n";
        return constructEmail("Reset Password", message + " \r\n" + url, user);
    }

    @Override
    public SimpleMailMessage constructVerificationTokenEmail(String token, User user) {
        String url = "http://localhost:4200/verifyEmail?token=" + token;
        String message = "To complete your account set-up, click this link: \n";
        return constructEmail("Verify Email", message + " \r\n" + url, user);
    }

    @Override
    public SimpleMailMessage constructEmail(String subject, String body,
                                             User user) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(user.getEmail());
        email.setFrom("startupnetworknc@gmail.com");
        javaMailSender.send(email);
        return email;
    }

}
