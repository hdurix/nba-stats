package fr.hippo.nbastats.infrastructure.secondary.nbawrapper;

import static fr.hippo.nbastats.domain.PlayerStatUnitTest.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.drmilk.nbawrapper.domain.utils.boxscore.Boxscore;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.hippo.nbastats.domain.PlayerStat;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class NbaWrapperBoxscoreConverterUnitTest {
    @Value("classpath:fixtures/boxscore.json")
    private Resource resource;

    @Mock
    private NbaWrapperPlayerConverter playerConverter;

    @InjectMocks
    private NbaWrapperBoxscoreConverter converter;

    @Test
    void shouldMapGame() throws IOException {
        when(playerConverter.toDomain(argThat(activePlayer -> "201567".equals(activePlayer.getPersonId())))).thenReturn(brookLopez());

        List<PlayerStat> playerStats = converter.extractStatForTeam(getBoxscore(), "1610612739");

        assertThat(playerStats.stream().filter(Objects::nonNull).findFirst().get())
            .hasToString("B. Lopez   *76|42  8 10  4  0\n 9/12 10/13  4/5 | 4|34'");
    }

    private Boxscore getBoxscore() throws IOException {
        return new ObjectMapper().readValue(defaultJson(), Boxscore.class);
    }

    private String defaultJson() throws IOException {
        return new String(Files.readAllBytes(resource.getFile().toPath()));
    }
}
