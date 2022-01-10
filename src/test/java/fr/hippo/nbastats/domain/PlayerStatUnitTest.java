package fr.hippo.nbastats.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PlayerStatUnitTest {

    static final int JEREMY_LAMB_ID = 10;
    static final int MO_BAMBA_ID = 11;
    static final int CODY_ZELLER_ID = 12;
    static final int NOT_PLAYING_ID = 13;

    @Test
    void shouldNotBuildWithoutIdentity() {
        assertThatThrownBy(() -> fullPlayerStat().identity(null).build())
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("identity");
    }

    @Test
    void shouldNotBuildWithoutFouls() {
        assertThatThrownBy(() -> fullPlayerStat().fouls(null).build())
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("fouls");
    }

    @Test
    void shouldNotBuildWithoutPoints() {
        assertThatThrownBy(() -> fullPlayerStat().points(null).build())
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("points");
    }

    @Test
    void shouldNotBuildWithoutRebounds() {
        assertThatThrownBy(() -> fullPlayerStat().rebounds(null).build())
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("rebounds");
    }

    @Test
    void shouldNotBuildWithoutAssists() {
        assertThatThrownBy(() -> fullPlayerStat().assists(null).build())
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("assists");
    }

    @Test
    void shouldNotBuildWithoutBlocks() {
        assertThatThrownBy(() -> fullPlayerStat().blocks(null).build())
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("blocks");
    }

    @Test
    void shouldNotBuildWithoutSteals() {
        assertThatThrownBy(() -> fullPlayerStat().steals(null).build())
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("steals");
    }

    @Test
    void shouldNotBuildWithoutFieldGoals() {
        assertThatThrownBy(() -> fullPlayerStat().fieldGoals(null).build())
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("fieldGoals");
    }

    @Test
    void shouldNotBuildWithoutThreePoints() {
        assertThatThrownBy(() -> fullPlayerStat().threePoints(null).build())
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("threePoints");
    }

    @Test
    void shouldNotBuildWithoutFreeThrows() {
        assertThatThrownBy(() -> fullPlayerStat().freeThrows(null).build())
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("freeThrows");
    }

    @Test
    void shouldNotBuildWithoutTurnovers() {
        assertThatThrownBy(() -> fullPlayerStat().turnovers(null).build())
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("turnovers");
    }

    @Test
    void shouldNotBuildWithoutMinutes() {
        assertThatThrownBy(() -> fullPlayerStat().minutes(null).build())
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("minutes");
    }

    @Test
    void shouldGetEvaluation() {
        assertThat(brookLopez().eval()).isEqualTo(76);
    }

    @Test
    void shouldGetPlayedWithMinutes() {
        assertThat(brookLopez().played()).isTrue();
    }

    @Test
    void shouldGetNotPlayedWithNoMinute() {
        assertThat(didNotPlayed().played()).isFalse();
    }

    @Test
    void shouldHaveFullToString() {
        assertThat(brookLopez()).hasToString("B. Lopez   *76|42  8 10  4  0\n 9/12 10/13  4/5 | 4|34'");
    }

    @Test
    void shouldGetId() {
        assertThat(moBamba().id()).isEqualTo(MO_BAMBA_ID);
    }

    public static PlayerStat brookLopez() {
        return fullPlayerStat().build();
    }

    public static PlayerStat jeremyLamb() {
        return fullPlayerStat().identity(new Identity(JEREMY_LAMB_ID, "Jeremy", "Lamb")).points(new Stat(20)).build();
    }

    static PlayerStat moBamba() {
        return fullPlayerStat().identity(new Identity(MO_BAMBA_ID, "Mo", "Bamba")).points(new Stat(10)).build();
    }

    static PlayerStat codyZeller() {
        return fullPlayerStat().identity(new Identity(CODY_ZELLER_ID, "Cody", "Zeller")).points(new Stat(0)).build();
    }

    static PlayerStat didNotPlayed() {
        return fullPlayerStat()
            .identity(new Identity(NOT_PLAYING_ID, "Not", "Playing"))
            .points(new Stat(0))
            .rebounds(new Stat(0))
            .minutes(new Stat(0))
            .build();
    }

    static PlayerStat.PlayerStatBuilder fullPlayerStat() {
        return PlayerStat
            .builder()
            .identity(IdentityUnitTest.defaultIdentity())
            .fouls(new Fouls(6))
            .points(new Stat(42))
            .rebounds(new Stat(8))
            .assists(new Stat(10))
            .blocks(new Stat(4))
            .steals(new Stat(0))
            .fieldGoals(new StatTuple(9, 12))
            .threePoints(new StatTuple(10, 13))
            .freeThrows(new StatTuple(4, 5))
            .turnovers(new Stat(4))
            .minutes(new Stat(34));
    }
}
