package com.peepcoins.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A CryptoUser.
 */
@Entity
@Table(name = "crypto_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CryptoUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(unique = true)
    private User internalUser;

    @OneToMany(mappedBy = "cryptoUser")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "cryptos", "cryptoUser" }, allowSetters = true)
    private Set<WatchList> watchLists = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public CryptoUser id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getInternalUser() {
        return this.internalUser;
    }

    public void setInternalUser(User user) {
        this.internalUser = user;
    }

    public CryptoUser internalUser(User user) {
        this.setInternalUser(user);
        return this;
    }

    public Set<WatchList> getWatchLists() {
        return this.watchLists;
    }

    public void setWatchLists(Set<WatchList> watchLists) {
        if (this.watchLists != null) {
            this.watchLists.forEach(i -> i.setCryptoUser(null));
        }
        if (watchLists != null) {
            watchLists.forEach(i -> i.setCryptoUser(this));
        }
        this.watchLists = watchLists;
    }

    public CryptoUser watchLists(Set<WatchList> watchLists) {
        this.setWatchLists(watchLists);
        return this;
    }

    public CryptoUser addWatchList(WatchList watchList) {
        this.watchLists.add(watchList);
        watchList.setCryptoUser(this);
        return this;
    }

    public CryptoUser removeWatchList(WatchList watchList) {
        this.watchLists.remove(watchList);
        watchList.setCryptoUser(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CryptoUser)) {
            return false;
        }
        return id != null && id.equals(((CryptoUser) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CryptoUser{" +
            "id=" + getId() +
            "}";
    }
}
