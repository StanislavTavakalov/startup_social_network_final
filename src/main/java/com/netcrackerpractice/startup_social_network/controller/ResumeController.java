package com.netcrackerpractice.startup_social_network.controller;

import com.netcrackerpractice.startup_social_network.dto.BusinessRoleDTO;
import com.netcrackerpractice.startup_social_network.dto.ResumeDTO;
import com.netcrackerpractice.startup_social_network.entity.Resume;
import com.netcrackerpractice.startup_social_network.entity.Skill;
import com.netcrackerpractice.startup_social_network.entity.StartupResume;
import com.netcrackerpractice.startup_social_network.mapper.BusinessRoleMapper;
import com.netcrackerpractice.startup_social_network.mapper.ResumeMapper;
import com.netcrackerpractice.startup_social_network.repository.BusinessRoleRepository;
import com.netcrackerpractice.startup_social_network.repository.SkillRepository;
import com.netcrackerpractice.startup_social_network.repository.StartupResumeRepository;
import com.netcrackerpractice.startup_social_network.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/resume")
public class ResumeController {
    @Autowired
    private ResumeService resumeService;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private BusinessRoleRepository businessRoleRepository;

    @Autowired
    private BusinessRoleMapper businessRoleMapper;

    @Autowired
    private ResumeMapper resumeMapper;

    @Autowired
    private StartupResumeRepository startupResumeRepository;


    @GetMapping("/list")
    public List<ResumeDTO> listAllResumes() {
        List<ResumeDTO> resumeDTOS = new ArrayList<>();
        resumeService.listAllResumes().forEach(resume -> resumeDTOS.add(resumeMapper.entityToDto(resume)));
        return resumeDTOS;
    }


    @GetMapping("/{id}")
    public ResumeDTO geResumeById(@PathVariable UUID id) {
        return resumeMapper.entityToDto(resumeService.getResumeById(id));
    }

    @GetMapping("/skills")
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }


    @GetMapping("/specialists-business-role")
    public List<BusinessRoleDTO> getAllBusinessRole() {
        List<BusinessRoleDTO> businessRoleDTOS = new ArrayList<>();
        businessRoleRepository.findAll().forEach(businessRole -> businessRoleDTOS.add(businessRoleMapper.entityToDto(businessRole)));
        return businessRoleDTOS;
    }


    @DeleteMapping("/delete/{id}")
    public void deleteResume(@PathVariable UUID id) {
        resumeService.deleteResumeById(id);
    }

    @PostMapping("/create")
    public Resume saveResume(@RequestBody Resume resume) {
        return resumeService.saveResume(resume);
    }

    @PutMapping("/update/{id}")
    public ResumeDTO updateResume(@PathVariable UUID id, @RequestBody Resume resume) {
        Resume resume1 = resumeService.updateResume(id, resume);
        return resumeMapper.entityToDto(resume1);
    }


    @GetMapping("/my-resume-list/{id}")
    public List<ResumeDTO> findMyResumeList(@PathVariable UUID id) {
        List<ResumeDTO> resumeDTOS = new ArrayList<>();
        resumeService.findResumesByAccountId(id).forEach(resume -> resumeDTOS.add(resumeMapper.entityToDto(resume)));
        return resumeDTOS;
    }

    @GetMapping("/checkIsInStartup")
    public ResumeDTO resumesThatParticipateInStartUp(@RequestParam(name = "id_account") UUID id){
        List<ResumeDTO> resumeDTOS = new ArrayList<>();
        Resume resume = resumeService.getResumeById(id);
        List<StartupResume> list = startupResumeRepository.findByResume(resume);
        if(list.size() != 0){
            return resumeMapper.entityToDto(resume);
        }
        else{
            return null;
        }
    }
}
