package com.peepcoins.service;

import com.peepcoins.service.impl.CryptoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class CryptoPriceUpdater {

    private final long SECOND = 1000L;

    private final long MINUTE = SECOND * 60;

    @Autowired
    private CryptoServiceImpl cryptoService;

    @Scheduled(fixedDelay = MINUTE)
    public void updatePrices() {
        cryptoService.fetchAndUpdateAll();
    }
}
