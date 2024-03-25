package com.example.paragonPioneerBackend.Bin.Config.Data;

import com.example.paragonPioneerBackend.Bin.Config.Data.EntityInserters.*;
import com.example.paragonPioneerBackend.Bin.Security.AuthServices.AuthenticationService;
import com.example.paragonPioneerBackend.Bin.Security.Requests.RegisterRequest;
import lombok.RequiredArgsConstructor;
import me.tongfei.progressbar.ProgressBar;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

/**
 * Application runner that orchestrates the execution of various data inserter components
 * to populate the database with initial data upon application startup. Ensures that data
 * insertions are executed in a specific order to maintain data integrity and correctly set up
 * entity relationships.
 * <p>
 * This component is crucial for initializing the application with a consistent and comprehensive
 * set of data, facilitating development, testing, and demonstration scenarios.
 */
@Configuration
@RequiredArgsConstructor
public class InsertRunner implements ApplicationRunner {
    private final AuthenticationService authenticationService;

    private final GoodInserter goodInserter;
    private final RecipeInserter recipeInserter;
    private final PopulationInserter populationInserter;
    private final BuildingInserter buildingInserter;
    private final Cost_Building_goodsInserter costBuildingGoodsInserter;
    private final Population_RequirementInserter populationRequirementInserter;
    private final Requirement_Population_BuildingInserter costBuildingPopulation;

    /**
     * Executes the data insertion tasks in the prescribed order when the application starts.
     * This method is automatically called by the Spring Boot framework, providing an entry point
     * for running the inserter components.
     *
     * @param args Application arguments passed at startup, not directly used by this method.
     */
    @Override
    public void run(ApplicationArguments args) {
        long amount = goodInserter.getInsertsLength() + populationInserter.getInsertsLength() +
                recipeInserter.getInsertsLength() + populationRequirementInserter.getInsertsLength() +
                buildingInserter.getInsertsLength();

        try (ProgressBar pb = new ProgressBar("Data Insertion", amount + 1)) {
            try {
                authenticationService.register(RegisterRequest.builder()
                        .email("amin@user.de")
                        .password("admin")
                        .build());
            } catch (Exception ignored) {
            }
            pb.step();

            goodInserter.run(pb::step);
            populationInserter.run(pb::step);
            recipeInserter.run(pb::step);
            populationRequirementInserter.run(pb::step);
            buildingInserter.run(pb::step);
        }
        /*
        todo: the following inserter are not havely required, and will be implemented after the 0.1 release
         */

//         costBuildingGoodsInserter.run(); // Insert cost-building-goods relations.
//         costBuildingPopulation.run(); // Insert requirement-population-building relations.
    }
}
