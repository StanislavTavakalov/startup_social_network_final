package com.netcrackerpractice.startup_social_network.controller;

import com.netcrackerpractice.startup_social_network.dto.StartupDTO;
import com.netcrackerpractice.startup_social_network.entity.Startup;
import com.netcrackerpractice.startup_social_network.entity.User;
import com.netcrackerpractice.startup_social_network.mapper.StartupMapper;
import com.netcrackerpractice.startup_social_network.payload.ApiResponse;
import com.netcrackerpractice.startup_social_network.service.StartupService;
import com.netcrackerpractice.startup_social_network.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/admin")
public class AdminController {
    @Autowired
    StartupService startupService;

    @Autowired
    UserService userService;

    @Autowired
    StartupMapper startupMapper;

    @PostMapping("/block/startup")
    private ResponseEntity<?> blockStartup(@RequestBody String id) {
        try {
            UUID uuid = UUID.fromString(id);
            startupService.blockStartup(uuid);
            return new ResponseEntity<>(new ApiResponse(true, "Startup blocked"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(true, "FAIL block startup"), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/unblock/startup")
    private ResponseEntity<?> unBlockStartup(@RequestBody String id) {
        try {
            UUID uuid = UUID.fromString(id);
            startupService.unBlockStartup(uuid);
            return new ResponseEntity<>(new ApiResponse(true, "Startup unblocked"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(true, "FAIL unblock startup"), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/block/user")
    private ResponseEntity<?> blockUser(@RequestBody String id) {
        try {
            UUID uuid = UUID.fromString(id);
            userService.blockUser(uuid);
            return new ResponseEntity<>(new ApiResponse(true, "User blocked"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(true, "FAIL block user"), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/unblock/user")
    private ResponseEntity<?> unBlockUser(@RequestBody String id) {
        try {
            UUID uuid = UUID.fromString(id);
            userService.unBlockUser(uuid);
            return new ResponseEntity<>(new ApiResponse(true, "User unblocked"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(true, "FAIL unblock user"), HttpStatus.NOT_FOUND);
        }
    }
}