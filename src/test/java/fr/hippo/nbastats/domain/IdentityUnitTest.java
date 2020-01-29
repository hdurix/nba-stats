package fr.hippo.nbastats.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class IdentityUnitTest {

    @Test
    public void shouldGetId() {
        assertThat(defaultIdentity().getId()).isEqualTo(42);
    }

    @Test
    public void shouldHaveEmptyIdentity() {
        assertThat(new Identity(42, null, null)).hasToString(" .         ");
    }

    @Test
    public void shouldHaveTruncatedLongName() {
        assertThat(new Identity(42, "Giannis", "Antetokounmpo")).hasToString("G. Antetoko");
    }

    @Test
    public void shouldHaveFullName() {
        assertThat(new Identity(42, "Brook", "Lopez")).hasToString("B. Lopez   ");
    }

    public static Identity defaultIdentity() {
        return new Identity(42, "Brook", "Lopez");
    }
}
