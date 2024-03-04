package com.example.paragonPioneerBackend.Entity.JoinTables;

import com.example.paragonPioneerBackend.Entity.BaseEntity;
import com.example.paragonPioneerBackend.Entity.Building;
import com.example.paragonPioneerBackend.Entity.Good;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * Entity for join table Cost_Building
 */
@Entity
@Getter
@Setter
@ToString
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "cost_building_goods")
@SQLDelete(sql = "UPDATE cost_building_goods SET deleted_at = current_date WHERE id=?")
@Where(clause = "deleted_at IS NULL")
public class Cost_Building_Goods extends BaseEntity {
    @ManyToOne()
    @JoinColumn(name = "good_id")
    @JsonManagedReference
    private Good good;

    @ManyToOne()
    @JsonBackReference
    @JoinColumn(name = "building_id")
    private Building building;

    @Column()
    private int amount;
}
