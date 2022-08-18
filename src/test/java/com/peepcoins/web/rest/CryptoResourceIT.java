package com.peepcoins.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.peepcoins.IntegrationTest;
import com.peepcoins.domain.Crypto;
import com.peepcoins.repository.CryptoRepository;
import java.util.List;
import java.util.UUID;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link CryptoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CryptoResourceIT {

    private static final String DEFAULT_SYMBOL = "AAAAAAAAAA";
    private static final String UPDATED_SYMBOL = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Double DEFAULT_CURRENT_PRICE = 1D;
    private static final Double UPDATED_CURRENT_PRICE = 2D;

    private static final Long DEFAULT_MARKET_CAP = 1L;
    private static final Long UPDATED_MARKET_CAP = 2L;

    private static final Integer DEFAULT_MARKET_CAP_RANK = 1;
    private static final Integer UPDATED_MARKET_CAP_RANK = 2;

    private static final Long DEFAULT_TOTAL_VOLUME = 1L;
    private static final Long UPDATED_TOTAL_VOLUME = 2L;

    private static final Double DEFAULT_HIGH_24 = 1D;
    private static final Double UPDATED_HIGH_24 = 2D;

    private static final Double DEFAULT_LOW_24 = 1D;
    private static final Double UPDATED_LOW_24 = 2D;

    private static final Double DEFAULT_PRICE_CHANGE_24 = 1D;
    private static final Double UPDATED_PRICE_CHANGE_24 = 2D;

    private static final Double DEFAULT_PRICE_CHANGE_PERCENTAGE_24_HR = 1D;
    private static final Double UPDATED_PRICE_CHANGE_PERCENTAGE_24_HR = 2D;

    private static final Long DEFAULT_TOTAL_SUPPLY = 1L;
    private static final Long UPDATED_TOTAL_SUPPLY = 2L;

    private static final Double DEFAULT_ATH = 1D;
    private static final Double UPDATED_ATH = 2D;

    private static final String DEFAULT_ATH_DATE = "AAAAAAAAAA";
    private static final String UPDATED_ATH_DATE = "BBBBBBBBBB";

    private static final Double DEFAULT_ATL = 1D;
    private static final Double UPDATED_ATL = 2D;

    private static final String DEFAULT_ATL_DATE = "AAAAAAAAAA";
    private static final String UPDATED_ATL_DATE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/cryptos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private CryptoRepository cryptoRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCryptoMockMvc;

    private Crypto crypto;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Crypto createEntity(EntityManager em) {
        Crypto crypto = new Crypto()
            .symbol(DEFAULT_SYMBOL)
            .name(DEFAULT_NAME)
            .currentPrice(DEFAULT_CURRENT_PRICE)
            .marketCap(DEFAULT_MARKET_CAP)
            .marketCapRank(DEFAULT_MARKET_CAP_RANK)
            .totalVolume(DEFAULT_TOTAL_VOLUME)
            .high24(DEFAULT_HIGH_24)
            .low24(DEFAULT_LOW_24)
            .priceChange24(DEFAULT_PRICE_CHANGE_24)
            .priceChangePercentage24hr(DEFAULT_PRICE_CHANGE_PERCENTAGE_24_HR)
            .totalSupply(DEFAULT_TOTAL_SUPPLY)
            .ath(DEFAULT_ATH)
            .athDate(DEFAULT_ATH_DATE)
            .atl(DEFAULT_ATL)
            .atlDate(DEFAULT_ATL_DATE);
        return crypto;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Crypto createUpdatedEntity(EntityManager em) {
        Crypto crypto = new Crypto()
            .symbol(UPDATED_SYMBOL)
            .name(UPDATED_NAME)
            .currentPrice(UPDATED_CURRENT_PRICE)
            .marketCap(UPDATED_MARKET_CAP)
            .marketCapRank(UPDATED_MARKET_CAP_RANK)
            .totalVolume(UPDATED_TOTAL_VOLUME)
            .high24(UPDATED_HIGH_24)
            .low24(UPDATED_LOW_24)
            .priceChange24(UPDATED_PRICE_CHANGE_24)
            .priceChangePercentage24hr(UPDATED_PRICE_CHANGE_PERCENTAGE_24_HR)
            .totalSupply(UPDATED_TOTAL_SUPPLY)
            .ath(UPDATED_ATH)
            .athDate(UPDATED_ATH_DATE)
            .atl(UPDATED_ATL)
            .atlDate(UPDATED_ATL_DATE);
        return crypto;
    }

    @BeforeEach
    public void initTest() {
        crypto = createEntity(em);
    }

    @Test
    @Transactional
    void createCrypto() throws Exception {
        int databaseSizeBeforeCreate = cryptoRepository.findAll().size();
        // Create the Crypto
        restCryptoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(crypto)))
            .andExpect(status().isCreated());

        // Validate the Crypto in the database
        List<Crypto> cryptoList = cryptoRepository.findAll();
        assertThat(cryptoList).hasSize(databaseSizeBeforeCreate + 1);
        Crypto testCrypto = cryptoList.get(cryptoList.size() - 1);
        assertThat(testCrypto.getSymbol()).isEqualTo(DEFAULT_SYMBOL);
        assertThat(testCrypto.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCrypto.getCurrentPrice()).isEqualTo(DEFAULT_CURRENT_PRICE);
        assertThat(testCrypto.getMarketCap()).isEqualTo(DEFAULT_MARKET_CAP);
        assertThat(testCrypto.getMarketCapRank()).isEqualTo(DEFAULT_MARKET_CAP_RANK);
        assertThat(testCrypto.getTotalVolume()).isEqualTo(DEFAULT_TOTAL_VOLUME);
        assertThat(testCrypto.getHigh24()).isEqualTo(DEFAULT_HIGH_24);
        assertThat(testCrypto.getLow24()).isEqualTo(DEFAULT_LOW_24);
        assertThat(testCrypto.getPriceChange24()).isEqualTo(DEFAULT_PRICE_CHANGE_24);
        assertThat(testCrypto.getPriceChangePercentage24hr()).isEqualTo(DEFAULT_PRICE_CHANGE_PERCENTAGE_24_HR);
        assertThat(testCrypto.getTotalSupply()).isEqualTo(DEFAULT_TOTAL_SUPPLY);
        assertThat(testCrypto.getAth()).isEqualTo(DEFAULT_ATH);
        assertThat(testCrypto.getAthDate()).isEqualTo(DEFAULT_ATH_DATE);
        assertThat(testCrypto.getAtl()).isEqualTo(DEFAULT_ATL);
        assertThat(testCrypto.getAtlDate()).isEqualTo(DEFAULT_ATL_DATE);
    }

    @Test
    @Transactional
    void createCryptoWithExistingId() throws Exception {
        // Create the Crypto with an existing ID
        crypto.setId("existing_id");

        int databaseSizeBeforeCreate = cryptoRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCryptoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(crypto)))
            .andExpect(status().isBadRequest());

        // Validate the Crypto in the database
        List<Crypto> cryptoList = cryptoRepository.findAll();
        assertThat(cryptoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = cryptoRepository.findAll().size();
        // set the field null
        crypto.setName(null);

        // Create the Crypto, which fails.

        restCryptoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(crypto)))
            .andExpect(status().isBadRequest());

        List<Crypto> cryptoList = cryptoRepository.findAll();
        assertThat(cryptoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllCryptos() throws Exception {
        // Initialize the database
        crypto.setId(UUID.randomUUID().toString());
        cryptoRepository.saveAndFlush(crypto);

        // Get all the cryptoList
        restCryptoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(crypto.getId())))
            .andExpect(jsonPath("$.[*].symbol").value(hasItem(DEFAULT_SYMBOL)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].currentPrice").value(hasItem(DEFAULT_CURRENT_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].marketCap").value(hasItem(DEFAULT_MARKET_CAP.intValue())))
            .andExpect(jsonPath("$.[*].marketCapRank").value(hasItem(DEFAULT_MARKET_CAP_RANK)))
            .andExpect(jsonPath("$.[*].totalVolume").value(hasItem(DEFAULT_TOTAL_VOLUME.intValue())))
            .andExpect(jsonPath("$.[*].high24").value(hasItem(DEFAULT_HIGH_24.doubleValue())))
            .andExpect(jsonPath("$.[*].low24").value(hasItem(DEFAULT_LOW_24.doubleValue())))
            .andExpect(jsonPath("$.[*].priceChange24").value(hasItem(DEFAULT_PRICE_CHANGE_24.doubleValue())))
            .andExpect(jsonPath("$.[*].priceChangePercentage24hr").value(hasItem(DEFAULT_PRICE_CHANGE_PERCENTAGE_24_HR.doubleValue())))
            .andExpect(jsonPath("$.[*].totalSupply").value(hasItem(DEFAULT_TOTAL_SUPPLY.intValue())))
            .andExpect(jsonPath("$.[*].ath").value(hasItem(DEFAULT_ATH.doubleValue())))
            .andExpect(jsonPath("$.[*].athDate").value(hasItem(DEFAULT_ATH_DATE)))
            .andExpect(jsonPath("$.[*].atl").value(hasItem(DEFAULT_ATL.doubleValue())))
            .andExpect(jsonPath("$.[*].atlDate").value(hasItem(DEFAULT_ATL_DATE)));
    }

    @Test
    @Transactional
    void getCrypto() throws Exception {
        // Initialize the database
        crypto.setId(UUID.randomUUID().toString());
        cryptoRepository.saveAndFlush(crypto);

        // Get the crypto
        restCryptoMockMvc
            .perform(get(ENTITY_API_URL_ID, crypto.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(crypto.getId()))
            .andExpect(jsonPath("$.symbol").value(DEFAULT_SYMBOL))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.currentPrice").value(DEFAULT_CURRENT_PRICE.doubleValue()))
            .andExpect(jsonPath("$.marketCap").value(DEFAULT_MARKET_CAP.intValue()))
            .andExpect(jsonPath("$.marketCapRank").value(DEFAULT_MARKET_CAP_RANK))
            .andExpect(jsonPath("$.totalVolume").value(DEFAULT_TOTAL_VOLUME.intValue()))
            .andExpect(jsonPath("$.high24").value(DEFAULT_HIGH_24.doubleValue()))
            .andExpect(jsonPath("$.low24").value(DEFAULT_LOW_24.doubleValue()))
            .andExpect(jsonPath("$.priceChange24").value(DEFAULT_PRICE_CHANGE_24.doubleValue()))
            .andExpect(jsonPath("$.priceChangePercentage24hr").value(DEFAULT_PRICE_CHANGE_PERCENTAGE_24_HR.doubleValue()))
            .andExpect(jsonPath("$.totalSupply").value(DEFAULT_TOTAL_SUPPLY.intValue()))
            .andExpect(jsonPath("$.ath").value(DEFAULT_ATH.doubleValue()))
            .andExpect(jsonPath("$.athDate").value(DEFAULT_ATH_DATE))
            .andExpect(jsonPath("$.atl").value(DEFAULT_ATL.doubleValue()))
            .andExpect(jsonPath("$.atlDate").value(DEFAULT_ATL_DATE));
    }

    @Test
    @Transactional
    void getNonExistingCrypto() throws Exception {
        // Get the crypto
        restCryptoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewCrypto() throws Exception {
        // Initialize the database
        crypto.setId(UUID.randomUUID().toString());
        cryptoRepository.saveAndFlush(crypto);

        int databaseSizeBeforeUpdate = cryptoRepository.findAll().size();

        // Update the crypto
        Crypto updatedCrypto = cryptoRepository.findById(crypto.getId()).get();
        // Disconnect from session so that the updates on updatedCrypto are not directly saved in db
        em.detach(updatedCrypto);
        updatedCrypto
            .symbol(UPDATED_SYMBOL)
            .name(UPDATED_NAME)
            .currentPrice(UPDATED_CURRENT_PRICE)
            .marketCap(UPDATED_MARKET_CAP)
            .marketCapRank(UPDATED_MARKET_CAP_RANK)
            .totalVolume(UPDATED_TOTAL_VOLUME)
            .high24(UPDATED_HIGH_24)
            .low24(UPDATED_LOW_24)
            .priceChange24(UPDATED_PRICE_CHANGE_24)
            .priceChangePercentage24hr(UPDATED_PRICE_CHANGE_PERCENTAGE_24_HR)
            .totalSupply(UPDATED_TOTAL_SUPPLY)
            .ath(UPDATED_ATH)
            .athDate(UPDATED_ATH_DATE)
            .atl(UPDATED_ATL)
            .atlDate(UPDATED_ATL_DATE);

        restCryptoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedCrypto.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedCrypto))
            )
            .andExpect(status().isOk());

        // Validate the Crypto in the database
        List<Crypto> cryptoList = cryptoRepository.findAll();
        assertThat(cryptoList).hasSize(databaseSizeBeforeUpdate);
        Crypto testCrypto = cryptoList.get(cryptoList.size() - 1);
        assertThat(testCrypto.getSymbol()).isEqualTo(UPDATED_SYMBOL);
        assertThat(testCrypto.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCrypto.getCurrentPrice()).isEqualTo(UPDATED_CURRENT_PRICE);
        assertThat(testCrypto.getMarketCap()).isEqualTo(UPDATED_MARKET_CAP);
        assertThat(testCrypto.getMarketCapRank()).isEqualTo(UPDATED_MARKET_CAP_RANK);
        assertThat(testCrypto.getTotalVolume()).isEqualTo(UPDATED_TOTAL_VOLUME);
        assertThat(testCrypto.getHigh24()).isEqualTo(UPDATED_HIGH_24);
        assertThat(testCrypto.getLow24()).isEqualTo(UPDATED_LOW_24);
        assertThat(testCrypto.getPriceChange24()).isEqualTo(UPDATED_PRICE_CHANGE_24);
        assertThat(testCrypto.getPriceChangePercentage24hr()).isEqualTo(UPDATED_PRICE_CHANGE_PERCENTAGE_24_HR);
        assertThat(testCrypto.getTotalSupply()).isEqualTo(UPDATED_TOTAL_SUPPLY);
        assertThat(testCrypto.getAth()).isEqualTo(UPDATED_ATH);
        assertThat(testCrypto.getAthDate()).isEqualTo(UPDATED_ATH_DATE);
        assertThat(testCrypto.getAtl()).isEqualTo(UPDATED_ATL);
        assertThat(testCrypto.getAtlDate()).isEqualTo(UPDATED_ATL_DATE);
    }

    @Test
    @Transactional
    void putNonExistingCrypto() throws Exception {
        int databaseSizeBeforeUpdate = cryptoRepository.findAll().size();
        crypto.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCryptoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, crypto.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(crypto))
            )
            .andExpect(status().isBadRequest());

        // Validate the Crypto in the database
        List<Crypto> cryptoList = cryptoRepository.findAll();
        assertThat(cryptoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCrypto() throws Exception {
        int databaseSizeBeforeUpdate = cryptoRepository.findAll().size();
        crypto.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCryptoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(crypto))
            )
            .andExpect(status().isBadRequest());

        // Validate the Crypto in the database
        List<Crypto> cryptoList = cryptoRepository.findAll();
        assertThat(cryptoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCrypto() throws Exception {
        int databaseSizeBeforeUpdate = cryptoRepository.findAll().size();
        crypto.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCryptoMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(crypto)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Crypto in the database
        List<Crypto> cryptoList = cryptoRepository.findAll();
        assertThat(cryptoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCryptoWithPatch() throws Exception {
        // Initialize the database
        crypto.setId(UUID.randomUUID().toString());
        cryptoRepository.saveAndFlush(crypto);

        int databaseSizeBeforeUpdate = cryptoRepository.findAll().size();

        // Update the crypto using partial update
        Crypto partialUpdatedCrypto = new Crypto();
        partialUpdatedCrypto.setId(crypto.getId());

        partialUpdatedCrypto
            .marketCapRank(UPDATED_MARKET_CAP_RANK)
            .totalVolume(UPDATED_TOTAL_VOLUME)
            .priceChangePercentage24hr(UPDATED_PRICE_CHANGE_PERCENTAGE_24_HR)
            .totalSupply(UPDATED_TOTAL_SUPPLY)
            .ath(UPDATED_ATH)
            .athDate(UPDATED_ATH_DATE)
            .atl(UPDATED_ATL)
            .atlDate(UPDATED_ATL_DATE);

        restCryptoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCrypto.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCrypto))
            )
            .andExpect(status().isOk());

        // Validate the Crypto in the database
        List<Crypto> cryptoList = cryptoRepository.findAll();
        assertThat(cryptoList).hasSize(databaseSizeBeforeUpdate);
        Crypto testCrypto = cryptoList.get(cryptoList.size() - 1);
        assertThat(testCrypto.getSymbol()).isEqualTo(DEFAULT_SYMBOL);
        assertThat(testCrypto.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCrypto.getCurrentPrice()).isEqualTo(DEFAULT_CURRENT_PRICE);
        assertThat(testCrypto.getMarketCap()).isEqualTo(DEFAULT_MARKET_CAP);
        assertThat(testCrypto.getMarketCapRank()).isEqualTo(UPDATED_MARKET_CAP_RANK);
        assertThat(testCrypto.getTotalVolume()).isEqualTo(UPDATED_TOTAL_VOLUME);
        assertThat(testCrypto.getHigh24()).isEqualTo(DEFAULT_HIGH_24);
        assertThat(testCrypto.getLow24()).isEqualTo(DEFAULT_LOW_24);
        assertThat(testCrypto.getPriceChange24()).isEqualTo(DEFAULT_PRICE_CHANGE_24);
        assertThat(testCrypto.getPriceChangePercentage24hr()).isEqualTo(UPDATED_PRICE_CHANGE_PERCENTAGE_24_HR);
        assertThat(testCrypto.getTotalSupply()).isEqualTo(UPDATED_TOTAL_SUPPLY);
        assertThat(testCrypto.getAth()).isEqualTo(UPDATED_ATH);
        assertThat(testCrypto.getAthDate()).isEqualTo(UPDATED_ATH_DATE);
        assertThat(testCrypto.getAtl()).isEqualTo(UPDATED_ATL);
        assertThat(testCrypto.getAtlDate()).isEqualTo(UPDATED_ATL_DATE);
    }

    @Test
    @Transactional
    void fullUpdateCryptoWithPatch() throws Exception {
        // Initialize the database
        crypto.setId(UUID.randomUUID().toString());
        cryptoRepository.saveAndFlush(crypto);

        int databaseSizeBeforeUpdate = cryptoRepository.findAll().size();

        // Update the crypto using partial update
        Crypto partialUpdatedCrypto = new Crypto();
        partialUpdatedCrypto.setId(crypto.getId());

        partialUpdatedCrypto
            .symbol(UPDATED_SYMBOL)
            .name(UPDATED_NAME)
            .currentPrice(UPDATED_CURRENT_PRICE)
            .marketCap(UPDATED_MARKET_CAP)
            .marketCapRank(UPDATED_MARKET_CAP_RANK)
            .totalVolume(UPDATED_TOTAL_VOLUME)
            .high24(UPDATED_HIGH_24)
            .low24(UPDATED_LOW_24)
            .priceChange24(UPDATED_PRICE_CHANGE_24)
            .priceChangePercentage24hr(UPDATED_PRICE_CHANGE_PERCENTAGE_24_HR)
            .totalSupply(UPDATED_TOTAL_SUPPLY)
            .ath(UPDATED_ATH)
            .athDate(UPDATED_ATH_DATE)
            .atl(UPDATED_ATL)
            .atlDate(UPDATED_ATL_DATE);

        restCryptoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCrypto.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCrypto))
            )
            .andExpect(status().isOk());

        // Validate the Crypto in the database
        List<Crypto> cryptoList = cryptoRepository.findAll();
        assertThat(cryptoList).hasSize(databaseSizeBeforeUpdate);
        Crypto testCrypto = cryptoList.get(cryptoList.size() - 1);
        assertThat(testCrypto.getSymbol()).isEqualTo(UPDATED_SYMBOL);
        assertThat(testCrypto.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCrypto.getCurrentPrice()).isEqualTo(UPDATED_CURRENT_PRICE);
        assertThat(testCrypto.getMarketCap()).isEqualTo(UPDATED_MARKET_CAP);
        assertThat(testCrypto.getMarketCapRank()).isEqualTo(UPDATED_MARKET_CAP_RANK);
        assertThat(testCrypto.getTotalVolume()).isEqualTo(UPDATED_TOTAL_VOLUME);
        assertThat(testCrypto.getHigh24()).isEqualTo(UPDATED_HIGH_24);
        assertThat(testCrypto.getLow24()).isEqualTo(UPDATED_LOW_24);
        assertThat(testCrypto.getPriceChange24()).isEqualTo(UPDATED_PRICE_CHANGE_24);
        assertThat(testCrypto.getPriceChangePercentage24hr()).isEqualTo(UPDATED_PRICE_CHANGE_PERCENTAGE_24_HR);
        assertThat(testCrypto.getTotalSupply()).isEqualTo(UPDATED_TOTAL_SUPPLY);
        assertThat(testCrypto.getAth()).isEqualTo(UPDATED_ATH);
        assertThat(testCrypto.getAthDate()).isEqualTo(UPDATED_ATH_DATE);
        assertThat(testCrypto.getAtl()).isEqualTo(UPDATED_ATL);
        assertThat(testCrypto.getAtlDate()).isEqualTo(UPDATED_ATL_DATE);
    }

    @Test
    @Transactional
    void patchNonExistingCrypto() throws Exception {
        int databaseSizeBeforeUpdate = cryptoRepository.findAll().size();
        crypto.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCryptoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, crypto.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(crypto))
            )
            .andExpect(status().isBadRequest());

        // Validate the Crypto in the database
        List<Crypto> cryptoList = cryptoRepository.findAll();
        assertThat(cryptoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCrypto() throws Exception {
        int databaseSizeBeforeUpdate = cryptoRepository.findAll().size();
        crypto.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCryptoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(crypto))
            )
            .andExpect(status().isBadRequest());

        // Validate the Crypto in the database
        List<Crypto> cryptoList = cryptoRepository.findAll();
        assertThat(cryptoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCrypto() throws Exception {
        int databaseSizeBeforeUpdate = cryptoRepository.findAll().size();
        crypto.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCryptoMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(crypto)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Crypto in the database
        List<Crypto> cryptoList = cryptoRepository.findAll();
        assertThat(cryptoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCrypto() throws Exception {
        // Initialize the database
        crypto.setId(UUID.randomUUID().toString());
        cryptoRepository.saveAndFlush(crypto);

        int databaseSizeBeforeDelete = cryptoRepository.findAll().size();

        // Delete the crypto
        restCryptoMockMvc
            .perform(delete(ENTITY_API_URL_ID, crypto.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Crypto> cryptoList = cryptoRepository.findAll();
        assertThat(cryptoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
