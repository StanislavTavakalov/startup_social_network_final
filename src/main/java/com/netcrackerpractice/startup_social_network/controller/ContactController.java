package com.netcrackerpractice.startup_social_network.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.netcrackerpractice.startup_social_network.dto.ContactDTO;
import com.netcrackerpractice.startup_social_network.entity.Account;
import com.netcrackerpractice.startup_social_network.exception.AccountNotFoundException;
import com.netcrackerpractice.startup_social_network.service.AccountService;
import com.netcrackerpractice.startup_social_network.service.ContactService;
import com.netcrackerpractice.startup_social_network.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/contacts")
public class ContactController {
    @Autowired
    ContactService contactService;

    @Autowired
    AccountService accountService;

    @JsonView(View.BasicInfo.class)
    @GetMapping("/{userId}")
    public List<Account> getUserContacts(@PathVariable(name = "userId") UUID userId) {
        return contactService.getUserContactsAccounts(userId);
    }

    @JsonView(View.BasicInfo.class)
    @PostMapping("/add")
    public ResponseEntity<Account> addUserContact(@Valid @RequestBody ContactDTO contactDTO) {
        contactService.addUserInContacts(contactDTO);
        return new ResponseEntity<Account>(accountService.findAccountById(contactDTO.getOtherId()).orElseThrow(
                () -> new AccountNotFoundException("Adding account not found")
        ), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteUserContact(@RequestParam(name = "yourId") UUID yourId,
                                            @RequestParam(name = "otherId") UUID otherId) {
        contactService.deleteUserFromContacts(yourId, otherId);
        return ResponseEntity.noContent().build();
    }

    @JsonView(View.BasicInfo.class)
    @GetMapping("/{userId}/search")
    public List<Account> searchInUserContacts(@PathVariable(name = "userId") UUID userId,
                                              @RequestParam(name = "name", required = false) String name) {
        return contactService.searchInUserContacts(userId, name);
    }
}
