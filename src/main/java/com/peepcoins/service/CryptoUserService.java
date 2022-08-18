package com.peepcoins.service;

import com.peepcoins.domain.CryptoUser;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link CryptoUser}.
 */
public interface CryptoUserService {
    /**
     * Save a cryptoUser.
     *
     * @param cryptoUser the entity to save.
     * @return the persisted entity.
     */
    CryptoUser save(CryptoUser cryptoUser);

    /**
     * Updates a cryptoUser.
     *
     * @param cryptoUser the entity to update.
     * @return the persisted entity.
     */
    CryptoUser update(CryptoUser cryptoUser);

    /**
     * Partially updates a cryptoUser.
     *
     * @param cryptoUser the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CryptoUser> partialUpdate(CryptoUser cryptoUser);

    /**
     * Get all the cryptoUsers.
     *
     * @return the list of entities.
     */
    List<CryptoUser> findAll();

    /**
     * Get the "id" cryptoUser.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CryptoUser> findOne(Long id);

    /**
     * Delete the "id" cryptoUser.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
