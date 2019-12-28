package fr.hippo.nbastats.infrastructure.secondary.telegram;

import fr.hippo.nbastats.domain.GameStatUnitTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class TelegramGameNotifierUnitTest {
    @InjectMocks
    private TelegramGameNotifier notifier;

    @Test
    public void shouldSendToTelegramBot() {
        notifier.send(GameStatUnitTest.defaultGameStat());
    }
}
