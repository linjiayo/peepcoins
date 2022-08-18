package com.peepcoins.service.impl;

import com.peepcoins.domain.CryptoUser;
import com.peepcoins.repository.CryptoUserRepository;
import com.peepcoins.service.CryptoUserService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link CryptoUser}.
 */
@Service
@Transactional
public class CryptoUserServiceImpl implements CryptoUserService {

    private final Logger log = LoggerFactory.getLogger(CryptoUserServiceImpl.class);

    private final CryptoUserRepository cryptoUserRepository;

    public CryptoUserServiceImpl(CryptoUserRepository cryptoUserRepository) {
        this.cryptoUserRepository = cryptoUserRepository;
    }

    @Override
    public CryptoUser save(CryptoUser cryptoUser) {
        log.debug("Request to save CryptoUser : {}", cryptoUser);
        return cryptoUserRepository.save(cryptoUser);
    }

    @Override
    public CryptoUser update(CryptoUser cryptoUser) {
        log.debug("Request to save CryptoUser : {}", cryptoUser);
        return cryptoUserRepository.save(cryptoUser);
    }

    @Override
    public Optional<CryptoUser> partialUpdate(CryptoUser cryptoUser) {
        log.debug("Request to partially update CryptoUser : {}", cryptoUser);

        return cryptoUserRepository
            .findById(cryptoUser.getId())
            .map(existingCryptoUser -> {
                return existingCryptoUser;
            })
            .map(cryptoUserRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CryptoUser> findAll() {
        log.debug("Request to get all CryptoUsers");
        return cryptoUserRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CryptoUser> findOne(Long id) {
        log.debug("Request to get CryptoUser : {}", id);
        return cryptoUserRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete CryptoUser : {}", id);
        cryptoUserRepository.deleteById(id);
    }
}
