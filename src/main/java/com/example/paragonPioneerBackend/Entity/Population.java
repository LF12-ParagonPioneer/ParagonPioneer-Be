package com.example.paragonPioneerBackend.Entity;

import com.example.paragonPioneerBackend.Entity.JoinTables.Requirement_Population_Building;
import com.example.paragonPioneerBackend.Entity.JoinTables.Population_Requirement;
import com.example.paragonPioneerBackend.Util.SlugUtil;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Set;

/**
 * Entity representing a Population
 */
@Entity
@Getter
@Setter
@ToString
@Builder
@Table(name = "population")
@RequiredArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE population SET deleted_at = current_date WHERE id=?")
@Where(clause = "deleted_at IS NULL")
public class Population extends BaseEntity implements Slugable {

    /**
     * Enum of all possible procurements ot a population
     */
    public enum PopulationProductionUnit {
        /**
         * Population creates Militias
         */
        Militias,

        /**
         * Population creates Income
         */
        Income,

        /**
         * Population creates Favor
         */
        Favor
    }

    @Column(name = "name", columnDefinition = "varchar(255)")
    private String name;

    @Column(name = "slug", nullable = false, unique = true,columnDefinition = "varchar(255)")
    private String slug;

    @OneToMany(mappedBy = "population")
    @JsonManagedReference
    @ToString.Exclude
    private Set<Population_Requirement> requiredGoods;

    @OneToMany(mappedBy = "population")
    @JsonBackReference
    @ToString.Exclude
    private Set<Requirement_Population_Building> requiredBuilding;

    /**
     * get the Type of the production for this population
     *
     * @return PopulationProductionUnit of this entity
     */
    public PopulationProductionUnit getPopulationProductionUnit() {
        if (getName() == null) {
            return null;
        }

        if (getName().equals("Pioneers")) {
            return PopulationProductionUnit.Militias;
        }

        if (getName().equals("Paragons")) {
            return PopulationProductionUnit.Favor;
        }

        return PopulationProductionUnit.Income;
    }

    @Override
    public String getSlug() {
        return this.slug;
    }

    public void setSlug(String slug) {
        if (!SlugUtil.validateSlug(slug)) {
            throw new IllegalStateException("Could not create Slug for" + slug);
        }

        this.slug = slug;
    }
}
