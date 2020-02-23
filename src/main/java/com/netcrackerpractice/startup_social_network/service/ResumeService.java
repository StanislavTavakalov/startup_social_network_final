package com.netcrackerpractice.startup_social_network.service;

import com.netcrackerpractice.startup_social_network.entity.BusinessRole;
import com.netcrackerpractice.startup_social_network.entity.Resume;
import com.netcrackerpractice.startup_social_network.entity.SearchObject;
import com.netcrackerpractice.startup_social_network.entity.Skill;
import com.netcrackerpractice.startup_social_network.entity.enums.BusinessRoleEnum;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface ResumeService {
    List<Resume> searchAccountsByRole(BusinessRoleEnum roleEnum);

    List<Resume> listAllResumes();

    List<Resume> searchAllSpecialist();

    List<Set<Skill>> listResumeSkillsOfspecialists();

    List<BusinessRole> listBusinessRolesOfSpecialist();

    Resume getResumeById(final UUID id);

    void deleteResumeById(UUID id);

    Resume saveResume(Resume startup);

    Resume updateResume(UUID id, Resume startup);

    List<Resume> specialistsAfterSearching(SearchObject searchObject);

    List<Resume> findResumesByAccountId(UUID id);
}
