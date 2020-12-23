package fr.hippo.nbastats.infrastructure.secondary.nbawrapper;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.drmilk.nbawrapper.domain.utils.boxscore.ActivePlayer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.hippo.nbastats.domain.Identity;
import fr.hippo.nbastats.domain.PlayerStat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class NbaWrapperPlayerConverterUnitTest {

    @Mock
    private NbaWrapperPlayers players;

    @InjectMocks
    private NbaWrapperPlayerConverter converter;

    @Test
    void shouldMapPlayer() throws JsonProcessingException {
        when(players.getIdentityFromPlayerId("201567")).thenReturn(new Identity(42, "Kevin", "Love"));

        PlayerStat playerStat = converter.toDomain(getActivePlayer());

        assertThat(playerStat).hasToString("K. Love     48|30  7  4  0  0\n10/16  6/11  4/6 | 0|34'");
    }

    @Test
    void shouldMapPlayerDidNotPlayed() throws JsonProcessingException {
        when(players.getIdentityFromPlayerId("201567")).thenReturn(new Identity(42, "Kevin", "Love"));

        PlayerStat playerStat = converter.toDomain(getActivePlayer(false));

        assertThat(playerStat).hasToString("K. Love     48|30  7  4  0  0\n10/16  6/11  4/6 | 0| 0'");
    }

    private ActivePlayer getActivePlayer() throws JsonProcessingException {
        return getActivePlayer(true);
    }

    private ActivePlayer getActivePlayer(boolean played) throws JsonProcessingException {
        return new ObjectMapper().readValue(defaultJson(played), ActivePlayer.class);
    }

    private String defaultJson(boolean played) {
        return (
            "{\n" +
            "        \"personId\": \"201567\",\n" +
            "        \"firstName\": \"Kevin\",\n" +
            "        \"lastName\": \"Love\",\n" +
            "        \"jersey\": \"0\",\n" +
            "        \"teamId\": \"1610612739\",\n" +
            "        \"isOnCourt\": false,\n" +
            "        \"points\": \"30\",\n" +
            "        \"pos\": \"PF\",\n" +
            "        \"position_full\": \"Power Forward\",\n" +
            "        \"player_code\": \"kevin_love\",\n" +
            "        \"min\": \"" +
            (played ? "34:40" : "") +
            "\",\n" +
            "        \"fgm\": \"10\",\n" +
            "        \"fga\": \"16\",\n" +
            "        \"fgp\": \"62.5\",\n" +
            "        \"ftm\": \"4\",\n" +
            "        \"fta\": \"6\",\n" +
            "        \"ftp\": \"66.7\",\n" +
            "        \"tpm\": \"6\",\n" +
            "        \"tpa\": \"11\",\n" +
            "        \"tpp\": \"54.5\",\n" +
            "        \"offReb\": \"1\",\n" +
            "        \"defReb\": \"6\",\n" +
            "        \"totReb\": \"7\",\n" +
            "        \"assists\": \"4\",\n" +
            "        \"pFouls\": \"2\",\n" +
            "        \"steals\": \"0\",\n" +
            "        \"turnovers\": \"\",\n" +
            "        \"blocks\": \"0\",\n" +
            "        \"plusMinus\": \"-6\",\n" +
            "        \"dnp\": \"\",\n" +
            "        \"sortKey\": {\n" +
            "          \"name\": 2,\n" +
            "          \"pos\": 0,\n" +
            "          \"points\": 3,\n" +
            "          \"min\": 1,\n" +
            "          \"fgm\": 3,\n" +
            "          \"fga\": 4,\n" +
            "          \"fgp\": 9,\n" +
            "          \"ftm\": 3,\n" +
            "          \"fta\": 1,\n" +
            "          \"ftp\": 11,\n" +
            "          \"tpm\": 1,\n" +
            "          \"tpa\": 1,\n" +
            "          \"tpp\": 4,\n" +
            "          \"offReb\": 10,\n" +
            "          \"defReb\": 2,\n" +
            "          \"totReb\": 3,\n" +
            "          \"assists\": 6,\n" +
            "          \"pFouls\": 13,\n" +
            "          \"steals\": 20,\n" +
            "          \"turnovers\": 22,\n" +
            "          \"blocks\": 21,\n" +
            "          \"plusMinus\": 16\n" +
            "        }\n" +
            "      }"
        );
    }
}
