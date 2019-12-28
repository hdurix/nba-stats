package fr.hippo.nbastats.infrastructure.secondary.telegram;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import fr.hippo.nbastats.domain.GameStatUnitTest;
import java.net.URI;
import java.net.URISyntaxException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

@ExtendWith(SpringExtension.class)
class TelegramGameNotifierUnitTest {
    @Mock
    private RestTemplate restTemplate;

    private TelegramGameNotifier notifier;

    @BeforeEach
    public void setup() {
        notifier = new TelegramGameNotifier(restTemplate, "123:abc", 456);
    }

    @Test
    public void shouldSendToTelegramBot() throws URISyntaxException {
        notifier.send(GameStatUnitTest.defaultGameStat());

        ArgumentCaptor<URI> captor = ArgumentCaptor.forClass(URI.class);
        verify(restTemplate).getForEntity(captor.capture(), eq(Object.class));

        URI url = captor.getValue();
        assertThat(url.toString())
            .startsWith("https://api.telegram.org/bot123:abc/sendMessage?chat_id=456&parse_mode=html&text=%3Cpre%3EPacers");
    }
}
