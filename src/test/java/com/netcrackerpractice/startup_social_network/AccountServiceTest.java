//package com.netcrackerpractice.startup_social_network;
//
//import com.netcrackerpractice.startup_social_network.entity.Account;
//import com.netcrackerpractice.startup_social_network.repository.AccountRepository;
//import com.netcrackerpractice.startup_social_network.service.AccountService;
//
//import com.netcrackerpractice.startup_social_network.service.impl.AccountServiceImpl;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.Optional;
//import java.util.UUID;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.when;
//
//@RunWith(SpringRunner.class)
//public class AccountServiceTest {
//
//    @InjectMocks
//    private AccountServiceImpl accountService;
//
//    @Mock
//    private AccountRepository accountRepository;
//
//    @Before
//    public void init() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void updateBalance_correctBalance_updateSuccess() {
//        String accountId = "7c53383a-cdad-4c40-9ff2-9c9b93750af0";
//        UUID accountUUID = UUID.fromString(accountId);
//        Integer beforeUpdateBalance = 5000;
//        Integer expectedBalance= 10000;
//
//        Account beforeUpdateAccount = new Account();
//        beforeUpdateAccount.setId(accountUUID);
//        beforeUpdateAccount.setBalance(beforeUpdateBalance);
//
//        Account afterUpdateAccount = new Account();
//        afterUpdateAccount.setId(accountUUID);
//        afterUpdateAccount.setBalance(expectedBalance);
//
//        Optional<Account> opBeforeUpdateAccount = Optional.of(beforeUpdateAccount);
//
//        when(accountRepository.findById(accountUUID)).thenReturn(opBeforeUpdateAccount);
//        when(accountRepository.save(afterUpdateAccount)).thenReturn(afterUpdateAccount);
//
//        Integer resultBalance = accountService.updateBalance(accountUUID, expectedBalance);
//        assertEquals(expectedBalance,  resultBalance);
//
//
//    }
//
//
//    @Test
//    public void updateBalance_negativeBalance_failedOnNegativeValue() {
//        String accountId = "7c53383a-cdad-4c40-9ff2-9c9b93750af1";
//        UUID accountUUID = UUID.fromString(accountId);
//        Integer beforeUpdateBalance = 5000;
//        Integer afterUpdateBalance= -10000;
//
//        Account beforeUpdateAccount = new Account();
//        beforeUpdateAccount.setId(accountUUID);
//        beforeUpdateAccount.setBalance(beforeUpdateBalance);
//
//        Account afterUpdateAccount = new Account();
//        afterUpdateAccount.setId(accountUUID);
//        afterUpdateAccount.setBalance(afterUpdateBalance);
//
//        Optional<Account> opBeforeUpdateAccount = Optional.of(beforeUpdateAccount);
//
//        when(accountRepository.findById(accountUUID)).thenReturn(opBeforeUpdateAccount);
//        when(accountRepository.save(afterUpdateAccount)).thenReturn(afterUpdateAccount);
//
//        Integer expectedResult = null;
//        Integer resultBalance = accountService.updateBalance(accountUUID, expectedResult);
//        assertEquals(expectedResult,  resultBalance);
//
//    }
//
//
//    @Test
//    public void updateBalance_nullBalance_failedOnNullValue() {
//        String accountId = "7c53383a-cdad-4c40-9ff2-9c9b93750af2";
//        UUID accountUUID = UUID.fromString(accountId);
//        Integer beforeUpdateBalance = 5000;
//        Integer afterUpdateBalance= -10000;
//
//        Account beforeUpdateAccount = new Account();
//        beforeUpdateAccount.setId(accountUUID);
//        beforeUpdateAccount.setBalance(beforeUpdateBalance);
//
//        Account afterUpdateAccount = new Account();
//        afterUpdateAccount.setId(accountUUID);
//        afterUpdateAccount.setBalance(afterUpdateBalance);
//
//        Optional<Account> opBeforeUpdateAccount = Optional.of(beforeUpdateAccount);
//
//        when(accountRepository.findById(accountUUID)).thenReturn(opBeforeUpdateAccount);
//        when(accountRepository.save(afterUpdateAccount)).thenReturn(afterUpdateAccount);
//
//        Integer expectedResult = null;
//        Integer resultBalance = accountService.updateBalance(accountUUID, expectedResult);
//        assertEquals(expectedResult,  resultBalance);
//
//    }
//}
