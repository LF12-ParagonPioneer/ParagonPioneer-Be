package com.example.paragonPioneerBackend.Bin.Config.Data.EntityInserters;

import com.example.paragonPioneerBackend.Dto.GoodDTO;
import com.example.paragonPioneerBackend.Service.GoodService;
import lombok.RequiredArgsConstructor;
import me.tongfei.progressbar.ProgressBar;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

/**
 * A component responsible for populating the database with initial goods data.
 * Utilizes the GoodService to create goods entities based on a predefined set of records,
 * ensuring the application has a base set of goods for various operations like crafting or building.
 * This class is particularly useful in development and testing environments, where a consistent
 * dataset is necessary for application functionality testing.
 */
@Component
@RequiredArgsConstructor
public class GoodInserter {
    private final GoodService service;

    /**
     * Record to store the initial setup data for goods, including their name and any remarks.
     */
    private record Inserter(String name, String remarks, boolean isMapResource) {
        // Static factory method with default value for isMapResource
        static Inserter of(String name, String remarks) {
            return new Inserter(name, remarks, false);
        }

        static Inserter of(String name, String remarks, boolean isMapResource) {
            return new Inserter(name, remarks, isMapResource);
        }
    }

    private final Inserter[] inserts = {
            Inserter.of("Land tile", "", true),
            Inserter.of("Water tile", "", true),
            Inserter.of("Mountain tile", "", true),
            Inserter.of("Hop Field", "", true),
            Inserter.of("Horse Field", "", true),
            Inserter.of("Indigo Field", "", true),
            Inserter.of("Linseed Field", "", true),
            Inserter.of("Potato Field", "", true),
            Inserter.of("Sheep Field", "", true),
            Inserter.of("Cotton Field", "", true),
            Inserter.of("Mulberry Trees", "", true),
            Inserter.of("Tobacco Field", "", true),
            Inserter.of("Wheat Field", "", true),
            Inserter.of("Coin", "Global"),
            Inserter.of("Cartography", "Global, used for Discovery"),
            Inserter.of("Favor", "Global, needed for more island slots"),
            Inserter.of("Wood", "Build material"),
            Inserter.of("Fish", ""),
            Inserter.of("Plank", "Build material"),
            Inserter.of("Schnapps", ""),
            Inserter.of("Stone", "Build material"),
            Inserter.of("Linseed", ""),
            Inserter.of("Linen", ""),
            Inserter.of("Bow", ""),
            Inserter.of("Archer", ""),
            Inserter.of("Ropes", ""),
            Inserter.of("Copper Ore", ""),
            Inserter.of("Copper Ingot", ""),
            Inserter.of("Copper Sword", ""),
            Inserter.of("Yarn", ""),
            Inserter.of("Fabric", ""),
            Inserter.of("Wheat", ""),
            Inserter.of("Flour", ""),
            Inserter.of("Bread", ""),
            Inserter.of("Tobacco", ""),
            Inserter.of("Cigar", ""),
            Inserter.of("Sail", ""),
            Inserter.of("Horse", ""),
            Inserter.of("Longbow", ""),
            Inserter.of("Clay", ""),
            Inserter.of("Brick", "Build material"),
            Inserter.of("Clothes", ""),
            Inserter.of("Malt", ""),
            Inserter.of("Hops", ""),
            Inserter.of("Beer", ""),
            Inserter.of("Cattle", ""),
            Inserter.of("Rock Salt", ""),
            Inserter.of("Salt", ""),
            Inserter.of("Meat", ""),
            Inserter.of("Coal", ""),
            Inserter.of("Gold Ore", ""),
            Inserter.of("Gold", ""),
            Inserter.of("Gold Ingot", ""),
            Inserter.of("Gold Jewelry", ""),
            Inserter.of("Linseed Oil", ""),
            Inserter.of("Iron Ore", ""),
            Inserter.of("Iron Ingot", ""),
            Inserter.of("Iron Sword", ""),
            Inserter.of("Militia", ""),
            Inserter.of("Tools", "Build material"),
            Inserter.of("Crossbow", ""),
            Inserter.of("Marble", "Build material"),
            Inserter.of("Silk", ""),
            Inserter.of("Silk Cloth", ""),
            Inserter.of("Dye", ""),
            Inserter.of("Garment", ""),
            Inserter.of("Gemstone", ""),
            Inserter.of("Goblet", ""),
            Inserter.of("Honey", ""),
            Inserter.of("Candle", ""),
            Inserter.of("Candle Holder", ""),
            Inserter.of("Liqueur", ""),
            Inserter.of("Lobster", ""),
            Inserter.of("Feast", ""),
            Inserter.of("Grapes", ""),
            Inserter.of("Barrel", ""),
            Inserter.of("Wine", ""),
            Inserter.of("Windjammer", ""),
            Inserter.of("Book", ""),
            Inserter.of("Coke", ""),
            Inserter.of("Steel Ingot", ""),
            Inserter.of("Armored Horse", ""),
            Inserter.of("Saltpeter", ""),
            Inserter.of("Gunpowder", ""),
            Inserter.of("Cannon", ""),
            Inserter.of("Metal Cuttings", ""),
            Inserter.of("Fireworks", ""),
            Inserter.of("Vineyard", ""),
    };

    /**
     * Returns the number of data insertion tasks for goods.
     *
     * @return The number of data insertion tasks.
     */
    public int getInsertsLength() {
        return inserts.length;
    }

    /**
     * Executes the data insertion tasks for goods.
     * This method is called by the InsertRunner component, providing an entry point
     * for running the inserter components.
     *
     * @param progressBarSupplier Supplier for a progress bar to display the insertion progress.
     */
    public void run(Supplier<ProgressBar> progressBarSupplier) {
        for (Inserter insert : inserts) {
            try {
                service.post(GoodDTO.builder()
                        .isMapResource(insert.isMapResource)
                        .name(insert.name)
                        .remarks(insert.remarks)
                        .build());
            } catch (Exception ignored) {
            }
            progressBarSupplier.get();
        }
    }
}