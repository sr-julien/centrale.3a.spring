package com.centrale.rest;

import com.centrale.rest.entity.SchoolClassEntity;
import com.centrale.rest.entity.SeriesEntity;
import com.centrale.rest.entity.StudentEntity;
import com.centrale.rest.projetDomain.Series;
import com.centrale.rest.repository.SchoolClassRepository;
import com.centrale.rest.repository.SeriesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;


@SpringBootApplication
public class RestApplication {

    private static final Logger log = LoggerFactory.getLogger(RestApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }

    // Simply to execute some code after startup
    @Bean
    public CommandLineRunner Init(SeriesRepository seriesRepository) {
        return (args) -> {
            log.info("Create and save series");
            // on prépare la création de 3 séries avec des états différents
            Series _BreakingBad = new Series("Breaking Bad",60);
            _BreakingBad.start();
            _BreakingBad.consume(58);
            Series _Witcher = new Series ("Witcher",40);
            _Witcher.start();
            _Witcher.finnish();
            Series _HLG = new Series("Héllène et les Garçon",680);
            _HLG.wish();

            //on crée les entités
            SeriesEntity Witcher = new SeriesEntity(_Witcher);
            SeriesEntity BreakingBad = new SeriesEntity(_BreakingBad);
            SeriesEntity HLG = new SeriesEntity(_HLG);

            //Sauvegarde des entitées dans la BDD
            seriesRepository.save(Witcher);
            seriesRepository.save(BreakingBad);
            seriesRepository.save(HLG);

        };
    }


}
