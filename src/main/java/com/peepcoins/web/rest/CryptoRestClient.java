package com.peepcoins.web.rest;

import com.peepcoins.domain.Crypto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CryptoRestClient {

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<Crypto[]> getCryptoInfo() {
        return restTemplate.getForEntity(
            "https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=10&page=1&sparkline=false",
            Crypto[].class
        );
    }
}
