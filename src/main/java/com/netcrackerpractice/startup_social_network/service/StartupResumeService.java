package com.netcrackerpractice.startup_social_network.service;

import com.netcrackerpractice.startup_social_network.dto.StartupResumeDTO;
import com.netcrackerpractice.startup_social_network.entity.StartupResume;

import java.util.UUID;

public interface StartupResumeService {
    StartupResume addStartupResume(StartupResume startupResume);

    void deleteStartupResumeById(UUID id);

    StartupResume acceptStartupResume(UUID startupResumeId, String startupRole);
}
