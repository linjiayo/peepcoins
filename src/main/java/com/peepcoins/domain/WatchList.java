package com.peepcoins.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A WatchList.
 */
@Entity
@Table(name = "watch_list")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class WatchList implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "watchList")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "watchList" }, allowSetters = true)
    private Set<Crypto> cryptos = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "internalUser", "watchLists" }, allowSetters = true)
    private CryptoUser cryptoUser;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public WatchList id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Crypto> getCryptos() {
        return this.cryptos;
    }

    public void setCryptos(Set<Crypto> cryptos) {
        if (this.cryptos != null) {
            this.cryptos.forEach(i -> i.setWatchList(null));
        }
        if (cryptos != null) {
            cryptos.forEach(i -> i.setWatchList(this));
        }
        this.cryptos = cryptos;
    }

    public WatchList cryptos(Set<Crypto> cryptos) {
        this.setCryptos(cryptos);
        return this;
    }

    public WatchList addCrypto(Crypto crypto) {
        this.cryptos.add(crypto);
        crypto.setWatchList(this);
        return this;
    }

    public WatchList removeCrypto(Crypto crypto) {
        this.cryptos.remove(crypto);
        crypto.setWatchList(null);
        return this;
    }

    public CryptoUser getCryptoUser() {
        return this.cryptoUser;
    }

    public void setCryptoUser(CryptoUser cryptoUser) {
        this.cryptoUser = cryptoUser;
    }

    public WatchList cryptoUser(CryptoUser cryptoUser) {
        this.setCryptoUser(cryptoUser);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WatchList)) {
            return false;
        }
        return id != null && id.equals(((WatchList) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WatchList{" +
            "id=" + getId() +
            "}";
    }
}
