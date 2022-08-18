package com.peepcoins.repository;

import com.peepcoins.domain.Crypto;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Crypto entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CryptoRepository extends JpaRepository<Crypto, String> {}
