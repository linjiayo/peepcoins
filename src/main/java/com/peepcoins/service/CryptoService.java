package com.peepcoins.service;

import com.peepcoins.domain.Crypto;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link Crypto}.
 */
public interface CryptoService {
    /**
     * Save a crypto.
     *
     * @param crypto the entity to save.
     * @return the persisted entity.
     */
    Crypto save(Crypto crypto);

    /**
     * Updates a crypto.
     *
     * @param crypto the entity to update.
     * @return the persisted entity.
     */
    Crypto update(Crypto crypto);

    /**
     * Partially updates a crypto.
     *
     * @param crypto the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Crypto> partialUpdate(Crypto crypto);

    /**
     * Get all the cryptos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Crypto> findAll(Pageable pageable);

    /**
     * Get the "id" crypto.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Crypto> findOne(String id);

    /**
     * Delete the "id" crypto.
     *
     * @param id the id of the entity.
     */
    void delete(String id);

    void fetchAndUpdateAll();
}
