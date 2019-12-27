package fr.hippo.nbastats.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class IdentityUnitTest {

    @Test
    public void shouldHaveEmptyIdentity() {
        assertThat(new Identity(null, null)).hasToString("       ");
    }

    @Test
    public void shouldHaveTruncatedLongName() {
        assertThat(new Identity("Giannis", "Antetokounmpo")).hasToString("G Antet");
    }

    @Test
    public void shouldHaveFullName() {
        assertThat(new Identity("Brook", "Lopez")).hasToString("B Lopez");
    }

    @Test
    public void shouldHaveShortName() {
        assertThat(new Identity("Jeremy", "Lamb")).hasToString("J Lamb ");
    }

    public static Identity defaultIdentity() {
        return new Identity("Brook", "Lopez");
    }
}
