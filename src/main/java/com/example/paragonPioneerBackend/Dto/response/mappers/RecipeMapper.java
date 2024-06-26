package com.example.paragonPioneerBackend.Dto.response.mappers;

import com.example.paragonPioneerBackend.Entity.Good;
import com.example.paragonPioneerBackend.Entity.Recipe;
import com.example.paragonPioneerBackend.Util.EnvironmentUtil;
import com.example.paragonPioneerBackend.Util.UuidUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * The RecipeMapper class is a Spring component that implements the ResponseMapper interface.
 * It is used to map a Recipe entity to a RecipeMapper object.
 * The RecipeMapper object contains the properties of the Recipe entity as well as additional properties for the inputs and their quantities.
 * The inputs are represented as IRIs (Internationalized Resource Identifiers), which are generated by the getIriOrNull method.
 * If the Good entity for an input is null or its id is null, the corresponding input property in the RecipeMapper object will be null.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Component
public class RecipeMapper implements ResponseMapper<RecipeMapper, Recipe> {
    private String id;
    private String slug;
    private String input1;
    private int quantityOfInput1;
    private String input2;
    private int quantityOfInput2;
    private String input3;
    private int quantityOfInput3;
    private String input4;
    private int quantityOfInput4;
    private String input5;
    private int quantityOfInput5;
    private String input6;
    private int quantityOfInput6;
    private String input7;
    private int quantityOfInput7;
    private String input8;
    private int quantityOfInput8;
    private String input9;
    private int quantityOfInput9;
    private String input10;
    private int quantityOfInput10;
    private String output;

    /**
     * This method is used to map a Recipe entity to a RecipeMapper object.
     * It uses the builder pattern to create a new RecipeMapper object and sets its properties based on the properties of the Recipe entity.
     * The id, slug, quantities of inputs, and output are directly copied from the Recipe entity.
     * The inputs are represented as IRIs, which are generated by the getIriOrNull method.
     *
     * @param input The Recipe entity to be mapped.
     * @return A new RecipeMapper object with properties set based on the Recipe entity.
     */
    @Override
    public RecipeMapper map(Recipe input) {
        return RecipeMapper.builder()
                .id(UuidUtil.getIri("recipe/",input))
                .slug(input.getSlug())
                .input1(getIriOrNull(input.getInput1()))
                .quantityOfInput1(input.getQuantityOfInput1())
                .input2((getIriOrNull(input.getInput2())))
                .quantityOfInput2(input.getQuantityOfInput2())
                .input3(getIriOrNull(input.getInput3()))
                .quantityOfInput3(input.getQuantityOfInput3())
                .input4(getIriOrNull(input.getInput4()))
                .quantityOfInput4(input.getQuantityOfInput4())
                .input5(getIriOrNull(input.getInput5()))
                .quantityOfInput5(input.getQuantityOfInput5())
                .input6(getIriOrNull(input.getInput6()))
                .quantityOfInput6(input.getQuantityOfInput6())
                .input7(getIriOrNull(input.getInput7()))
                .quantityOfInput7(input.getQuantityOfInput7())
                .input8(getIriOrNull(input.getInput8()))
                .quantityOfInput8(input.getQuantityOfInput8())
                .input9(getIriOrNull(input.getInput9()))
                .quantityOfInput9(input.getQuantityOfInput9())
                .input10(getIriOrNull(input.getInput10()))
                .quantityOfInput10(input.getQuantityOfInput10())
                .output(getIriOrNull(input.getOutput()))
                .build();
    }

    /**
     * This method is used to generate an IRI for a Good entity.
     * It checks if the Good entity and its id are not null.
     * If they are not null, it returns the base URL of the API concatenated with the string "good/" and the id of the Good entity.
     * If the Good entity or its id is null, it returns null.
     *
     * @param good The Good entity for which to generate an IRI.
     * @return The IRI for the Good entity, or null if the Good entity or its id is null.
     */
    private static String getIriOrNull(Good good) {
        return good != null && good.getId() != null ? EnvironmentUtil.getApiBaseUrl() + "good/" + good.getId() : null;
    }
}