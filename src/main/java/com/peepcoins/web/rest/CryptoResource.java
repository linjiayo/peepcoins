package com.peepcoins.web.rest;

import com.peepcoins.domain.Crypto;
import com.peepcoins.repository.CryptoRepository;
import com.peepcoins.service.CryptoService;
import com.peepcoins.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.peepcoins.domain.Crypto}.
 */
@RestController
@RequestMapping("/api")
public class CryptoResource {

    private final Logger log = LoggerFactory.getLogger(CryptoResource.class);

    private static final String ENTITY_NAME = "crypto";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CryptoService cryptoService;

    private final CryptoRepository cryptoRepository;

    public CryptoResource(CryptoService cryptoService, CryptoRepository cryptoRepository) {
        this.cryptoService = cryptoService;
        this.cryptoRepository = cryptoRepository;
    }

    /**
     * {@code POST  /cryptos} : Create a new crypto.
     *
     * @param crypto the crypto to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new crypto, or with status {@code 400 (Bad Request)} if the crypto has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cryptos")
    public ResponseEntity<Crypto> createCrypto(@Valid @RequestBody Crypto crypto) throws URISyntaxException {
        log.debug("REST request to save Crypto : {}", crypto);
        if (crypto.getId() != null) {
            throw new BadRequestAlertException("A new crypto cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Crypto result = cryptoService.save(crypto);
        return ResponseEntity
            .created(new URI("/api/cryptos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /cryptos/:id} : Updates an existing crypto.
     *
     * @param id the id of the crypto to save.
     * @param crypto the crypto to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated crypto,
     * or with status {@code 400 (Bad Request)} if the crypto is not valid,
     * or with status {@code 500 (Internal Server Error)} if the crypto couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cryptos/{id}")
    public ResponseEntity<Crypto> updateCrypto(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody Crypto crypto
    ) throws URISyntaxException {
        log.debug("REST request to update Crypto : {}, {}", id, crypto);
        if (crypto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, crypto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!cryptoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Crypto result = cryptoService.update(crypto);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, crypto.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /cryptos/:id} : Partial updates given fields of an existing crypto, field will ignore if it is null
     *
     * @param id the id of the crypto to save.
     * @param crypto the crypto to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated crypto,
     * or with status {@code 400 (Bad Request)} if the crypto is not valid,
     * or with status {@code 404 (Not Found)} if the crypto is not found,
     * or with status {@code 500 (Internal Server Error)} if the crypto couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/cryptos/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Crypto> partialUpdateCrypto(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody Crypto crypto
    ) throws URISyntaxException {
        log.debug("REST request to partial update Crypto partially : {}, {}", id, crypto);
        if (crypto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, crypto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!cryptoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Crypto> result = cryptoService.partialUpdate(crypto);

        return ResponseUtil.wrapOrNotFound(result, HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, crypto.getId()));
    }

    /**
     * {@code GET  /cryptos} : get all the cryptos.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cryptos in body.
     */
    @GetMapping("/cryptos")
    public ResponseEntity<List<Crypto>> getAllCryptos(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Cryptos");
        Page<Crypto> page = cryptoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /cryptos/:id} : get the "id" crypto.
     *
     * @param id the id of the crypto to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the crypto, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cryptos/{id}")
    public ResponseEntity<Crypto> getCrypto(@PathVariable String id) {
        log.debug("REST request to get Crypto : {}", id);
        Optional<Crypto> crypto = cryptoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(crypto);
    }

    /**
     * {@code DELETE  /cryptos/:id} : delete the "id" crypto.
     *
     * @param id the id of the crypto to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cryptos/{id}")
    public ResponseEntity<Void> deleteCrypto(@PathVariable String id) {
        log.debug("REST request to delete Crypto : {}", id);
        cryptoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id)).build();
    }
}
