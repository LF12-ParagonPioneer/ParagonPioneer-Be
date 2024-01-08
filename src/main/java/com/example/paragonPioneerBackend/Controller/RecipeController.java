package com.example.paragonPioneerBackend.Controller;

import com.example.paragonPioneerBackend.Dto.RecipeDTO;
import com.example.paragonPioneerBackend.Entity.Recipe;
import com.example.paragonPioneerBackend.Repository.RecipeRepository;
import com.example.paragonPioneerBackend.Service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(path = "/api/v1/recipe")
public class RecipeController extends BaseController<Recipe, RecipeRepository, RecipeDTO, RecipeService> {

    @Autowired
    public RecipeController(RecipeService service) {
        super(service);
    }
}