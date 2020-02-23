package com.netcrackerpractice.startup_social_network.repository;

import com.netcrackerpractice.startup_social_network.entity.Account;
import com.netcrackerpractice.startup_social_network.entity.BusinessRole;
import com.netcrackerpractice.startup_social_network.entity.Resume;
import com.netcrackerpractice.startup_social_network.entity.enums.BusinessRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


import java.util.Set;
import java.util.UUID;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, UUID> {
    List<Resume> findResumeByBusinessRole(BusinessRole businessRole);

    @Query(value = "SELECT * FROM resumes LEFT OUTER JOIN business_roles ON resumes.id_business_role = business_roles.id WHERE business_roles.role_name != 'INVESTOR' ", nativeQuery = true)
    List<Resume> findSpecialistsResumes();

    List<Resume> findResumeByAccount(Account account);

    List<Resume> findResumesByAccount_Id(UUID id);

    @Query(value = "SELECT * FROM resumes " +
            "LEFT OUTER JOIN accounts ON resumes.id_account = accounts.id " +
            "WHERE accounts.first_name LIKE %?1% ", nativeQuery = true)
    List<Resume> findResumeByName(String name);


    @Query(value = "SELECT * FROM resumes " +
            "LEFT OUTER JOIN business_roles ON resumes.id_business_role = business_roles.id " +
            "WHERE business_roles.role_name = ?1 ", nativeQuery = true)
    List<Resume> findResumeByBusinessRoleName(String businessRolNeName);


    @Query(value = "SELECT * FROM resumes " +
            "LEFT OUTER JOIN accounts ON resumes.id_account = accounts.id " +
            "LEFT OUTER JOIN business_roles ON resumes.id_business_role = business_roles.id  " +
            "WHERE business_roles.role_name = ?1 AND accounts.first_name LIKE %?2%", nativeQuery = true)
    List<Resume> findResumeByRoleNameAndAccountName(String roleName, String accountName);

    List<Resume> findResumesByAccount_FirstName(String accountName);

    @Query(value = "SELECT * FROM resumes " +
            "LEFT OUTER JOIN resumes_resume_skills ON resumes.id = resumes_resume_skills.resume_id " +
            "LEFT OUTER JOIN skills ON resumes_resume_skills.resumes_skills_id = skills.id " +
            "LEFT OUTER JOIN business_roles ON resumes.id_business_role = business_roles.id  " +
            "WHERE business_roles.role_name = ?1 AND skills.skill_name = ?2", nativeQuery = true)
    List<Resume> findResumeByRoleNameAndSkillName(String roleName, String skillName);

    @Query(value = "DELETE FROM resumes_resume_skills WHERE resumes_resume_skills.resume_id = ?1", nativeQuery = true)
    @Modifying
    @Transactional
    void deleteResumeSkills(UUID id);


    @Query(value = "DELETE FROM resumes_resume_skills WHERE resumes_resume_skills.resume_id = ?1 AND resumes_resume_skills.resumes_skills_id = ?2", nativeQuery = true)
    @Modifying
    @Transactional
    void deleteResumeSkillsByResumeAndSkill(UUID id, UUID id_skill);

   @Query(value = "UPDATE resumes SET id_business_role = ?2, info = ?3" +
           " WHERE id = ?1", nativeQuery = true)
   @Modifying
   @Transactional
   void updateBusunessRoleAndInfo(UUID id,UUID id_businessRole, String info);

    @Query(value = "INSERT INTO resumes_resume_skills(resume_id, resumes_skills_id) " +
            "VALUES (?1, ?2)", nativeQuery = true)
    @Modifying
    @Transactional
    void addSkills(UUID resume_id,UUID skill_id);
}
