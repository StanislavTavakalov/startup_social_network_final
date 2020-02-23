package com.netcrackerpractice.startup_social_network.service;

import com.netcrackerpractice.startup_social_network.entity.User;
import com.netcrackerpractice.startup_social_network.payload.ResetPasswordRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    List<User> findAll();

    User saveUser(User user);

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByLoginOrEmail(String login, String email);

    Optional<User> findUserById(UUID userId);

    Optional<User> findUserByLogin(String login);
    String createTokenForUser(User user);
    String validatePasswordResetToken(UUID id, String token);
    ResponseEntity<?> updatePassword(ResetPasswordRequest resetPasswordRequest);
    ResponseEntity<?> verifyUserEmail(String token);

    void blockUser(UUID id);
    void unBlockUser(UUID id);
}
