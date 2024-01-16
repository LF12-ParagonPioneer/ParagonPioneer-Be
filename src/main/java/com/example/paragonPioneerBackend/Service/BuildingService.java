package com.example.paragonPioneerBackend.Service;

import com.example.paragonPioneerBackend.Dto.BuildingDTO;
import com.example.paragonPioneerBackend.Dto.PopulationBuildingDTO;
import com.example.paragonPioneerBackend.Dto.ProductionBuildingDTO;
import com.example.paragonPioneerBackend.Entity.Building;
import com.example.paragonPioneerBackend.Entity.PopulationBuilding;
import com.example.paragonPioneerBackend.Entity.ProductionBuilding;
import com.example.paragonPioneerBackend.Repository.BuildingRepository;
import com.example.paragonPioneerBackend.Repository.PopulationBuildingRepository;
import com.example.paragonPioneerBackend.Repository.ProductionBuildingRepository;
import com.example.paragonPioneerBackend.Repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * the Base handling the CRUD functions for the Building Entities. Extends BaseService
 * @param <BuildingTypeDTO> The DTO the Service should use
 */
@Component(value = "buildingService")
public class BuildingService<BuildingTypeDTO extends BuildingDTO> extends BaseService<Building, BuildingRepository, BuildingTypeDTO> {

    private final BuildingRepository buildingRepository;
    private final PopulationBuildingRepository populationBuildingRepository;
    private final ProductionBuildingRepository productionBuildingRepository;
    private final RecipeRepository recipeRepository;

    /**
     * Constructs a new BuildingService. is Autowired
     * @param repository  the repository the Service should use
     * @param buildingRepository  the repository the Service should use
     * @param populationBuildingRepository the repository the Service should use
     * @param productionBuildingRepository the repository the Service should use
     * @param recipeRepository the repository the Service should use
     */
    @Autowired
    public BuildingService(BuildingRepository repository, BuildingRepository buildingRepository, PopulationBuildingRepository populationBuildingRepository, ProductionBuildingRepository productionBuildingRepository, RecipeRepository recipeRepository) {
        super(repository);
        this.buildingRepository = buildingRepository;
        this.populationBuildingRepository = populationBuildingRepository;
        this.productionBuildingRepository = productionBuildingRepository;
        this.recipeRepository = recipeRepository;
    }

    /**
     * Adds new Entity to the database
     * Overridden from BaseService
     * @param buildingTypeDTO DTO responding to the Entity to add.
     * @return the added entity
     */
    @Override
    public Building post(BuildingTypeDTO buildingTypeDTO) {
        if (buildingTypeDTO instanceof PopulationBuildingDTO populationBuildingDTO) {
            return buildingRepository.save(PopulationBuilding.builder()
                    .name(populationBuildingDTO.getName())
                    .remarks(populationBuildingDTO.getRemarks())
                    .capacity(populationBuildingDTO.getCapacity())
                    .build());
        }

        if (buildingTypeDTO instanceof ProductionBuildingDTO productionBuildingDTO) {
            return buildingRepository.save(ProductionBuilding.builder()
                    .name(productionBuildingDTO.getName())
                    .remarks(productionBuildingDTO.getRemarks())
                    .productionPerMinute(productionBuildingDTO.getProductionPerMinute())
                    .recipe(recipeRepository.findById(productionBuildingDTO.getIdOfRecipe()).orElse(null))
                    .build());
        }

        return null;
    }

    /**
     * Updates an Entity
     * Overridden from BaseService
     * @param original original entity
     * @param buildingTypeDTO dto containing the updated data
     * @return the updated entity
     */
    @Override
    public Building putPatch(Building original, BuildingTypeDTO buildingTypeDTO) {
        original.setName(buildingTypeDTO.getName() != null ? buildingTypeDTO.getName() : original.getName());
        original.setRemarks(buildingTypeDTO.getRemarks() != null ? buildingTypeDTO.getRemarks() : original.getRemarks());

        if (buildingTypeDTO instanceof PopulationBuildingDTO populationBuildingDTO && original instanceof PopulationBuilding) {
            ((PopulationBuilding) original).setCapacity(populationBuildingDTO.getCapacity() != ((PopulationBuilding) original).getCapacity() ? populationBuildingDTO.getCapacity() : ((PopulationBuilding) original).getCapacity());
            return original;
        }

        if (buildingTypeDTO instanceof ProductionBuildingDTO productionBuildingDTO  && original instanceof ProductionBuilding) {
            ((ProductionBuilding) original).setProductionPerMinute(productionBuildingDTO.getProductionPerMinute() != ((ProductionBuilding) original).getProductionPerMinute() ? productionBuildingDTO.getProductionPerMinute() : ((ProductionBuilding) original).getProductionPerMinute());
            ((ProductionBuilding) original).setRecipe(productionBuildingDTO.getIdOfRecipe() != null ? recipeRepository.findById(productionBuildingDTO.getIdOfRecipe()).get() : ((ProductionBuilding) original).getRecipe());
            return original;
        }

        return null;
    }

    /**
     * get all ProductionBuildings
     * @return List of all ProductionBuildings
     */
    public List<ProductionBuilding> getAllProductionBuilding() {
        return productionBuildingRepository.findAll();
    }

    /**
     * get all PopulationBuildings
     * @return  List of all PopulationBuildings
     */
    public List<PopulationBuilding> getAlPopulationBuilding() {
        return populationBuildingRepository.findAll();
    }
}
