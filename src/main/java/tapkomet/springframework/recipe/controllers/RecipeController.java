package tapkomet.springframework.recipe.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tapkomet.springframework.recipe.commands.RecipeCommand;
import tapkomet.springframework.recipe.services.RecipeService;

/**
 * Created by Tapkomet on 2/11/2020
 */
@Slf4j
@Controller
public class RecipeController {

    private final RecipeService recipeService;

    RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{id}/show")
    public String getRecipe(@PathVariable String id, Model model) throws Exception {

        model.addAttribute("recipe", recipeService.findById(new Long(id)));
        log.debug("found a recipe");

        return "/recipe/show";
    }

    @GetMapping("/recipe/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());
        log.debug("Showing recipe add form");

        return "/recipe/recipeform";
    }

    @GetMapping("/recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model) throws Exception {
        model.addAttribute("recipe", recipeService.findCommandById(new Long(id)));
        log.debug("Editing recipe number " + id);

        return "/recipe/recipeform";
    }

    @GetMapping("/recipe/{id}/delete")
    public String deleteRecipeById(@PathVariable String id, Model model) throws Exception {
        recipeService.deleteById(new Long(id));
        log.debug("Deleting recipe number " + id);

        return "redirect:/index";
    }

    @PostMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);

        return "redirect:/recipe/" + savedCommand.getId() + "/show";
    }
}
