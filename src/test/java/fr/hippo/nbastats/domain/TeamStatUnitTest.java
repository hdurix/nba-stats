package fr.hippo.nbastats.domain;

import static fr.hippo.nbastats.domain.PlayerStatUnitTest.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

public class TeamStatUnitTest {

    @Test
    public void shouldNotBuildWithoutFilter() {
        assertThatThrownBy(() -> fullTeamStat().filter(null).build())
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("filter");
    }

    @Test
    public void shouldNotBuildWithoutName() {
        assertThatThrownBy(() -> fullTeamStat().name(null).build())
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("name");
    }

    @Test
    public void shouldNotBuildWithoutPlayers() {
        assertThatThrownBy(() -> fullTeamStat().players(null).build())
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

    @Test
    public void shouldHaveFilteredToString() {
        assertThat(detroitFiltered()).hasToString("------ Detroit Pistons ------\n" + brookLopez() + "\n\n" + moBamba());
    }

    public static TeamStat detroit() {
        return fullTeamStat().build();
    }

    public static TeamStat detroitFiltered() {
        return fullTeamStat().filter(new StatFilter(50, List.of(MO_BAMBA_ID, NOT_PLAYING_ID))).build();
    }

    public static TeamStat indiana() {
        return fullTeamStat().name(TeamName.INDIANA).score(123).build();
    }

    public static TeamStat.TeamStatBuilder fullTeamStat() {
        return TeamStat.builder().filter(statFilter()).name(TeamName.DETROIT).score(124).players(players());
    }

    private static StatFilter statFilter() {
        return StatFilterUnitTest.empty();
    }

    private static List<PlayerStat> players() {
        return List.of(jeremyLamb(), didNotPlayed(), brookLopez(), moBamba());
    }
}
