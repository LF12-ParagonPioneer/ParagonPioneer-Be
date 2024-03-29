package com.example.paragonPioneerBackend.Dto.response.mappers;

import com.example.paragonPioneerBackend.Entity.Population;
import com.example.paragonPioneerBackend.Util.EnvironmentUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


/**
 * The PopulationMapper class is a Spring component that implements the ResponseMapper interface.
 * It is used to map a Population entity to a PopulationMapper object.
 * The PopulationMapper object contains the properties of the Population entity as well as additional properties for the required goods and their production unit.
 * The required goods are represented as IRIs (Internationalized Resource Identifiers), which are generated by concatenating the base URL of the API with the string "/requirement/population_good/" and the id of the Good entity.
 * If the Good entity for a required good is null or its id is null, the corresponding required good property in the PopulationMapper object will be null.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Component
public class PopulationMapper  implements ResponseMapper<PopulationMapper, Population> {
    private String id;
    private String name;
    private String slug;
    @Builder.Default
    private String[] requiredGoods = null;
    private String populationProductionUnit;

    /**
     * This method is used to map a Population entity to a PopulationMapper object.
     * It uses the builder pattern to create a new PopulationMapper object and sets its properties based on the properties of the Population entity.
     * The id, name, slug, and populationProductionUnit are directly copied from the Population entity.
     * The required goods are represented as IRIs, which are generated by concatenating the base URL of the API with the string "/requirement/population_good/" and the id of the Good entity.
     *
     * @param input The Population entity to be mapped.
     * @return A new PopulationMapper object with properties set based on the Population entity.
     */
    @Override
    public PopulationMapper map(Population input) {
        return PopulationMapper.builder()
                .id(input.getId().toString())
                .name(input.getName())
                .slug(input.getSlug())
                .requiredGoods(input.getRequiredGoods().stream().map(good -> EnvironmentUtil.getApiBaseUrl() + "/requirement/population_good/" + good.getId().toString()).toArray(String[]::new))
                .populationProductionUnit(input.getPopulationProductionUnit().name())
                .build();
    }
}