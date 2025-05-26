package com.SkyRise.SkyRise_express.repository;

import com.SkyRise.SkyRise_express.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
 