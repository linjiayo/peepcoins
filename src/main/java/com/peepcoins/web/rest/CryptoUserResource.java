package com.peepcoins.web.rest;

import com.peepcoins.domain.CryptoUser;
import com.peepcoins.repository.CryptoUserRepository;
import com.peepcoins.service.CryptoUserService;
import com.peepcoins.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.peepcoins.domain.CryptoUser}.
 */
@RestController
@RequestMapping("/api")
public class CryptoUserResource {

    private final Logger log = LoggerFactory.getLogger(CryptoUserResource.class);

    private static final String ENTITY_NAME = "cryptoUser";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CryptoUserService cryptoUserService;

    private final CryptoUserRepository cryptoUserRepository;

    public CryptoUserResource(CryptoUserService cryptoUserService, CryptoUserRepository cryptoUserRepository) {
        this.cryptoUserService = cryptoUserService;
        this.cryptoUserRepository = cryptoUserRepository;
    }

    /**
     * {@code POST  /crypto-users} : Create a new cryptoUser.
     *
     * @param cryptoUser the cryptoUser to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cryptoUser, or with status {@code 400 (Bad Request)} if the cryptoUser has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/crypto-users")
    public ResponseEntity<CryptoUser> createCryptoUser(@RequestBody CryptoUser cryptoUser) throws URISyntaxException {
        log.debug("REST request to save CryptoUser : {}", cryptoUser);
        if (cryptoUser.getId() != null) {
            throw new BadRequestAlertException("A new cryptoUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CryptoUser result = cryptoUserService.save(cryptoUser);
        return ResponseEntity
            .created(new URI("/api/crypto-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /crypto-users/:id} : Updates an existing cryptoUser.
     *
     * @param id the id of the cryptoUser to save.
     * @param cryptoUser the cryptoUser to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cryptoUser,
     * or with status {@code 400 (Bad Request)} if the cryptoUser is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cryptoUser couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/crypto-users/{id}")
    public ResponseEntity<CryptoUser> updateCryptoUser(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CryptoUser cryptoUser
    ) throws URISyntaxException {
        log.debug("REST request to update CryptoUser : {}, {}", id, cryptoUser);
        if (cryptoUser.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, cryptoUser.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!cryptoUserRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CryptoUser result = cryptoUserService.update(cryptoUser);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, cryptoUser.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /crypto-users/:id} : Partial updates given fields of an existing cryptoUser, field will ignore if it is null
     *
     * @param id the id of the cryptoUser to save.
     * @param cryptoUser the cryptoUser to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cryptoUser,
     * or with status {@code 400 (Bad Request)} if the cryptoUser is not valid,
     * or with status {@code 404 (Not Found)} if the cryptoUser is not found,
     * or with status {@code 500 (Internal Server Error)} if the cryptoUser couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/crypto-users/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CryptoUser> partialUpdateCryptoUser(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CryptoUser cryptoUser
    ) throws URISyntaxException {
        log.debug("REST request to partial update CryptoUser partially : {}, {}", id, cryptoUser);
        if (cryptoUser.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, cryptoUser.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!cryptoUserRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CryptoUser> result = cryptoUserService.partialUpdate(cryptoUser);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, cryptoUser.getId().toString())
        );
    }

    /**
     * {@code GET  /crypto-users} : get all the cryptoUsers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cryptoUsers in body.
     */
    @GetMapping("/crypto-users")
    public List<CryptoUser> getAllCryptoUsers() {
        log.debug("REST request to get all CryptoUsers");
        return cryptoUserService.findAll();
    }

    /**
     * {@code GET  /crypto-users/:id} : get the "id" cryptoUser.
     *
     * @param id the id of the cryptoUser to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cryptoUser, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/crypto-users/{id}")
    public ResponseEntity<CryptoUser> getCryptoUser(@PathVariable Long id) {
        log.debug("REST request to get CryptoUser : {}", id);
        Optional<CryptoUser> cryptoUser = cryptoUserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cryptoUser);
    }

    /**
     * {@code DELETE  /crypto-users/:id} : delete the "id" cryptoUser.
     *
     * @param id the id of the cryptoUser to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/crypto-users/{id}")
    public ResponseEntity<Void> deleteCryptoUser(@PathVariable Long id) {
        log.debug("REST request to delete CryptoUser : {}", id);
        cryptoUserService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
