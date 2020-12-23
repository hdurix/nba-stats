package fr.hippo.nbastats.config;

import fr.hippo.nbastats.domain.StatFilter;
import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "filter")
public class StatFilterProperties {

    private Integer highEval;
    private List<Integer> playerIds;

    void setHighEval(Integer highEval) {
        this.highEval = highEval;
    }

    void setPlayerIds(List<Integer> playerIds) {
        this.playerIds = playerIds;
    }

    public StatFilter statFilter() {
        return new StatFilter(highEval, playerIds);
    }
}
