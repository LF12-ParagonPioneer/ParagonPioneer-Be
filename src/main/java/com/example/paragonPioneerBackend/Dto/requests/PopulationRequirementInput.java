package com.example.paragonPioneerBackend.Dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO class for transferring data related to the requirements of a population segment within the application.
 * It encompasses details about goods consumed or produced by the population, including the rate of consumption
 * and production, as well as identifying basic necessities for the population. This class is particularly useful
 * for CRUD operations on population requirements and for displaying this information in user interfaces or API responses.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PopulationRequirementInput {
    /**
     * The identifier of the good associated with this requirement. It references the specific good that is
     * consumed or produced by the population segment identified by {@code populationId}.
     */
    @Builder.Default
    private String good = null;

    /**
     * The identifier of the population segment associated with this requirement. It references the specific
     * population segment that consumes or produces the good identified by {@code goodId}.
     */
    @Builder.Default
    private String population = null;

    /**
     * The rate at which the specified good is consumed by the population segment. This field quantifies
     * the consumption needs of the population for the good.
     */
    @Builder.Default
    private Float consumption = null;

    /**
     * The rate at which the specified good is produced by the population segment. This field quantifies
     * the production output of the population for the good.
     */
    @Builder.Default
    private Float produceRate = null;

    /**
     * A flag indicating whether the specified good is considered a basic necessity for the population segment.
     * Basic necessities are essential for the survival or well-being of the population.
     */
    @Builder.Default
    private boolean isBasic = true;
}