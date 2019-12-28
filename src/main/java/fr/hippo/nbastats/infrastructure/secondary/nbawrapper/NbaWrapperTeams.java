package fr.hippo.nbastats.infrastructure.secondary.nbawrapper;

import fr.hippo.nbastats.domain.TeamName;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
class NbaWrapperTeams {
    private static Map<String, TeamName> IDS = Map.ofEntries(
        toEntry("1610612737", TeamName.ATLANTA),
        toEntry("1610612738", TeamName.BOSTON),
        toEntry("1610612751", TeamName.BROOKLYN),
        toEntry("1610612766", TeamName.CHARLOTTE),
        toEntry("1610612741", TeamName.CHICAGO),
        toEntry("1610612739", TeamName.CLEVELAND),
        toEntry("1610612742", TeamName.DALLAS),
        toEntry("1610612743", TeamName.DENVER),
        toEntry("1610612765", TeamName.DETROIT),
        toEntry("1610612744", TeamName.GOLDEN_STATE),
        toEntry("1610612745", TeamName.HOUSTON),
        toEntry("1610612754", TeamName.INDIANA),
        toEntry("1610612746", TeamName.CLIPPERS),
        toEntry("1610612747", TeamName.LAKERS),
        toEntry("1610612763", TeamName.MEMPHIS),
        toEntry("1610612748", TeamName.MIAMI),
        toEntry("1610612749", TeamName.MILWAUKEE),
        toEntry("1610612750", TeamName.MINNESOTA),
        toEntry("1610612740", TeamName.NEW_ORLEANS),
        toEntry("1610612752", TeamName.NEW_YORK),
        toEntry("1610612760", TeamName.OKLAHOMA),
        toEntry("1610612753", TeamName.ORLANDO),
        toEntry("1610612755", TeamName.PHILADELPHIA),
        toEntry("1610612756", TeamName.PHOENIX),
        toEntry("1610612757", TeamName.PORTLAND),
        toEntry("1610612758", TeamName.SACRAMENTO),
        toEntry("1610612759", TeamName.SAN_ANTONIO),
        toEntry("1610612761", TeamName.TORONTO),
        toEntry("1610612762", TeamName.UTAH),
        toEntry("1610612764", TeamName.WASHINGTON)
    );

    private static SimpleImmutableEntry<String, TeamName> toEntry(String id, TeamName teamName) {
        return new SimpleImmutableEntry<String, TeamName>(id, teamName);
    }

    public TeamName findById(String wrapperId) {
        return IDS.get(wrapperId);
    }
}
