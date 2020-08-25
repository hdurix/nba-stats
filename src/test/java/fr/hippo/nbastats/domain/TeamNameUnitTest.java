package fr.hippo.nbastats.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

class TeamNameUnitTest {

    @Test
    void shouldGetCity() {
        assertThat(TeamName.ORLANDO.city()).isEqualTo("Orlando");
    }

    @Test
    void shouldGetNickname() {
        assertThat(TeamName.ORLANDO.nickname()).isEqualTo("Magic");
    }

    @Test
    void shouldGetFullName() {
        assertThat(TeamName.ORLANDO.fullName()).isEqualTo("Orlando Magic");
    }

    @Test
    void shouldHaveToStringCenteredWithHyphens() {
        assertThat(TeamName.ORLANDO.toString()).isEqualTo("------- Orlando Magic -------");
        assertThat(TeamName.MINNESOTA.toString()).isEqualTo("-- Minnesota Timberwolves ---");

        Arrays.stream(TeamName.values()).map(TeamName::toString).forEach(System.out::println);
    }
}
