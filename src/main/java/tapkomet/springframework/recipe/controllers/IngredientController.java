package tapkomet.springframework.recipe.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tapkomet.springframework.recipe.commands.IngredientCommand;
import tapkomet.springframework.recipe.commands.UnitOfMeasureCommand;
import tapkomet.springframework.recipe.domain.Ingredient;
import tapkomet.springframework.recipe.services.IngredientService;
import tapkomet.springframework.recipe.services.RecipeService;
import tapkomet.springframework.recipe.services.UnitOfMeasureService;

/**
 * Created by Tapkomet on 2/17/2020
 */
@Slf4j
@Controller
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;

    IngredientController(RecipeService recipeService, IngredientService ingredientService,
                         UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping("/recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable String recipeId, Model model) {
        log.debug("Getting ingredient list by recipe id");

        model.addAttribute("recipe", recipeService.findCommandById(new Long(recipeId)));

        return "/recipe/ingredient/list";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{id}/show")
    public String showRecipeIngredient(@PathVariable String recipeId, @PathVariable String id, Model model) {
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(new Long(recipeId), new Long(id)));
        return "/recipe/ingredient/show";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/new")
    public String newIngredient(@PathVariable String recipeId, Model model) {
        model.addAttribute("ingredient", IngredientCommand.builder().recipeId(new Long(recipeId))
                .unitOfMeasure(new UnitOfMeasureCommand()).build());
        model.addAttribute("unitOfMeasureList", unitOfMeasureService.getUnitsOfMeasure());
        return "/recipe/ingredient/ingredientform";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{id}/update")
    public String updateIngredient(@PathVariable String recipeId, @PathVariable String id, Model model) {
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(new Long(recipeId), new Long(id)));
        model.addAttribute("unitOfMeasureList", unitOfMeasureService.getUnitsOfMeasure());
        return "/recipe/ingredient/ingredientform";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{id}/delete")
    public String deleteIngredientById(@PathVariable String recipeId, @PathVariable String id, Model model) throws Exception {
        ingredientService.deleteById(new Long(id));
        log.debug("Deleting ingredient number " + id);

        return "redirect:/recipe/" + new Long(recipeId) + "/ingredients";
    }

    @PostMapping("/recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@PathVariable String recipeId, @ModelAttribute IngredientCommand command) {
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";
    }
}
