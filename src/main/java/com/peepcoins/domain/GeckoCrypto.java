package com.peepcoins.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GeckoCrypto {

    @JsonProperty("id")
    private String cryptoId;

    private String symbol;

    private String name;

    @JsonProperty("current_price")
    private Double currentPrice;

    @JsonProperty("market_cap")
    private Long marketCap;

    @JsonProperty("market_cap_rank")
    private Integer marketCapRank;

    @JsonProperty("total_volume")
    private Long totalVolume;

    @JsonProperty("high_24h")
    private Double high24;

    @JsonProperty("low_24h")
    private Double low24;

    @JsonProperty("price_change_24hr")
    private Double priceChange24hr;

    @JsonProperty("price_change_percentage_24hr")
    private Double priceChangePercentage24hr;

    @JsonProperty("total_supply")
    private Long totalSupply;

    private Double ath;

    @JsonProperty("ath_date")
    private String athDate;

    private Double atl;

    @JsonProperty("atl_date")
    private String atlDate;

    public GeckoCrypto(GeckoCryptoBuilder builder) {
        cryptoId = builder.id;
    }

    public String getId() {
        return cryptoId;
    }

    public void setId(String id) {
        this.cryptoId = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Long getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(Long marketCap) {
        this.marketCap = marketCap;
    }

    public Integer getMarketCapRank() {
        return marketCapRank;
    }

    public void setMarketCapRank(Integer marketCapRank) {
        this.marketCapRank = marketCapRank;
    }

    public Long getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(Long totalVolume) {
        this.totalVolume = totalVolume;
    }

    public Double getHigh24() {
        return high24;
    }

    public void setHigh24(Double high24) {
        this.high24 = high24;
    }

    public Double getLow24() {
        return low24;
    }

    public void setLow24(Double low24) {
        this.low24 = low24;
    }

    public Double getPriceChange24hr() {
        return priceChange24hr;
    }

    public void setPriceChange24hr(Double priceChange24hr) {
        this.priceChange24hr = priceChange24hr;
    }

    public Double getPriceChangePercentage24hr() {
        return priceChangePercentage24hr;
    }

    public void setPriceChangePercentage24hr(Double priceChangePercentage24hr) {
        this.priceChangePercentage24hr = priceChangePercentage24hr;
    }

    public Long getTotalSupply() {
        return totalSupply;
    }

    public void setTotalSupply(Long totalSupply) {
        this.totalSupply = totalSupply;
    }

    public Double getAth() {
        return ath;
    }

    public void setAth(Double ath) {
        this.ath = ath;
    }

    public String getAthDate() {
        return athDate;
    }

    public void setAthDate(String athDate) {
        this.athDate = athDate;
    }

    public Double getAtl() {
        return atl;
    }

    public void setAtl(Double atl) {
        this.atl = atl;
    }

    public String getAtlDate() {
        return atlDate;
    }

    public void setAtlDate(String atlDate) {
        this.atlDate = atlDate;
    }
}
