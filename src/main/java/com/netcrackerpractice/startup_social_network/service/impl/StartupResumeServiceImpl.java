package com.netcrackerpractice.startup_social_network.service.impl;

import com.netcrackerpractice.startup_social_network.entity.StartupResume;
import com.netcrackerpractice.startup_social_network.entity.StartupRole;
import com.netcrackerpractice.startup_social_network.entity.enums.StartupRoleEnum;
import com.netcrackerpractice.startup_social_network.mapper.StartupResumeMapper;
import com.netcrackerpractice.startup_social_network.repository.StartupResumeRepository;
import com.netcrackerpractice.startup_social_network.repository.StartupRoleRepository;
import com.netcrackerpractice.startup_social_network.service.StartupResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class StartupResumeServiceImpl implements StartupResumeService {

    @Autowired
    StartupResumeRepository startupResumeRepository;

    @Autowired
    StartupRoleRepository startupRoleRepository;

    @Autowired
    StartupResumeMapper startupResumeMapper;

    @Override
    public StartupResume addStartupResume(StartupResume startupResume) {
        return startupResumeRepository.save(startupResume);
    }

    @Override
    public void deleteStartupResumeById(UUID id) {
        startupResumeRepository.deleteById(id);
    }

    @Override
    public StartupResume acceptStartupResume(UUID startupResumeId, String sRole) {
        Optional<StartupResume> sr = startupResumeRepository.findById(startupResumeId);
        if (sr.isPresent()) {
            StartupResume startupResume = sr.get();
            startupResume.setAccepted(true);
            StartupRole startupRole = new StartupRole();
            startupRole.setAccount(startupResume.getResume().getAccount());
            startupRole.setStartup(startupResume.getStartup());
            startupRole.setRoleName(StartupRoleEnum.valueOf(sRole.toUpperCase()));
            startupRoleRepository.save(startupRole);
            return startupResumeRepository.save(startupResume);
        }
        return null;
    }
}
