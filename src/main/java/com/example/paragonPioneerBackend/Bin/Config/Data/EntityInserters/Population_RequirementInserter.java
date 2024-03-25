package com.example.paragonPioneerBackend.Bin.Config.Data.EntityInserters;

import com.example.paragonPioneerBackend.Dto.Population_RequirementDTO;
import com.example.paragonPioneerBackend.Exception.EntityNotFoundException;
import com.example.paragonPioneerBackend.Service.GoodService;
import com.example.paragonPioneerBackend.Service.PopulationService;
import com.example.paragonPioneerBackend.Service.Population_RequirementService;
import lombok.RequiredArgsConstructor;
import me.tongfei.progressbar.ProgressBar;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

/**
 * Component responsible for seeding the database with initial data representing the relationships
 * between population segments and their requirements. It utilizes the Population_RequirementService
 * to create these associations based on predefined records, setting up essential data for the
 * application to simulate or manage the consumption and production needs of various population segments.
 */
@Component
@RequiredArgsConstructor
public class Population_RequirementInserter {
    private final Population_RequirementService populationRequirementService;
    private final GoodService goodService;
    private final PopulationService populationService;

    /**
     * Record to store the setup data for each population requirement, including the population
     * name, the good name, consumption rate, production rate, and whether it is a basic requirement.
     */
    private record Inserter(String populationName, String goodName, float consumption, float produce, boolean isBasic) {
    }

    private final Inserter[] inserts = {
            new Inserter("Pioneers", "Water", 0, 0, true),
            // Additional inserts omitted for brevity
    };

    /**
     * Returns the number of data insertion tasks for population requirements.
     *
     * @return The number of data insertion tasks.
     */
    public int getInsertsLength() {
        return inserts.length;
    }

    /**
     * Executes the data insertion tasks for population requirements.
     * This method is called by the InsertRunner component, providing an entry point
     * for running the inserter components.
     *
     * @param progressBarSupplier A supplier for a progress bar to display the progress of the data insertion tasks.
     */
    public void run(Supplier<ProgressBar> progressBarSupplier) {
        for (Inserter insert : inserts) {
            try {
                String goodId = null;
                String populationId = null;
                goodId = goodService.findByName(insert.goodName).getId().toString();
                populationId = populationService.findByName(insert.populationName).getId().toString();

                Population_RequirementDTO dto = Population_RequirementDTO.builder()
                        .goodId(goodId)
                        .populationId(populationId)
                        .consumption(insert.consumption)
                        .produce(insert.produce)
                        .isBasic(insert.isBasic)
                        .build();

                populationRequirementService.post(dto);
            } catch (Exception ignored) {
            }
            progressBarSupplier.get();
        }
    }
}
