package com.netcrackerpractice.startup_social_network.repository;


import com.netcrackerpractice.startup_social_network.entity.Investment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface InvestmentRepository extends JpaRepository<Investment, Long> {
    Optional<Investment> findById(UUID id);

    //
//    @Query(value = "SELECT i.id, i.a, i.s, SUM(investments.sum_of_investment)" +
//            " FROM Investment i join Startup s" +
//            "join Account a where s.id = ?1 group by i.a, i.s")
//SELECT a.id, s.id, SUM(investments.sum_of_investment)
//    FROM investments join startups s on investments.id_startup = s.id
//    join accounts a on investments.id_investor = a.id where s.id = '7b14db42-18a0-4253-aea6-4d95be1ad235' group by a.id, s.id"
//
////@Query(value = "SELECT i, SUM(i.sumOfInvestment) from Investment i join i.startup s where s.id =?1 group by i")
    @Query(value = "SELECT  investments.id_investor as id,investments.id_investor, investments.id_startup, Sum(investments.sum_of_investment)/9 as sum_of_investment FROM investments join accounts a on investments.id_investor = a.id join startups s2 on a.id = s2.id_creator where investments.id_startup = ?1 group by investments.id_startup, investments.id_investor", nativeQuery = true)
    Set<Investment> findTopInvestorForStartup(UUID startupId);
}
