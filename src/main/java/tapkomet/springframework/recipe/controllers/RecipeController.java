package tapkomet.springframework.recipe.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tapkomet.springframework.recipe.commands.RecipeCommand;
import tapkomet.springframework.recipe.exceptions.NotFoundException;
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

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception e) {
        log.error("Handling not found exception");
        log.error(e.getMessage());
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("404error");
        modelAndView.addObject("exception", e);
        return modelAndView;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleNumberFormatException(Exception e) {
        log.error("Handling number format exception");
        log.error(e.getMessage());
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("400error");
        modelAndView.addObject("exception", e);
        return modelAndView;
    }
}
