package fr.hippo.nbastats.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IdentityUnitTest {

    @Test
    void shouldGetId() {
        assertThat(defaultIdentity().getId()).isEqualTo(42);
    }

    @Test
    void shouldHaveEmptyIdentity() {
        assertThat(new Identity(42, null, null)).hasToString(" .         ");
    }

    @Test
    void shouldHaveTruncatedLongName() {
        assertThat(new Identity(42, "Giannis", "Antetokounmpo")).hasToString("G. Antetoko");
    }

    @Test
    void shouldHaveFullName() {
        assertThat(new Identity(42, "Brook", "Lopez")).hasToString("B. Lopez   ");
    }

    static Identity defaultIdentity() {
        return new Identity(42, "Brook", "Lopez");
    }
}
