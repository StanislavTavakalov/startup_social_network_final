package com.netcrackerpractice.startup_social_network.controller;

import com.netcrackerpractice.startup_social_network.dto.StartupResumeDTO;
import com.netcrackerpractice.startup_social_network.entity.StartupResume;
import com.netcrackerpractice.startup_social_network.mapper.StartupResumeMapper;
import com.netcrackerpractice.startup_social_network.service.StartupResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/startup-resume")
public class StartupResumeController {

    @Autowired
    private StartupResumeService startupResumeService;

    @Autowired
    StartupResumeMapper startupResumeMapper;

    @PostMapping("")
    public ResponseEntity<StartupResumeDTO> addStartupResume(@RequestBody StartupResumeDTO startupResume) {
        StartupResume startupResume1 = startupResumeService.addStartupResume(startupResumeMapper.dtoToEntity(startupResume));
        if (startupResume1 != null) {
            return ResponseEntity.ok(startupResumeMapper.entityToDto(startupResume1));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/accept-resume/{id}")
    public ResponseEntity<StartupResumeDTO> acceptStartupResume(@PathVariable(name = "id") UUID id, @RequestBody String startupRole) {
        StartupResume startupResume1 = startupResumeService.acceptStartupResume(id, startupRole);
        if (startupResume1 != null) {
            return ResponseEntity.ok(startupResumeMapper.entityToDto(startupResume1));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/cancel-resume/{id}")
    public void rejectResume(@PathVariable(name = "id") UUID id) {
        startupResumeService.deleteStartupResumeById(id);
    }


}
