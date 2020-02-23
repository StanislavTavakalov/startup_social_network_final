package com.netcrackerpractice.startup_social_network.service.impl;

import com.netcrackerpractice.startup_social_network.dto.ContactDTO;
import com.netcrackerpractice.startup_social_network.entity.Account;
import com.netcrackerpractice.startup_social_network.repository.ContactRepository;
import com.netcrackerpractice.startup_social_network.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    ContactRepository contactRepository;

    @Override
    public List<Account> searchInUserContacts(UUID userId, String name) {
        List<Account> accountEntityList = contactRepository.getUserContacts(userId);

        if (name != null) {
            accountEntityList = findByName(accountEntityList, name);
        }
        return accountEntityList;
    }

    @Override
    public List<Account> getUserContactsAccounts(UUID userId) {
        return contactRepository.getUserContacts(userId);
    }

    @Override
    public void addUserInContacts(ContactDTO contactDTO) {
        contactRepository.addUserInContacts(contactDTO.getYourId(), contactDTO.getOtherId());
    }

    @Override
    public void deleteUserFromContacts(UUID yourId, UUID otherId) {
        contactRepository.deleteUserFromContacts(yourId, otherId);
    }

    private List<Account> findByName(List<Account> accountEntityList, String name) {
        String[] names = name.trim().split(" ");
        String firstName = names[0], lastName;

        if (names.length != 1)
            lastName = names[1];
        else
            lastName = "";

        return accountEntityList.stream()
                .filter(accountEntity ->
                        accountEntity.getFirstName().toLowerCase().contains(firstName.toLowerCase())
                                && accountEntity.getLastName().toLowerCase().contains(lastName.toLowerCase())
                                ||
                                accountEntity.getFirstName().toLowerCase().contains(lastName.toLowerCase())
                                        && accountEntity.getLastName().toLowerCase().contains(firstName.toLowerCase())
                ).collect(Collectors.toList());
    }
}
