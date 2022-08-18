package com.peepcoins.service.impl;

import com.peepcoins.domain.WatchList;
import com.peepcoins.repository.WatchListRepository;
import com.peepcoins.service.WatchListService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link WatchList}.
 */
@Service
@Transactional
public class WatchListServiceImpl implements WatchListService {

    private final Logger log = LoggerFactory.getLogger(WatchListServiceImpl.class);

    private final WatchListRepository watchListRepository;

    public WatchListServiceImpl(WatchListRepository watchListRepository) {
        this.watchListRepository = watchListRepository;
    }

    @Override
    public WatchList save(WatchList watchList) {
        log.debug("Request to save WatchList : {}", watchList);
        return watchListRepository.save(watchList);
    }

    @Override
    public WatchList update(WatchList watchList) {
        log.debug("Request to save WatchList : {}", watchList);
        return watchListRepository.save(watchList);
    }

    @Override
    public Optional<WatchList> partialUpdate(WatchList watchList) {
        log.debug("Request to partially update WatchList : {}", watchList);

        return watchListRepository
            .findById(watchList.getId())
            .map(existingWatchList -> {
                return existingWatchList;
            })
            .map(watchListRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WatchList> findAll() {
        log.debug("Request to get all WatchLists");
        return watchListRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<WatchList> findOne(Long id) {
        log.debug("Request to get WatchList : {}", id);
        return watchListRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete WatchList : {}", id);
        watchListRepository.deleteById(id);
    }
}
