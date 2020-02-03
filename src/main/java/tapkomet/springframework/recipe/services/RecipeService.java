package tapkomet.springframework.recipe.services;

import tapkomet.springframework.recipe.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
}
