package tapkomet.springframework.recipe.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import tapkomet.springframework.recipe.services.RecipeService;

/**
 * Created by Tapkomet on 2/11/2020
 */
@Slf4j
@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipe/show/{id}")
    public String getRecipe(@PathVariable String id, Model model) throws Exception {

        model.addAttribute("recipe", recipeService.findById(new Long(id)));
        log.debug("found a recipe");

        return "/recipe/show";
    }


}
