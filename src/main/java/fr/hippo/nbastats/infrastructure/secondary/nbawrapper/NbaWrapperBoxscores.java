package fr.hippo.nbastats.infrastructure.secondary.nbawrapper;

import com.drmilk.nbawrapper.domain.League;
import com.drmilk.nbawrapper.domain.utils.boxscore.Boxscore;
import com.drmilk.nbawrapper.exception.BoxscoreNotFoundException;
import java.time.LocalDate;
import org.springframework.stereotype.Repository;

@Repository
class NbaWrapperBoxscores {

    public Boxscore findByGameId(LocalDate date, String gameId) {
        int day = date.getDayOfMonth();
        int month = date.getMonthValue();
        int year = date.getYear();

        try {
            return League.getBoxscore(day, month, year, gameId);
        } catch (BoxscoreNotFoundException e) {
            throw new IllegalStateException("Boxscore can't be found");
        }
    }
}
