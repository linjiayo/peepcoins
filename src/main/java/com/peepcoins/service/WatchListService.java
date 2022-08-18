package com.peepcoins.service;

import com.peepcoins.domain.WatchList;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link WatchList}.
 */
public interface WatchListService {
    /**
     * Save a watchList.
     *
     * @param watchList the entity to save.
     * @return the persisted entity.
     */
    WatchList save(WatchList watchList);

    /**
     * Updates a watchList.
     *
     * @param watchList the entity to update.
     * @return the persisted entity.
     */
    WatchList update(WatchList watchList);

    /**
     * Partially updates a watchList.
     *
     * @param watchList the entity to update partially.
     * @return the persisted entity.
     */
    Optional<WatchList> partialUpdate(WatchList watchList);

    /**
     * Get all the watchLists.
     *
     * @return the list of entities.
     */
    List<WatchList> findAll();

    /**
     * Get the "id" watchList.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WatchList> findOne(Long id);

    /**
     * Delete the "id" watchList.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
