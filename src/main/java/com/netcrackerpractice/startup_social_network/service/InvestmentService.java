package com.netcrackerpractice.startup_social_network.service;

import com.netcrackerpractice.startup_social_network.entity.Investment;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface InvestmentService {
    Optional<Investment> findById(UUID id);

    Investment saveInvestment(Investment investment);

    Set<Investment> findTopInvestorForStartup(UUID startupId);
}
