package fr.hippo.nbastats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class NbaStatsApplication {

    public static void main(String[] args) {
        SpringApplication.run(NbaStatsApplication.class, args);
    }
}
