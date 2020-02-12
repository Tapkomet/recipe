package tapkomet.springframework.recipe.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tapkomet.springframework.recipe.domain.Recipe;
import tapkomet.springframework.recipe.repositories.RecipeRepository;
import tapkomet.springframework.recipe.services.RecipeService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipes() {
        log.debug("Getting all recipes");
        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }

    @Override
    public Recipe findById(Long id) throws RuntimeException {
        log.debug("Looking for recipe by id: " + id);
        Optional<Recipe> optionalRecipe = recipeRepository.findById(id);
        if (optionalRecipe.isPresent()) {
            return optionalRecipe.get();
        } else {
            throw new RuntimeException();
        }
    }
}
