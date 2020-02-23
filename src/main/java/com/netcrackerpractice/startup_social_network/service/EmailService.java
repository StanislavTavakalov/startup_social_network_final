package com.netcrackerpractice.startup_social_network.service;

import com.netcrackerpractice.startup_social_network.entity.User;
import org.springframework.mail.SimpleMailMessage;


public interface EmailService {
    SimpleMailMessage constructResetTokenEmail(String token, User user);
    SimpleMailMessage constructVerificationTokenEmail(String token, User user);
    SimpleMailMessage constructEmail(String subject, String body, User user);
}
