package com.netcrackerpractice.startup_social_network.service.impl;

import com.netcrackerpractice.startup_social_network.entity.*;
import com.netcrackerpractice.startup_social_network.entity.enums.BusinessRoleEnum;
import com.netcrackerpractice.startup_social_network.repository.BusinessRoleRepository;
import com.netcrackerpractice.startup_social_network.repository.ResumeRepository;
import com.netcrackerpractice.startup_social_network.repository.SkillRepository;
import com.netcrackerpractice.startup_social_network.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ResumeServiceImpl implements ResumeService {

    private static final int ZERO_LEN = 0;
    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private BusinessRoleRepository businessRoleRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Override
    public List<Resume> specialistsAfterSearching(SearchObject searchObject) {
        if (searchObject.getSkills().length == ZERO_LEN && searchObject.getRoles().length == ZERO_LEN && searchObject.getSearchString() != null) {
            return resumeRepository.findResumeByName(searchObject.getSearchString());
        } else if (searchObject.getSkills().length != ZERO_LEN && searchObject.getRoles().length == ZERO_LEN && searchObject.getSearchString() == null) {
            Set<Resume> setResume = new HashSet<>();
            for (String skillName : searchObject.getSkills()) {
                Skill skill = skillRepository.findBySkillName(skillName);
                setResume.addAll(skill.getResumeSet());
            }
            return new ArrayList<>(setResume);
        } else if (searchObject.getSkills().length == ZERO_LEN && searchObject.getRoles().length != ZERO_LEN && searchObject.getSearchString() == null) {
            Set<Resume> setResume = new HashSet<>();
            for (String roleName : searchObject.getRoles()) {
                List<Resume> resumeList = resumeRepository.findResumeByBusinessRoleName(roleName.toUpperCase());
                setResume.addAll(resumeList);
            }
            return new ArrayList<>(setResume);
        } else if (searchObject.getSkills().length != ZERO_LEN && searchObject.getRoles().length == ZERO_LEN && searchObject.getSearchString() != null) {
            Set<Resume> setResume = new HashSet<>();
            for (String skillName : searchObject.getSkills()) {
                Skill skill = skillRepository.findBySkillName(skillName);
                setResume.addAll(skill.getResumeSet());
            }
            Set<Resume> resumes = new HashSet<>();
            for (Resume resume : setResume) {
                if (resume.getAccount().getFirstName().equals(searchObject.getSearchString().trim())) {
                    resumes.add(resume);
                }
            }
            return new ArrayList<>(resumes);
        } else if (searchObject.getSkills().length == ZERO_LEN && searchObject.getRoles().length != ZERO_LEN && searchObject.getSearchString() != null) {
            Set<Resume> setResume = new HashSet<>();
            for (String roleName : searchObject.getRoles()) {
                setResume.addAll(resumeRepository.findResumeByRoleNameAndAccountName(roleName.toUpperCase(), searchObject.getSearchString()));
            }
            return new ArrayList<>(setResume);
        } else if (searchObject.getSkills().length != ZERO_LEN && searchObject.getRoles().length != ZERO_LEN && searchObject.getSearchString() == null) {
            Set<Resume> setResume = new HashSet<>();
            Set<Resume> setResumeWithSkill = new HashSet<>();
            Set<Resume> setResumeWithRole = new HashSet<>();
            for (String skillName : searchObject.getSkills()) {
                Skill skill = skillRepository.findBySkillName(skillName);
                setResumeWithSkill.addAll(skill.getResumeSet());
            }
            for (String roleName : searchObject.getRoles()) {
                setResumeWithRole.addAll(resumeRepository.findResumeByBusinessRoleName(roleName));
            }
            for (Resume roleResume : setResumeWithRole) {
                for (Resume skillResume : setResumeWithSkill) {
                    if (roleResume.getId().equals(skillResume.getId())) {
                        setResume.add(roleResume);
                    }
                }
            }
            return new ArrayList<>(setResume);
        } else {
            Set<Resume> setResume = new HashSet<>();
            Set<Resume> resumes = new HashSet<>();
            Set<Resume> setResumeWithSkill = new HashSet<>();
            Set<Resume> setResumeWithRole = new HashSet<>();
            for (String skillName : searchObject.getSkills()) {
                Skill skill = skillRepository.findBySkillName(skillName);
                setResumeWithSkill.addAll(skill.getResumeSet());
            }
            for (String roleName : searchObject.getRoles()) {
                setResumeWithRole.addAll(resumeRepository.findResumeByBusinessRoleName(roleName));
            }
            for (Resume roleResume : setResumeWithRole) {
                for (Resume skillResume : setResumeWithSkill) {
                    if (roleResume.getId().equals(skillResume.getId())) {
                        setResume.add(roleResume);
                    }
                }
            }

            for (Resume resume : setResume) {
                if (resume.getAccount().getFirstName().equals(searchObject.getSearchString().trim())) {
                    resumes.add(resume);
                }
            }
            return new ArrayList<>(resumes);
        }
    }

    @Override
    public List<Resume> searchAccountsByRole(BusinessRoleEnum businessRoleEnum) {
        BusinessRole businessRoleName = businessRoleRepository.findBusinessRoleByBusinessRoleName(businessRoleEnum);
        return resumeRepository.findResumeByBusinessRole(businessRoleName);
    }


    @Override
    public Resume getResumeById(final UUID id) {
        if (resumeRepository.findById(id).isPresent()) {
            return resumeRepository.findById(id).get();
        } else {
            return null;
        }

    }

    @Override
    public List<Resume> listAllResumes() {
        return resumeRepository.findAll();
    }

    @Override
    public List<BusinessRole> listBusinessRolesOfSpecialist() {
        List<Resume> resumeList = resumeRepository.findSpecialistsResumes();
        return resumeList.stream().map(Resume::getBusinessRole).collect(Collectors.toList());
    }

    @Override
    public List<Set<Skill>> listResumeSkillsOfspecialists() {
       /* List<Resume> resumeList = resumeRepository.findSpecialistsResumes();
        return resumeList.stream().map(Resume::getResumeSkills).collect(Collectors.toList());*/
        return null;
    }

    @Override
    public List<Resume> searchAllSpecialist() {
        return resumeRepository.findSpecialistsResumes();
    }


    private List<Account> accounts(List<Resume> resumeList) {
        return resumeList.stream().map(Resume::getAccount).collect(Collectors.toList());
    }


    @Override
    public void deleteResumeById(UUID id) {
        resumeRepository.deleteById(id);
    }

    @Override
    public Resume saveResume(Resume resume) {
        return resumeRepository.save(resume);
    }

    @Override
    @Transactional
    public Resume updateResume(UUID id, Resume resume) {
        resumeRepository.updateBusunessRoleAndInfo(id, resume.getBusinessRole().getId(), resume.getInfo());
        resumeRepository.deleteResumeSkills(id);
        for (Skill skill: resume.getResumeSkills()) {
            resumeRepository.addSkills(id, skill.getId());
        }
        return getResumeById(id);
    }


    @Override
    public List<Resume> findResumesByAccountId(UUID id) {
        return resumeRepository.findResumesByAccount_Id(id);
    }
}
