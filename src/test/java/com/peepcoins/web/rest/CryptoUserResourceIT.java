package com.peepcoins.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.peepcoins.IntegrationTest;
import com.peepcoins.domain.CryptoUser;
import com.peepcoins.repository.CryptoUserRepository;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
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
 * Integration tests for the {@link CryptoUserResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CryptoUserResourceIT {

    private static final String ENTITY_API_URL = "/api/crypto-users";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CryptoUserRepository cryptoUserRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCryptoUserMockMvc;

    private CryptoUser cryptoUser;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CryptoUser createEntity(EntityManager em) {
        CryptoUser cryptoUser = new CryptoUser();
        return cryptoUser;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CryptoUser createUpdatedEntity(EntityManager em) {
        CryptoUser cryptoUser = new CryptoUser();
        return cryptoUser;
    }

    @BeforeEach
    public void initTest() {
        cryptoUser = createEntity(em);
    }

    @Test
    @Transactional
    void createCryptoUser() throws Exception {
        int databaseSizeBeforeCreate = cryptoUserRepository.findAll().size();
        // Create the CryptoUser
        restCryptoUserMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(cryptoUser)))
            .andExpect(status().isCreated());

        // Validate the CryptoUser in the database
        List<CryptoUser> cryptoUserList = cryptoUserRepository.findAll();
        assertThat(cryptoUserList).hasSize(databaseSizeBeforeCreate + 1);
        CryptoUser testCryptoUser = cryptoUserList.get(cryptoUserList.size() - 1);
    }

    @Test
    @Transactional
    void createCryptoUserWithExistingId() throws Exception {
        // Create the CryptoUser with an existing ID
        cryptoUser.setId(1L);

        int databaseSizeBeforeCreate = cryptoUserRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCryptoUserMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(cryptoUser)))
            .andExpect(status().isBadRequest());

        // Validate the CryptoUser in the database
        List<CryptoUser> cryptoUserList = cryptoUserRepository.findAll();
        assertThat(cryptoUserList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCryptoUsers() throws Exception {
        // Initialize the database
        cryptoUserRepository.saveAndFlush(cryptoUser);

        // Get all the cryptoUserList
        restCryptoUserMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cryptoUser.getId().intValue())));
    }

    @Test
    @Transactional
    void getCryptoUser() throws Exception {
        // Initialize the database
        cryptoUserRepository.saveAndFlush(cryptoUser);

        // Get the cryptoUser
        restCryptoUserMockMvc
            .perform(get(ENTITY_API_URL_ID, cryptoUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cryptoUser.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingCryptoUser() throws Exception {
        // Get the cryptoUser
        restCryptoUserMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewCryptoUser() throws Exception {
        // Initialize the database
        cryptoUserRepository.saveAndFlush(cryptoUser);

        int databaseSizeBeforeUpdate = cryptoUserRepository.findAll().size();

        // Update the cryptoUser
        CryptoUser updatedCryptoUser = cryptoUserRepository.findById(cryptoUser.getId()).get();
        // Disconnect from session so that the updates on updatedCryptoUser are not directly saved in db
        em.detach(updatedCryptoUser);

        restCryptoUserMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedCryptoUser.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedCryptoUser))
            )
            .andExpect(status().isOk());

        // Validate the CryptoUser in the database
        List<CryptoUser> cryptoUserList = cryptoUserRepository.findAll();
        assertThat(cryptoUserList).hasSize(databaseSizeBeforeUpdate);
        CryptoUser testCryptoUser = cryptoUserList.get(cryptoUserList.size() - 1);
    }

    @Test
    @Transactional
    void putNonExistingCryptoUser() throws Exception {
        int databaseSizeBeforeUpdate = cryptoUserRepository.findAll().size();
        cryptoUser.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCryptoUserMockMvc
            .perform(
                put(ENTITY_API_URL_ID, cryptoUser.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(cryptoUser))
            )
            .andExpect(status().isBadRequest());

        // Validate the CryptoUser in the database
        List<CryptoUser> cryptoUserList = cryptoUserRepository.findAll();
        assertThat(cryptoUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCryptoUser() throws Exception {
        int databaseSizeBeforeUpdate = cryptoUserRepository.findAll().size();
        cryptoUser.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCryptoUserMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(cryptoUser))
            )
            .andExpect(status().isBadRequest());

        // Validate the CryptoUser in the database
        List<CryptoUser> cryptoUserList = cryptoUserRepository.findAll();
        assertThat(cryptoUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCryptoUser() throws Exception {
        int databaseSizeBeforeUpdate = cryptoUserRepository.findAll().size();
        cryptoUser.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCryptoUserMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(cryptoUser)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CryptoUser in the database
        List<CryptoUser> cryptoUserList = cryptoUserRepository.findAll();
        assertThat(cryptoUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCryptoUserWithPatch() throws Exception {
        // Initialize the database
        cryptoUserRepository.saveAndFlush(cryptoUser);

        int databaseSizeBeforeUpdate = cryptoUserRepository.findAll().size();

        // Update the cryptoUser using partial update
        CryptoUser partialUpdatedCryptoUser = new CryptoUser();
        partialUpdatedCryptoUser.setId(cryptoUser.getId());

        restCryptoUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCryptoUser.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCryptoUser))
            )
            .andExpect(status().isOk());

        // Validate the CryptoUser in the database
        List<CryptoUser> cryptoUserList = cryptoUserRepository.findAll();
        assertThat(cryptoUserList).hasSize(databaseSizeBeforeUpdate);
        CryptoUser testCryptoUser = cryptoUserList.get(cryptoUserList.size() - 1);
    }

    @Test
    @Transactional
    void fullUpdateCryptoUserWithPatch() throws Exception {
        // Initialize the database
        cryptoUserRepository.saveAndFlush(cryptoUser);

        int databaseSizeBeforeUpdate = cryptoUserRepository.findAll().size();

        // Update the cryptoUser using partial update
        CryptoUser partialUpdatedCryptoUser = new CryptoUser();
        partialUpdatedCryptoUser.setId(cryptoUser.getId());

        restCryptoUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCryptoUser.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCryptoUser))
            )
            .andExpect(status().isOk());

        // Validate the CryptoUser in the database
        List<CryptoUser> cryptoUserList = cryptoUserRepository.findAll();
        assertThat(cryptoUserList).hasSize(databaseSizeBeforeUpdate);
        CryptoUser testCryptoUser = cryptoUserList.get(cryptoUserList.size() - 1);
    }

    @Test
    @Transactional
    void patchNonExistingCryptoUser() throws Exception {
        int databaseSizeBeforeUpdate = cryptoUserRepository.findAll().size();
        cryptoUser.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCryptoUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, cryptoUser.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(cryptoUser))
            )
            .andExpect(status().isBadRequest());

        // Validate the CryptoUser in the database
        List<CryptoUser> cryptoUserList = cryptoUserRepository.findAll();
        assertThat(cryptoUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCryptoUser() throws Exception {
        int databaseSizeBeforeUpdate = cryptoUserRepository.findAll().size();
        cryptoUser.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCryptoUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(cryptoUser))
            )
            .andExpect(status().isBadRequest());

        // Validate the CryptoUser in the database
        List<CryptoUser> cryptoUserList = cryptoUserRepository.findAll();
        assertThat(cryptoUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCryptoUser() throws Exception {
        int databaseSizeBeforeUpdate = cryptoUserRepository.findAll().size();
        cryptoUser.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCryptoUserMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(cryptoUser))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CryptoUser in the database
        List<CryptoUser> cryptoUserList = cryptoUserRepository.findAll();
        assertThat(cryptoUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCryptoUser() throws Exception {
        // Initialize the database
        cryptoUserRepository.saveAndFlush(cryptoUser);

        int databaseSizeBeforeDelete = cryptoUserRepository.findAll().size();

        // Delete the cryptoUser
        restCryptoUserMockMvc
            .perform(delete(ENTITY_API_URL_ID, cryptoUser.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CryptoUser> cryptoUserList = cryptoUserRepository.findAll();
        assertThat(cryptoUserList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
