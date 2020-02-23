package com.netcrackerpractice.startup_social_network.service;

import com.netcrackerpractice.startup_social_network.dto.ContactDTO;
import com.netcrackerpractice.startup_social_network.entity.Account;

import java.util.List;
import java.util.UUID;

public interface ContactService {
    List<Account> getUserContactsAccounts(UUID userId);

    List<Account> searchInUserContacts(UUID userId, String name);

    void addUserInContacts(ContactDTO contactDTO);

    void deleteUserFromContacts(UUID yourId, UUID otherId);
}
