package com.peepcoins.repository;

import com.peepcoins.domain.WatchList;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the WatchList entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WatchListRepository extends JpaRepository<WatchList, Long> {}
