package fr.hippo.nbastats.domain;

import org.apache.commons.lang3.StringUtils;

public enum TeamName {
    BOSTON("Boston", "Celtics", "Boston Celtics"),
    BROOKLYN("Brooklyn", "Nets", "Brooklyn Nets"),
    NEW_YORK("New York", "Knicks", "New York Knicks"),
    PHILADELPHIA("Philadelphia 76", "rs", "Philadelphia 76ers"),
    TORONTO("Toronto", "Raptors", "Toronto Raptors"),
    CHICAGO("Chicago", "Bulls", "Chicago Bulls"),
    CLEVELAND("Cleveland", "Cavaliers", "Cleveland Cavaliers"),
    DETROIT("Detroit", "Pistons", "Detroit Pistons"),
    INDIANA("Indiana", "Pacers", "Indiana Pacers"),
    MILWAUKEE("Milwaukee", "Bucks", "Milwaukee Bucks"),
    ATLANTA("Atlanta", "Hawks", "Atlanta Hawks"),
    CHARLOTTE("Charlotte", "Hornets", "Charlotte Hornets"),
    MIAMI("Miami", "Heat", "Miami Heat"),
    ORLANDO("Orlando", "Magic", "Orlando Magic"),
    WASHINGTON("Washington", "Wizards", "Washington Wizards"),
    DENVER("Denver", "Nuggets", "Denver Nuggets"),
    MINNESOTA("Minnesota", "Timberwolves", "Minnesota Timberwolves"),
    OKLAHOMA("Oklahoma City", "Thunder", "Oklahoma City Thunder"),
    PORTLAND("Portland", "Blazers", "Portland Trail Blazers"),
    UTAH("Salt Lake City", "Jazz", "Utah Jazz"),
    GOLDEN_STATE("San Francisco", "Warriors", "Golden State Warriors"),
    CLIPPERS("Los Angeles", "Clippers", "Los Angeles Clippers"),
    LAKERS("Los Angeles", "Lakers", "Los Angeles Lakers"),
    PHOENIX("Phoenix", "Suns", "Phoenix Suns"),
    SACRAMENTO("Sacramento", "Kings", "Sacramento Kings"),
    DALLAS("Dallas", "Mavericks", "Dallas Mavericks"),
    HOUSTON("Houston", "Rockets", "Houston Rockets"),
    MEMPHIS("Memphis", "Grizzlies", "Memphis Grizzlies"),
    NEW_ORLEANS("New Orleans", "Hornets", "New Orleans Hornets"),
    SAN_ANTONIO("San Antonio", "Spurs", "San Antonio Spurs");
    private final String city;
    private final String nickname;
    private final String fullName;

    TeamName(String city, String nickname, String fullName) {
        this.city = city;
        this.nickname = nickname;
        this.fullName = fullName;
    }

    public String city() {
        return city;
    }

    public String nickname() {
        return nickname;
    }

    public String fullName() {
        return fullName;
    }

    @Override
    public String toString() {
        return StringUtils.center(" " + fullName + " ", 25, "-");
    }
}
