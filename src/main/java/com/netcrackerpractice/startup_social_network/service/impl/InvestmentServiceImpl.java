package com.netcrackerpractice.startup_social_network.service.impl;

import com.netcrackerpractice.startup_social_network.entity.Account;
import com.netcrackerpractice.startup_social_network.entity.Investment;
import com.netcrackerpractice.startup_social_network.repository.AccountRepository;
import com.netcrackerpractice.startup_social_network.repository.InvestmentRepository;
import com.netcrackerpractice.startup_social_network.service.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class InvestmentServiceImpl implements InvestmentService {

    @Autowired
    InvestmentRepository investmentRepository;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public Optional<Investment> findById(UUID id) {
        return investmentRepository.findById(id);
    }

    @Transactional
    @Override
    public Investment saveInvestment(Investment investment) {
        Optional<Account> account = accountRepository.findById(investment.getInvestor().getId());
        if (account.isPresent()) {
            if ((investment.getSumOfInvestment() < 0) || ((account.get().getBalance() - investment.getSumOfInvestment()) < 0)) {
                return null;
            }
            account.get().setBalance(account.get().getBalance() - investment.getSumOfInvestment());
            accountRepository.save(account.get());
            return investmentRepository.save(investment);
        } else {
            return null;
        }
    }

    @Override
    public Set<Investment> findTopInvestorForStartup(UUID startupId) {
        return investmentRepository.findTopInvestorForStartup(startupId);
    }
}
