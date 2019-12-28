package fr.hippo.nbastats.infrastructure.secondary.nbawrapper;

import fr.hippo.nbastats.domain.TeamName;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
class NbaWrapperTeams {
    private static Map<String, TeamName> TEAM_IDS = Map.ofEntries(
        entry("1610612737", TeamName.ATLANTA),
        entry("1610612738", TeamName.BOSTON),
        entry("1610612751", TeamName.BROOKLYN),
        entry("1610612766", TeamName.CHARLOTTE),
        entry("1610612741", TeamName.CHICAGO),
        entry("1610612739", TeamName.CLEVELAND),
        entry("1610612742", TeamName.DALLAS),
        entry("1610612743", TeamName.DENVER),
        entry("1610612765", TeamName.DETROIT),
        entry("1610612744", TeamName.GOLDEN_STATE),
        entry("1610612745", TeamName.HOUSTON),
        entry("1610612754", TeamName.INDIANA),
        entry("1610612746", TeamName.CLIPPERS),
        entry("1610612747", TeamName.LAKERS),
        entry("1610612763", TeamName.MEMPHIS),
        entry("1610612748", TeamName.MIAMI),
        entry("1610612749", TeamName.MILWAUKEE),
        entry("1610612750", TeamName.MINNESOTA),
        entry("1610612740", TeamName.NEW_ORLEANS),
        entry("1610612752", TeamName.NEW_YORK),
        entry("1610612760", TeamName.OKLAHOMA),
        entry("1610612753", TeamName.ORLANDO),
        entry("1610612755", TeamName.PHILADELPHIA),
        entry("1610612756", TeamName.PHOENIX),
        entry("1610612757", TeamName.PORTLAND),
        entry("1610612758", TeamName.SACRAMENTO),
        entry("1610612759", TeamName.SAN_ANTONIO),
        entry("1610612761", TeamName.TORONTO),
        entry("1610612762", TeamName.UTAH),
        entry("1610612764", TeamName.WASHINGTON)
    );

    private static SimpleImmutableEntry<String, TeamName> entry(String id, TeamName teamName) {
        return new SimpleImmutableEntry<String, TeamName>(id, teamName);
    }

    public TeamName findById(String wrapperId) {
        return TEAM_IDS.get(wrapperId);
    }
}
