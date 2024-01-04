package com.example.paragonPioneerBackend.Repository;

import com.example.paragonPioneerBackend.Entity.Population;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PopulationRepository extends JpaRepository<Population, UUID> {
    public Population findByNameIs(String name);
}