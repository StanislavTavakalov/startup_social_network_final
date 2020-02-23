package com.netcrackerpractice.startup_social_network.controller;


import com.netcrackerpractice.startup_social_network.entity.User;
import com.netcrackerpractice.startup_social_network.payload.ResetPasswordRequest;
import com.netcrackerpractice.startup_social_network.service.AccountService;
import com.netcrackerpractice.startup_social_network.service.AuthService;
import com.netcrackerpractice.startup_social_network.service.EmailService;
import com.netcrackerpractice.startup_social_network.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;

    @Autowired
    AccountService accountService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/user-list")
    public List<User> getStartupList() {
        return userService.findAll();
    }

    @RequestMapping(value = "/resetPassword",
            method = RequestMethod.POST)
    public ResponseEntity<?> resetPassword(@RequestParam("email") String userEmail){
        User user = userService.findUserByEmail(userEmail).get();
        String token=userService.createTokenForUser(user);
        emailService.constructResetTokenEmail(token, user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/savePassword")
    public ResponseEntity<?> updatePassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {
        System.out.println(resetPasswordRequest.getPassword());
        return userService.updatePassword(resetPasswordRequest);
    }

    @PostMapping("/verifyEmail")
    public ResponseEntity<?> confirmRegistration(@RequestParam("token") String token) {
        System.out.println(token);
        return userService.verifyUserEmail(token);
    }



}
