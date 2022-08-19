package com.peepcoins.service.impl;

import com.peepcoins.domain.Crypto;
import com.peepcoins.repository.CryptoRepository;
import com.peepcoins.service.CryptoService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Crypto}.
 */
@Service
@Transactional
public class CryptoServiceImpl implements CryptoService {

    private final Logger log = LoggerFactory.getLogger(CryptoServiceImpl.class);

    private final CryptoRepository cryptoRepository;

    public CryptoServiceImpl(CryptoRepository cryptoRepository) {
        this.cryptoRepository = cryptoRepository;
    }

    @Override
    public Crypto save(Crypto crypto) {
        log.debug("Request to save Crypto : {}", crypto);
        return cryptoRepository.save(crypto);
    }

    @Override
    public Crypto update(Crypto crypto) {
        log.debug("Request to save Crypto : {}", crypto);
        crypto.setIsPersisted();
        return cryptoRepository.save(crypto);
    }

    @Override
    public Optional<Crypto> partialUpdate(Crypto crypto) {
        log.debug("Request to partially update Crypto : {}", crypto);

        return cryptoRepository
            .findById(crypto.getId())
            .map(existingCrypto -> {
                if (crypto.getSymbol() != null) {
                    existingCrypto.setSymbol(crypto.getSymbol());
                }
                if (crypto.getName() != null) {
                    existingCrypto.setName(crypto.getName());
                }
                if (crypto.getCurrentPrice() != null) {
                    existingCrypto.setCurrentPrice(crypto.getCurrentPrice());
                }
                if (crypto.getMarketCap() != null) {
                    existingCrypto.setMarketCap(crypto.getMarketCap());
                }
                if (crypto.getMarketCapRank() != null) {
                    existingCrypto.setMarketCapRank(crypto.getMarketCapRank());
                }
                if (crypto.getTotalVolume() != null) {
                    existingCrypto.setTotalVolume(crypto.getTotalVolume());
                }
                if (crypto.getHigh24() != null) {
                    existingCrypto.setHigh24(crypto.getHigh24());
                }
                if (crypto.getLow24() != null) {
                    existingCrypto.setLow24(crypto.getLow24());
                }
                if (crypto.getPriceChange24() != null) {
                    existingCrypto.setPriceChange24(crypto.getPriceChange24());
                }
                if (crypto.getPriceChangePercentage24hr() != null) {
                    existingCrypto.setPriceChangePercentage24hr(crypto.getPriceChangePercentage24hr());
                }
                if (crypto.getTotalSupply() != null) {
                    existingCrypto.setTotalSupply(crypto.getTotalSupply());
                }
                if (crypto.getAth() != null) {
                    existingCrypto.setAth(crypto.getAth());
                }
                if (crypto.getAthDate() != null) {
                    existingCrypto.setAthDate(crypto.getAthDate());
                }
                if (crypto.getAtl() != null) {
                    existingCrypto.setAtl(crypto.getAtl());
                }
                if (crypto.getAtlDate() != null) {
                    existingCrypto.setAtlDate(crypto.getAtlDate());
                }

                return existingCrypto;
            })
            .map(cryptoRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Crypto> findAll(Pageable pageable) {
        log.debug("Request to get all Cryptos");
        return cryptoRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Crypto> findOne(String id) {
        log.debug("Request to get Crypto : {}", id);
        return cryptoRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Crypto : {}", id);
        cryptoRepository.deleteById(id);
    }


}
