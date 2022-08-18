package com.peepcoins.repository;

import com.peepcoins.domain.CryptoUser;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the CryptoUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CryptoUserRepository extends JpaRepository<CryptoUser, Long> {}
