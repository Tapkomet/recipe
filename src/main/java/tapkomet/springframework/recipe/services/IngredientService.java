package tapkomet.springframework.recipe.services;

import tapkomet.springframework.recipe.commands.IngredientCommand;
import tapkomet.springframework.recipe.domain.Ingredient;

import java.util.Set;

public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(long anyLong, long anyLong1);

    IngredientCommand saveIngredientCommand(IngredientCommand command);

    void deleteById(Long aLong);
}
