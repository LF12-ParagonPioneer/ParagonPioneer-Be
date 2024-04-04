package com.example.paragonPioneerBackend.Dto.response.mappers;

import com.example.paragonPioneerBackend.Entity.PopulationBuilding;
import com.example.paragonPioneerBackend.Entity.ProductionBuilding;
import com.example.paragonPioneerBackend.Entity.abstractEntity.Building;
import com.example.paragonPioneerBackend.Entity.joinTables.CostBuildingGoods;
import com.example.paragonPioneerBackend.Util.UuidUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The BuildingMapper class is a Spring component that implements the ResponseMapper interface.
 * It is used to map a Building entity to a BuildingMapper object.
 * The BuildingMapper object contains the properties of the Building entity as well as additional properties for the costs, required population, capacity, production per minute, and recipe.
 * The id, name, remarks, and slug are directly copied from the Building entity.
 * The costs are represented as IRIs (Internationalized Resource Identifiers), which are generated by the getIri method of the UuidUtil class.
 * The capacity and production per minute are only set if the Building entity is an instance of PopulationBuilding or ProductionBuilding, respectively.
 * The recipe is only set if the Building entity is an instance of ProductionBuilding.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Component
public class BuildingMapper implements ResponseMapper<BuildingMapper, Building> {

    private String id;
    private String name;
    private String remarks;
    @Builder.Default
    private ArrayList<Map<String,String>> costs = null;
    @Builder.Default
    private String requirePopulation = null;
    private String slug;
    // Only for population buildings
    @Builder.Default
    private Integer capacity = null;
    // Only for production buildings
    @Builder.Default
    private Float productionPerMinute = null;
    @Builder.Default
    private String recipe = null;

    /**
     * This method is used to map a Building entity to a BuildingMapper object.
     * It uses the builder pattern to create a new BuildingMapper object and sets its properties based on the properties of the Building entity.
     * The id, name, remarks, costs, and slug are directly copied from the Building entity.
     * The costs are represented as IRIs, which are generated by the getIri method of the UuidUtil class.
     * If the Building entity is an instance of PopulationBuilding, the capacity is set.
     * If the Building entity is an instance of ProductionBuilding, the production per minute and recipe are set.
     *
     * @param input The Building entity to be mapped.
     * @return A new BuildingMapper object with properties set based on the Building entity.
     */
    @Override
    public BuildingMapper map(Building input) {
        var costs = new ArrayList<Map<String, String>>();
        for (CostBuildingGoods costBuildingGoods: input.getCosts()){
            var cost = new LinkedHashMap<String, String>();
            cost.put("good", UuidUtil.getIri("goods/", costBuildingGoods.getGood()));
            cost.put("amount", String.valueOf(costBuildingGoods.getAmount()));
            costs.add(cost);
        }
        var mapper = BuildingMapper.builder()
                .id(input.getId().toString())
                .name(input.getName())
                .remarks(input.getRemarks())
                .costs(costs)
                .slug(input.getSlug());


        if (input instanceof PopulationBuilding) {
            mapper.capacity(((PopulationBuilding) input).getCapacity());
        } else if (input instanceof ProductionBuilding) {
            mapper.productionPerMinute(((ProductionBuilding) input).getProductionPerMinute());
            mapper.recipe(UuidUtil.getIri("recipe/", ((ProductionBuilding) input).getRecipe()));
        }
        return mapper.build();
    }
}