package com.peepcoins.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.peepcoins.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CryptoUserTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CryptoUser.class);
        CryptoUser cryptoUser1 = new CryptoUser();
        cryptoUser1.setId(1L);
        CryptoUser cryptoUser2 = new CryptoUser();
        cryptoUser2.setId(cryptoUser1.getId());
        assertThat(cryptoUser1).isEqualTo(cryptoUser2);
        cryptoUser2.setId(2L);
        assertThat(cryptoUser1).isNotEqualTo(cryptoUser2);
        cryptoUser1.setId(null);
        assertThat(cryptoUser1).isNotEqualTo(cryptoUser2);
    }
}
