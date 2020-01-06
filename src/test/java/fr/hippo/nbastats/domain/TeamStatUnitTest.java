package fr.hippo.nbastats.domain;

import static fr.hippo.nbastats.domain.PlayerStatUnitTest.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

public class TeamStatUnitTest {

    @Test
    public void shouldNotBuildWithoutName() {
        assertThatThrownBy(() -> new TeamStat(null, 42, players()))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("name");
    }

    @Test
    public void shouldNotBuildWithoutPlayers() {
        assertThatThrownBy(() -> new TeamStat(TeamName.DETROIT, 42, null))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("players");
    }

    @Test
    public void shouldGetName() {
        assertThat(detroit().getName()).isEqualTo(TeamName.DETROIT);
    }

    @Test
    public void shouldGetScore() {
        assertThat(detroit().getScore()).isEqualTo(124);
    }

    @Test
    public void shouldGetUnmodifiablePlayers() {
        assertThatThrownBy(() -> detroit().getPlayers().clear()).isExactlyInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    public void shouldGetPlayersOrderedByEvaluation() {
        List<PlayerStat> players = detroit().getPlayers();

        assertThat(players)
            .extracting(PlayerStat::toString)
            .containsExactly(brookLopez().toString(), jeremyLamb().toString(), moBamba().toString(), didNotPlayed().toString());
    }

    @Test
    public void shouldHaveFullToString() {
        assertThat(detroit()).hasToString("------ Detroit Pistons ------\n" + brookLopez() + "\n\n" + jeremyLamb() + "\n\n" + moBamba());
    }

    public static TeamStat detroit() {
        return new TeamStat(TeamName.DETROIT, 124, players());
    }

    public static TeamStat indiana() {
        return new TeamStat(TeamName.INDIANA, 123, players());
    }

    private static List<PlayerStat> players() {
        return List.of(jeremyLamb(), didNotPlayed(), brookLopez(), moBamba());
    }
}
