package com.example.paragonPioneerBackend.Controller;

import com.example.paragonPioneerBackend.Dto.Cost_Building_PopulationDTO;
import com.example.paragonPioneerBackend.Entity.JoinTables.Cost_Building_Population;
import com.example.paragonPioneerBackend.Repository.Cost_Building_PopulationRepository;
import com.example.paragonPioneerBackend.Service.Cost_Building_PopulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/api/v1/cost/building_population")
public class Cost_Building_PopulationController extends BaseController<Cost_Building_Population, Cost_Building_PopulationRepository, Cost_Building_PopulationDTO, Cost_Building_PopulationService> {

    @Autowired
    public Cost_Building_PopulationController(Cost_Building_PopulationService service) {
        super(service);
    }
}