package com.example.paragonPioneerBackend.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * DTO for sending Population Building data
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Getter
@Setter
@SuperBuilder
public class PopulationBuildingDTO extends BuildingDTO {
    @JsonProperty("capacity")
    private int capacity;

    /**
     * Constructor for creating a new instance of PopulationBuildingDTO
     * @param id of the new instance
     * @param name of the new instance
     * @param remarks of the new instance
     * @param capacity of the new instance
     */
    public PopulationBuildingDTO(String id, String name, String remarks, int capacity, String slug) {
        super(id, name, remarks, slug);
        this.capacity = capacity;
    }

    /**
     * Constructor for creating a new instance of PopulationBuildingDTO
     * @param capacity of the new instance
     */
    public PopulationBuildingDTO(int capacity) {
        this.capacity = capacity;
    }
}
