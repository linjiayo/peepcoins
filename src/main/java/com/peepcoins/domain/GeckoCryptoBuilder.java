package com.peepcoins.domain;

public class GeckoCryptoBuilder {

    public String id;
    public String symbol;
    public String name;
    public Double currentPrice;
    public Long marketCap;
    public Integer marketCapRank;
    public Long totalVolume;
    public Double high24;
    public Double low24;
    public Double priceChange24;
    public Double priceChangePercentage24hr;
    public Long totalSupply;
    public Double ath;
    public String athDate;
    public Double atl;
    public String atlDate;

    public GeckoCryptoBuilder id(String id) {
        this.id = id;
        return this;
    }

    public GeckoCryptoBuilder symbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public GeckoCryptoBuilder name(String name) {
        this.name = name;
        return this;
    }

    public GeckoCryptoBuilder currentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
        return this;
    }

    public GeckoCryptoBuilder marketCap(Long marketCap) {
        this.marketCap = marketCap;
        return this;
    }

    public GeckoCryptoBuilder marketCapRank(Integer marketCapRank) {
        this.marketCapRank = marketCapRank;
        return this;
    }

    public GeckoCryptoBuilder totalVolume(Long totalVolume) {
        this.totalVolume = totalVolume;
        return this;
    }

    public GeckoCryptoBuilder high24(Double high24) {
        this.high24 = high24;
        return this;
    }

    public GeckoCryptoBuilder low24(Double low24) {
        this.low24 = low24;
        return this;
    }

    public GeckoCryptoBuilder priceChange24(Double priceChange24) {
        this.priceChange24 = priceChange24;
        return this;
    }

    public GeckoCryptoBuilder priceChangePercentage24(Double priceChangePercentage24hr) {
        this.priceChangePercentage24hr = priceChangePercentage24hr;
        return this;
    }

    public GeckoCryptoBuilder totalSupply(Long totalSupply) {
        this.totalSupply = totalSupply;
        return this;
    }

    public GeckoCryptoBuilder ath(Double ath) {
        this.ath = ath;
        return this;
    }

    public GeckoCryptoBuilder athDate(String date) {
        this.athDate = date;
        return this;
    }

    public GeckoCryptoBuilder atl(Double atl) {
        this.atl = atl;
        return this;
    }

    public GeckoCryptoBuilder atlDate(String atlDate) {
        this.atlDate = atlDate;
        return this;
    }

    public GeckoCrypto build() {
        return new GeckoCrypto(this);
    }
}
