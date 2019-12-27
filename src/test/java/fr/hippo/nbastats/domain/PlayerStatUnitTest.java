package fr.hippo.nbastats.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PlayerStatUnitTest {

    @Test
    public void shouldNotBuildWithoutIdentity() {
        assertThatThrownBy(() -> fullPlayerStat().identity(null).build())
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("identity");
    }

    @Test
    public void shouldNotBuildWithoutFouls() {
        assertThatThrownBy(() -> fullPlayerStat().fouls(null).build())
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("fouls");
    }

    @Test
    public void shouldNotBuildWithoutEvaluation() {
        assertThatThrownBy(() -> fullPlayerStat().evaluation(null).build())
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("evaluation");
    }

    @Test
    public void shouldNotBuildWithoutPoints() {
        assertThatThrownBy(() -> fullPlayerStat().points(null).build())
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("points");
    }

    @Test
    public void shouldNotBuildWithoutRebounds() {
        assertThatThrownBy(() -> fullPlayerStat().rebounds(null).build())
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("rebounds");
    }

    @Test
    public void shouldNotBuildWithoutAssists() {
        assertThatThrownBy(() -> fullPlayerStat().assists(null).build())
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("assists");
    }

    @Test
    public void shouldNotBuildWithoutBlocks() {
        assertThatThrownBy(() -> fullPlayerStat().blocks(null).build())
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("blocks");
    }

    @Test
    public void shouldNotBuildWithoutSteals() {
        assertThatThrownBy(() -> fullPlayerStat().steals(null).build())
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("steals");
    }

    @Test
    public void shouldNotBuildWithoutFieldGoals() {
        assertThatThrownBy(() -> fullPlayerStat().fieldGoals(null).build())
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("fieldGoals");
    }

    @Test
    public void shouldNotBuildWithoutThreePoints() {
        assertThatThrownBy(() -> fullPlayerStat().threePoints(null).build())
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("threePoints");
    }

    @Test
    public void shouldNotBuildWithoutFreeThrows() {
        assertThatThrownBy(() -> fullPlayerStat().freeThrows(null).build())
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("freeThrows");
    }

    @Test
    public void shouldNotBuildWithoutTurnovers() {
        assertThatThrownBy(() -> fullPlayerStat().turnovers(null).build())
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("turnovers");
    }

    @Test
    public void shouldNotBuildWithoutMinutes() {
        assertThatThrownBy(() -> fullPlayerStat().minutes(null).build())
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("minutes");
    }

    @Test
    public void shouldGetEvaluation() {
        assertThat(brookLopez().eval()).isEqualTo(51);
    }

    @Test
    public void shouldHaveFullToString() {
        assertThat(brookLopez()).hasToString("B Lopez*51|42  8 10  4  0\n 9/12 10/13  4/5 | 4|34'");
    }

    public static PlayerStat brookLopez() {
        return fullPlayerStat().build();
    }

    public static PlayerStat jeremyLamb() {
        return fullPlayerStat().identity(new Identity("Jeremy", "Lamb")).evaluation(new TwoDigitNumber(12)).build();
    }

    private static PlayerStat.PlayerStatBuilder fullPlayerStat() {
        return PlayerStat
            .builder()
            .identity(IdentityUnitTest.defaultIdentity())
            .fouls(new Fouls(6))
            .evaluation(new TwoDigitNumber(51))
            .points(new TwoDigitNumber(42))
            .rebounds(new TwoDigitNumber(8))
            .assists(new TwoDigitNumber(10))
            .blocks(new TwoDigitNumber(4))
            .steals(new TwoDigitNumber(0))
            .fieldGoals(new TwoValues(9, 12))
            .threePoints(new TwoValues(10, 13))
            .freeThrows(new TwoValues(4, 5))
            .turnovers(new TwoDigitNumber(4))
            .minutes(new TwoDigitNumber(34));
    }
}
