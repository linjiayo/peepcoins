package com.peepcoins.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.peepcoins.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CryptoTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Crypto.class);
        Crypto crypto1 = new Crypto();
        crypto1.setId("id1");
        Crypto crypto2 = new Crypto();
        crypto2.setId(crypto1.getId());
        assertThat(crypto1).isEqualTo(crypto2);
        crypto2.setId("id2");
        assertThat(crypto1).isNotEqualTo(crypto2);
        crypto1.setId(null);
        assertThat(crypto1).isNotEqualTo(crypto2);
    }
}
