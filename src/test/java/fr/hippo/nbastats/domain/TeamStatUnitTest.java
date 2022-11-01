package fr.hippo.nbastats.domain;

import static fr.hippo.nbastats.domain.PlayerStatUnitTest.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

class TeamStatUnitTest {

    @Test
    void shouldNotBuildWithoutFilter() {
        assertThatThrownBy(() -> fullTeamStat().filter(null).build())
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("filter");
    }

    @Test
    void shouldNotBuildWithoutName() {
        assertThatThrownBy(() -> fullTeamStat().name(null).build())
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("name");
    }

    @Test
    void shouldNotBuildWithoutPlayers() {
        assertThatThrownBy(() -> fullTeamStat().players(null).build())
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("players");
    }

    @Test
    void shouldBuild() {
        TeamStat teamStat = detroit();
        assertThat(teamStat.getName()).isEqualTo(TeamName.DETROIT);
        assertThat(teamStat.getScore()).isEqualTo(124);
        assertThat(teamStat.getWins()).isEqualTo(12);
        assertThat(teamStat.getLosses()).isEqualTo(13);
    }

    @Test
    void shouldGetUnmodifiablePlayers() {
        assertThatThrownBy(() -> detroit().getPlayers().clear()).isExactlyInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void shouldGetPlayersOrderedByEvaluation() {
        List<PlayerStat> players = detroit().getPlayers();

        assertThat(players)
            .extracting(PlayerStat::toString)
            .containsExactly(
                brookLopez().toString(),
                jeremyLamb().toString(),
                moBamba().toString(),
                codyZeller().toString(),
                didNotPlayed().toString()
            );
    }

    @Test
    void shouldHaveFullToString() {
        assertThat(detroit())
            .hasToString(
                "------ Detroit Pistons ------\n" + brookLopez() + "\n\n" + jeremyLamb() + "\n\n" + moBamba() + "\n\n" + codyZeller()
            );
    }

    @Test
    void shouldHaveFilteredToString() {
        assertThat(detroitLowEvalFiltered())
            .hasToString(
                "------ Detroit Pistons ------\n" + brookLopez() + "\n\n" + jeremyLamb() + "\n\n" + moBamba() + "\n\n" + codyZeller()
            );
    }

    @Test
    void shouldHaveAtLeast3PlayersToString() {
        PlayerStat notWantedPlayer = brookLopez();
        assertThat(detroitHighEvalFiltered())
            .hasToString("------ Detroit Pistons ------\n" + notWantedPlayer + "\n\n" + moBamba() + "\n\n" + codyZeller());
    }

    static TeamStat detroit() {
        return fullTeamStat().build();
    }

    private TeamStat detroitLowEvalFiltered() {
        return fullTeamStat().filter(new StatFilter(20, List.of(MO_BAMBA_ID, CODY_ZELLER_ID, NOT_PLAYING_ID))).build();
    }

    private TeamStat detroitHighEvalFiltered() {
        return fullTeamStat().filter(new StatFilter(100, List.of(MO_BAMBA_ID, CODY_ZELLER_ID, NOT_PLAYING_ID))).build();
    }

    static TeamStat indiana() {
        return fullTeamStat().name(TeamName.INDIANA).score(123).wins(8).losses(18).build();
    }

    private static TeamStat.TeamStatBuilder fullTeamStat() {
        return TeamStat.builder().filter(emptyFilter()).name(TeamName.DETROIT).score(124).wins(12).losses(13).players(players());
    }

    private static StatFilter emptyFilter() {
        return StatFilterUnitTest.empty();
    }

    private static List<PlayerStat> players() {
        return List.of(jeremyLamb(), didNotPlayed(), brookLopez(), moBamba(), codyZeller());
    }
}
