package com.netcrackerpractice.startup_social_network.controller;

import com.netcrackerpractice.startup_social_network.dto.DetailAccountDTO;
import com.netcrackerpractice.startup_social_network.entity.Account;
import com.netcrackerpractice.startup_social_network.mapper.AccountMapper;
import com.netcrackerpractice.startup_social_network.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.UUID;

@RestController
@RequestMapping("api/account")
public class AccountController {
    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private AccountService accountService;

    @GetMapping("/account-list")
    public Iterable<DetailAccountDTO> getAllAccounts() {
        LinkedList<DetailAccountDTO> accountList = new LinkedList<>();
        accountService.findAll().forEach(account -> accountList.add(accountMapper.entityToDto(account)));
        return accountList;
    }

    @GetMapping(value = "/{accountId}")
    public ResponseEntity<?> getAccount(@PathVariable("accountId") UUID id) {
       // Account account = accountService.findAccountById(id).get();
        DetailAccountDTO accountDTO = accountMapper.entityToDto(accountService.findAccountById(id).get());
        return new ResponseEntity<>(accountDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody DetailAccountDTO accountDTO) {
        Account account = accountMapper.dtoToEntity(accountDTO);
        return new ResponseEntity<>(accountService.saveAccount(accountMapper.dtoToEntity(accountDTO)), HttpStatus.OK);
    }


    @PutMapping(value = "/update/{accountId}")
    public ResponseEntity<?> update(@PathVariable("accountId") UUID id, @RequestBody DetailAccountDTO accountDTO) throws URISyntaxException {
        if (accountDTO.getId() == null) {
            return ResponseEntity.badRequest().header("Failure", "Save failed. Try again").build();
        }
        Account account = accountMapper.dtoToEntity(accountDTO);
        Account ac = accountService.updateAccount(id, account, accountDTO.getImage());

        if( ac != null){
            return ResponseEntity.ok(accountMapper.entityToDto(ac));
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    @DeleteMapping("/delete/{accountId}")
    public ResponseEntity<?> delete(@PathVariable("accountId") UUID id) {
        accountService.deleteAccountById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update-balance/{accountId}")
    public ResponseEntity<Integer> updateAccountBalance(@PathVariable("accountId") UUID id, @RequestBody Integer currentBalance) {
        Integer balance = accountService.updateBalance(id, currentBalance);
        if (balance != null) {
            return ResponseEntity.ok(balance);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}
