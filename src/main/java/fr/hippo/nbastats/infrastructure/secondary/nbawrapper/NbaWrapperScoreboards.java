package fr.hippo.nbastats.infrastructure.secondary.nbawrapper;

import com.drmilk.nbawrapper.domain.League;
import com.drmilk.nbawrapper.domain.utils.scoreboard.Scoreboard;
import com.drmilk.nbawrapper.exception.ScoreboardNotFoundException;
import java.time.LocalDate;
import org.springframework.stereotype.Repository;

@Repository
class NbaWrapperScoreboards {

    Scoreboard forDate(LocalDate date) {
        int day = date.getDayOfMonth();
        int month = date.getMonthValue();
        int year = date.getYear();

        try {
            return League.getScoreboard(day, month, year);
        } catch (ScoreboardNotFoundException e) {
            throw new IllegalStateException("Scoreboard can't be found");
        }
    }
}
