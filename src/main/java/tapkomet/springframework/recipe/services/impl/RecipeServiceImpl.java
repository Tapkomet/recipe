package tapkomet.springframework.recipe.services.impl;

import org.springframework.stereotype.Service;
import tapkomet.springframework.recipe.domain.Recipe;
import tapkomet.springframework.recipe.repositories.RecipeRepository;
import tapkomet.springframework.recipe.services.RecipeService;

import java.util.HashSet;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipes() {
        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }
}
