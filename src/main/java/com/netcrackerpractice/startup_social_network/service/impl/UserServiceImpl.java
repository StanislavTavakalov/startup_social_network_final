package com.netcrackerpractice.startup_social_network.service.impl;

import com.netcrackerpractice.startup_social_network.entity.User;
import com.netcrackerpractice.startup_social_network.entity.UserToken;
import com.netcrackerpractice.startup_social_network.payload.ApiResponse;
import com.netcrackerpractice.startup_social_network.payload.ResetPasswordRequest;
import com.netcrackerpractice.startup_social_network.repository.TokenRepository;
import com.netcrackerpractice.startup_social_network.repository.UserRepository;
import com.netcrackerpractice.startup_social_network.service.AuthService;
import com.netcrackerpractice.startup_social_network.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private AuthService authService;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findUserByLoginOrEmail(String username, String email) {
        return userRepository.findByLoginOrEmail(username, email);
    }

    @Override
    public Optional<User> findUserById(UUID userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> findUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public String createTokenForUser(User user){
        String token = UUID.randomUUID().toString();
        UserToken myToken = new UserToken(user, token);
        tokenRepository.save(myToken);
        return token;
    }

    @Override
    public String validatePasswordResetToken(UUID id, String token){
        System.out.println(token);

        UserToken passUserToken = tokenRepository.findByToken(token).get();
        System.out.println(passUserToken.getUser().getId());
        System.out.println(id);
        if (!id.equals(passUserToken.getUser().getId())) {
            return "invalidToken";
        }
        Calendar cal = Calendar.getInstance();
        System.out.println(passUserToken.getExpiryDate()
                .getTime() - cal.getTime()
                .getTime());
        if ((passUserToken.getExpiryDate()
                .getTime() - cal.getTime()
                .getTime()) <= 0) {
            return "expired";
        }
        return null;
    }

    @Override
    public ResponseEntity<?> updatePassword(ResetPasswordRequest resetPasswordRequest) {
        String result = validatePasswordResetToken(resetPasswordRequest.getId(), resetPasswordRequest.getToken());
        System.out.println(result);
        if (result == null) {
            User user= userRepository.findById(resetPasswordRequest.getId()).get();
            user.setHashedPassword(passwordEncoder.encode(resetPasswordRequest.getPassword()));
            userRepository.save(user);
            tokenRepository.delete(tokenRepository.findByToken(resetPasswordRequest.getToken()).get());
            return authService.authenticateUser(user.getLogin(),resetPasswordRequest.getPassword());
        }
        return new ResponseEntity(new ApiResponse(false, "Invalid token"),
                HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<?> verifyUserEmail(String token) {
        UserToken verificationUserToken =tokenRepository.findByToken(token).get();
        Calendar cal = Calendar.getInstance();
        if ((verificationUserToken.getExpiryDate()
                .getTime() - cal.getTime()
                .getTime()) <= 0) {
            return new ResponseEntity(new ApiResponse(false, "Token expired"),
                    HttpStatus.BAD_REQUEST);
        }
        User user= verificationUserToken.getUser();
        user.setEnabled(true);
        tokenRepository.deleteById(verificationUserToken.getId());
        saveUser(user);
        return ResponseEntity.ok().build();
    }

    @Override
    public void blockUser(UUID id) {
        userRepository.blockUser(id);
    }

    @Override
    public void unBlockUser(UUID id) {
        userRepository.unBlockUser(id);
    }
}
