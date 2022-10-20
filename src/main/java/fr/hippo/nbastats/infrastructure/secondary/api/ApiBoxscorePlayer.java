package fr.hippo.nbastats.infrastructure.secondary.api;

import fr.hippo.nbastats.domain.Fouls;
import fr.hippo.nbastats.domain.Identity;
import fr.hippo.nbastats.domain.PlayerStat;
import fr.hippo.nbastats.domain.Stat;
import fr.hippo.nbastats.domain.StatTuple;

class ApiBoxscorePlayer {

    private int personId;
    private String firstName;
    private String familyName;
    private ApiBoxscorePlayerStatistics statistics;

    public int getPersonId() {
        return personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public ApiBoxscorePlayerStatistics getStatistics() {
        return statistics;
    }

    PlayerStat toDomain() {
        Identity identity = new Identity(personId, firstName, familyName);

        return PlayerStat
            .builder()
            .identity(identity)
            .fouls(new Fouls(statistics.getFoulsPersonal()))
            .points(new Stat(statistics.getPoints()))
            .rebounds(new Stat(statistics.getReboundsTotal()))
            .assists(new Stat(statistics.getAssists()))
            .blocks(new Stat(statistics.getBlocks()))
            .steals(new Stat(statistics.getSteals()))
            .fieldGoals(new StatTuple(statistics.getFieldGoalsMade(), statistics.getFieldGoalsAttempted()))
            .threePoints(new StatTuple(statistics.getThreePointersMade(), statistics.getThreePointersAttempted()))
            .freeThrows(new StatTuple(statistics.getFreeThrowsMade(), statistics.getFreeThrowsAttempted()))
            .turnovers(new Stat(statistics.getTurnovers()))
            .minutes(new Stat(parseMinutes(statistics.getMinutesCalculated())))
            .build();
    }

    private static int parseMinutes(String minutes) {
        return Integer.parseInt(minutes.split("PT")[1].split("M")[0]);
    }
}
