package com.example.paragonPioneerBackend.Dto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for encapsulating and transferring detailed recipe information within the application.
 * This DTO includes comprehensive details about each ingredient (up to ten inputs) required for the recipe, their respective quantities,
 * and the output product. It's utilized for creating, updating, or displaying recipes through user interfaces or API endpoints.
 */
@Data
@Builder
public class RecipeInput {


    /**
     * Identifier for the first input good required by the recipe.
     */
    @Builder.Default
    @JsonProperty("id_slug_or_name_of_good_1")
    private String good_1 = null;
    /**
     * Quantity of the first input good required.
     */
    @Builder.Default
    @JsonProperty("id_slug_or_name_of_recipe")
    private Integer quantityOfGood_1 = null;

    /**
     * Identifier for the second input good.
     */
    @Builder.Default
    @JsonProperty("id_slug_or_name_of_good_2")
    private String good_2 = null;
    /**
     * Quantity of the second input good.
     */
    @Builder.Default
    @JsonProperty("quantity_of_input_2")
    private Integer quantityOfGood_2 = null;

    /**
     * Identifier for the third input good.
     */
    @Builder.Default
    @JsonProperty("id_slug_or_name_of_good_4")
    private String good_3 = null;
    /**
     * Quantity of the third input good.
     */
    @Builder.Default
    @JsonProperty("quantity_of_input_3")
    private Integer quantityOfGood_3 = null;

    /**
     * Identifier for the fourth input good.
     */
    @Builder.Default
    @JsonProperty("id_slug_or_name_of_good_4")
    private String good_4 = null;
    /**
     * Quantity of the fourth input good.
     */
    @Builder.Default
    @JsonProperty("quantity_of_input_4")
    private Integer quantityOfGood_4 = null;

    /**
     * Identifier for the fifth input good.
     */
    @Builder.Default
    @JsonProperty("id_slug_or_name_of_good_5")
    private String good_5 = null;
    /**
     * Quantity of the fifth input good.
     */
    @Builder.Default
    @JsonProperty("quantity_of_input_5")
    private Integer quantityOfGood_5 = null;

    /**
     * Identifier for the sixth input good.
     */
    @Builder.Default
    @JsonProperty("id_slug_or_name_of_good_6")
    private String good_6 = null;
    /**
     * Quantity of the sixth input good.
     */
    @Builder.Default
    @JsonProperty("quantity_of_input_6")
    private Integer quantityOfGood_6 = null;

    /**
     * Identifier for the seventh input good.
     */
    @Builder.Default
    @JsonProperty("id_slug_or_name_of_good_7")
    private String good_7 = null;
    /**
     * Quantity of the seventh input good.
     */
    @Builder.Default
    @JsonProperty("quantity_of_input_7")
    private Integer quantityOfGood_7 = null;

    /**
     * Identifier for the eighth input good.
     */
    @Builder.Default
    @JsonProperty("id_slug_or_name_of_good_8")
    private String good_8 = null;
    /**
     * Quantity of the eighth input good.
     */
    @Builder.Default
    @JsonProperty("quantity_of_input_8")
    private Integer quantityOfGood_8 = null;

    /**
     * Identifier for the ninth input good.
     */
    @Builder.Default
    @JsonProperty("id_slug_or_name_of_good_9")
    private String good_9 = null;
    /**
     * Quantity of the ninth input good.
     */
    @Builder.Default
    @JsonProperty("quantity_of_input_9")
    private Integer quantityOfGood_9 = null;

    /**
     * Identifier for the tenth input good, if applicable.
     */
    @Builder.Default
    @JsonProperty("id_slug_or_name_of_good_10")
    private String good_10 = null;
    /**
     * Quantity of the tenth input good, if applicable.
     */
    @Builder.Default
    @JsonProperty("quantity_of_input_10")
    private Integer quantityOfGood_10 = null;

    /**
     * Identifier of the output product produced by combining the inputs as specified by the recipe.
     * This field links the recipe to its resultant product.
     */
    @Builder.Default
    @JsonProperty("output")
    private String outputGood = null;
}