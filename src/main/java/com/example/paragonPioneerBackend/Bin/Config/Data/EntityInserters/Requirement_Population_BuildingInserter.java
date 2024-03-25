package com.example.paragonPioneerBackend.Bin.Config.Data.EntityInserters;

import com.example.paragonPioneerBackend.Dto.Requirement_Population_BuildingDTO;
import com.example.paragonPioneerBackend.Exception.EntityNotFoundException;
import com.example.paragonPioneerBackend.Service.BuildingService;
import com.example.paragonPioneerBackend.Service.PopulationService;
import com.example.paragonPioneerBackend.Service.Requirement_Building_PopulationService;
import lombok.RequiredArgsConstructor;
import me.tongfei.progressbar.ProgressBar;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

/**
 * Component for seeding the database with initial data on the population requirements for buildings.
 * It uses Requirement_Building_PopulationService to create relationships that specify the minimum
 * population needed for each building, leveraging BuildingService and PopulationService to link
 * buildings with their respective population segments. This process is crucial for establishing
 * a realistic and interactive environment where population dynamics directly influence building
 * functionality and operational capabilities.
 */
@Component
@RequiredArgsConstructor
public class Requirement_Population_BuildingInserter {
    private final Requirement_Building_PopulationService requirementBuildingPopulationService;
    private final BuildingService<?> buildingService;
    private final PopulationService populationService;

    /**
     * Record to store the initial setup data for building-population requirements,
     * including the building name, the required population segment name, and the amount needed.
     */
    private record Inserter(String buildingName, String populationName, int amount) {
    }

    private final Inserter[] inserts = {
            new Inserter("Fisherman", "Pioneers", 10),
            // Additional inserts omitted for brevity
    };

    /**
     * Returns the number of data insertion tasks for building-population requirements.
     *
     * @return The number of data insertion tasks.
     */
    public int getInsertsLength() {
        return inserts.length;
    }

    /**
     * Executes the data insertion tasks for building-population requirements.
     * This method is called by the InsertRunner component, providing an entry point
     * for running the inserter components.
     *
     * @param progressBarSupplier A supplier for a progress bar to display the progress of the data insertion tasks.
     */
    public void run(Supplier<ProgressBar> progressBarSupplier) {
        for (Inserter insert : inserts) {
            try {
                String populationId = null;
                String buildingId = null;

                populationId = populationService.findByName(insert.populationName).getId().toString();
                buildingId = buildingService.findByName(insert.buildingName).getId().toString();
                Requirement_Population_BuildingDTO dto = Requirement_Population_BuildingDTO.builder()
                        .populationId(populationId)
                        .buildingId(buildingId)
                        .amount(insert.amount)
                        .build();

                requirementBuildingPopulationService.post(dto);
            } catch (Exception ignored) {
            }
            progressBarSupplier.get();
        }
    }
}
