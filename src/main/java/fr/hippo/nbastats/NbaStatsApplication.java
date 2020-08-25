package fr.hippo.nbastats;

import fr.hippo.nbastats.config.StatFilterProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableCaching
@EnableScheduling
@SpringBootApplication
@EnableConfigurationProperties(StatFilterProperties.class)
class NbaStatsApplication {

    public static void main(String[] args) {
        SpringApplication.run(NbaStatsApplication.class, args);
    }
}
