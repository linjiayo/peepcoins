package com.peepcoins.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.domain.Persistable;

/**
 * A Crypto.
 */
@Entity
@Table(name = "crypto")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Crypto implements Serializable, Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @Column(name = "id", nullable = false)
    @JsonProperty("id")
    private String id;

    @Column(name = "symbol")
    private String symbol;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "current_price")
    @JsonProperty("current_price")
    private Double currentPrice;

    @Column(name = "market_cap")
    @JsonProperty("market_cap")
    private Long marketCap;

    @Column(name = "market_cap_rank")
    @JsonProperty("market_cap_rank")
    private Integer marketCapRank;

    @Column(name = "total_volume")
    @JsonProperty("total_volume")
    private Long totalVolume;

    @Column(name = "high_24")
    @JsonProperty("high_24h")
    private Double high24;

    @Column(name = "low_24")
    @JsonProperty("low_24h")
    private Double low24;

    @Column(name = "price_change_24")
    @JsonProperty("price_change_24h")
    private Double priceChange24;

    @Column(name = "price_change_percentage_24_hr")
    @JsonProperty("price_change_percentage_24h")
    private Double priceChangePercentage24hr;

    @Column(name = "total_supply")
    @JsonProperty("total_supply")
    private Long totalSupply;

    @Column(name = "ath")
    private Double ath;

    @Column(name = "ath_date")
    @JsonProperty("ath_date")
    private String athDate;

    @Column(name = "atl")
    private Double atl;

    @Column(name = "atl_date")
    @JsonProperty("atl_date")
    private String atlDate;

    @Transient
    private boolean isPersisted;

    @ManyToOne
    @JsonIgnoreProperties(value = { "cryptos", "cryptoUser" }, allowSetters = true)
    private WatchList watchList;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Crypto id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public Crypto symbol(String symbol) {
        this.setSymbol(symbol);
        return this;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return this.name;
    }

    public Crypto name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCurrentPrice() {
        return this.currentPrice;
    }

    public Crypto currentPrice(Double currentPrice) {
        this.setCurrentPrice(currentPrice);
        return this;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Long getMarketCap() {
        return this.marketCap;
    }

    public Crypto marketCap(Long marketCap) {
        this.setMarketCap(marketCap);
        return this;
    }

    public void setMarketCap(Long marketCap) {
        this.marketCap = marketCap;
    }

    public Integer getMarketCapRank() {
        return this.marketCapRank;
    }

    public Crypto marketCapRank(Integer marketCapRank) {
        this.setMarketCapRank(marketCapRank);
        return this;
    }

    public void setMarketCapRank(Integer marketCapRank) {
        this.marketCapRank = marketCapRank;
    }

    public Long getTotalVolume() {
        return this.totalVolume;
    }

    public Crypto totalVolume(Long totalVolume) {
        this.setTotalVolume(totalVolume);
        return this;
    }

    public void setTotalVolume(Long totalVolume) {
        this.totalVolume = totalVolume;
    }

    public Double getHigh24() {
        return this.high24;
    }

    public Crypto high24(Double high24) {
        this.setHigh24(high24);
        return this;
    }

    public void setHigh24(Double high24) {
        this.high24 = high24;
    }

    public Double getLow24() {
        return this.low24;
    }

    public Crypto low24(Double low24) {
        this.setLow24(low24);
        return this;
    }

    public void setLow24(Double low24) {
        this.low24 = low24;
    }

    public Double getPriceChange24() {
        return this.priceChange24;
    }

    public Crypto priceChange24(Double priceChange24) {
        this.setPriceChange24(priceChange24);
        return this;
    }

    public void setPriceChange24(Double priceChange24) {
        this.priceChange24 = priceChange24;
    }

    public Double getPriceChangePercentage24hr() {
        return this.priceChangePercentage24hr;
    }

    public Crypto priceChangePercentage24hr(Double priceChangePercentage24hr) {
        this.setPriceChangePercentage24hr(priceChangePercentage24hr);
        return this;
    }

    public void setPriceChangePercentage24hr(Double priceChangePercentage24hr) {
        this.priceChangePercentage24hr = priceChangePercentage24hr;
    }

    public Long getTotalSupply() {
        return this.totalSupply;
    }

    public Crypto totalSupply(Long totalSupply) {
        this.setTotalSupply(totalSupply);
        return this;
    }

    public void setTotalSupply(Long totalSupply) {
        this.totalSupply = totalSupply;
    }

    public Double getAth() {
        return this.ath;
    }

    public Crypto ath(Double ath) {
        this.setAth(ath);
        return this;
    }

    public void setAth(Double ath) {
        this.ath = ath;
    }

    public String getAthDate() {
        return this.athDate;
    }

    public Crypto athDate(String athDate) {
        this.setAthDate(athDate);
        return this;
    }

    public void setAthDate(String athDate) {
        this.athDate = athDate;
    }

    public Double getAtl() {
        return this.atl;
    }

    public Crypto atl(Double atl) {
        this.setAtl(atl);
        return this;
    }

    public void setAtl(Double atl) {
        this.atl = atl;
    }

    public String getAtlDate() {
        return this.atlDate;
    }

    public Crypto atlDate(String atlDate) {
        this.setAtlDate(atlDate);
        return this;
    }

    public void setAtlDate(String atlDate) {
        this.atlDate = atlDate;
    }

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public Crypto setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    public WatchList getWatchList() {
        return this.watchList;
    }

    public void setWatchList(WatchList watchList) {
        this.watchList = watchList;
    }

    public Crypto watchList(WatchList watchList) {
        this.setWatchList(watchList);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Crypto)) {
            return false;
        }
        return id != null && id.equals(((Crypto) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Crypto{" +
            "id=" + getId() +
            ", symbol='" + getSymbol() + "'" +
            ", name='" + getName() + "'" +
            ", currentPrice=" + getCurrentPrice() +
            ", marketCap=" + getMarketCap() +
            ", marketCapRank=" + getMarketCapRank() +
            ", totalVolume=" + getTotalVolume() +
            ", high24=" + getHigh24() +
            ", low24=" + getLow24() +
            ", priceChange24=" + getPriceChange24() +
            ", priceChangePercentage24hr=" + getPriceChangePercentage24hr() +
            ", totalSupply=" + getTotalSupply() +
            ", ath=" + getAth() +
            ", athDate='" + getAthDate() + "'" +
            ", atl=" + getAtl() +
            ", atlDate='" + getAtlDate() + "'" +
            "}";
    }
}
