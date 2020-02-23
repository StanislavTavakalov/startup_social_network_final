package com.netcrackerpractice.startup_social_network.service.impl;

import com.netcrackerpractice.startup_social_network.entity.Account;
import com.netcrackerpractice.startup_social_network.entity.Resume;
import com.netcrackerpractice.startup_social_network.repository.AccountRepository;
import com.netcrackerpractice.startup_social_network.repository.BusinessRoleRepository;
import com.netcrackerpractice.startup_social_network.repository.ResumeRepository;
import com.netcrackerpractice.startup_social_network.service.AccountService;
import com.netcrackerpractice.startup_social_network.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    private ResumeRepository resumeRepository;

    @Autowired
    private BusinessRoleRepository businessRoleRepository;


    @Autowired
    private ImageService imageService;

    @Override
    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> findAccountById(UUID uuid) {
        return accountRepository.findById(uuid);
    }

    @Override
    @Transactional
    public void deleteAccountById(UUID uuid) {
        accountRepository.deleteById(uuid);
    }

    @Override
    public Account updateAccount(UUID id, Account account, String image){
        if(accountRepository.findById(id)!=null){
            Account updatedAccount=account;
            try {
                if(image != null && !image.equals("")) {
                    imageService.deleteImageFromGoogleDrive(updatedAccount.getImageId(), updatedAccount.getCompressedImageId());
                    File imageFile = imageService.convertStringToFile(image);
                    String imageId = imageService.saveImageToGoogleDrive(imageFile);

                    String comressedImagePath = imageService.compressionImage(imageFile);
                    File comressedImageFile = new File(comressedImagePath);
                    String comressedImageId = imageService.saveImageToGoogleDrive(comressedImageFile);

                    updatedAccount.setImageId(imageId);
                    updatedAccount.setCompressedImageId(comressedImageId);

                    imageFile.delete();
                    comressedImageFile.delete();
                }
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            updatedAccount.setResumes(new ArrayList<Resume>());
            return saveAccount(updatedAccount);
        }
        return null;
    }


    private List<Account> accountsList(Set<Resume> setList) {
        return setList.stream().map(Resume::getAccount).collect(Collectors.toList());
    }

    @Override
    public Integer updateBalance(UUID id, Integer balance) {
        Optional<Account> account = this.accountRepository.findById(id);
        if((account.isPresent() && balance != null ) && balance > 0 ){
            account.get().setBalance(balance);
            return this.accountRepository.save(account.get()).getBalance();
        }
        else {
            return null;
        }
    }
}
