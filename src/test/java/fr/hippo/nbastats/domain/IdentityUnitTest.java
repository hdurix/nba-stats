package fr.hippo.nbastats.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class IdentityUnitTest {

    @Test
    void shouldGetId() {
        assertThat(defaultIdentity().getId()).isEqualTo(42);
    }

    @Test
    void shouldHaveEmptyIdentity() {
        assertThat(new Identity(42)).hasToString("?. ???     ");
    }

    @Test
    void shouldHaveTruncatedLongName() {
        assertThat(new Identity(42, "Giannis", "Antetokounmpo")).hasToString("G. Antetoko");
    }

    @Test
    void shouldHaveFullName() {
        assertThat(new Identity(42, "Brook", "Lopez")).hasToString("B. Lopez   ");
    }

    @Test
    void shouldBeUnknown() {
        assertThat(unknownIdentity().isUnknown()).isTrue();
    }

    public static Identity unknownIdentity() {
        return new Identity(42);
    }

    public static Identity defaultIdentity() {
        return new Identity(42, "Brook", "Lopez");
    }
}
