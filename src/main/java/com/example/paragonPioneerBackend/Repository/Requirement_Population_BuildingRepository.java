package com.example.paragonPioneerBackend.Repository;


import com.example.paragonPioneerBackend.Entity.JoinTables.Requirement_Population_Building;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface Requirement_Population_BuildingRepository extends JpaRepository<Requirement_Population_Building, UUID> {
}