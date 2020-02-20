package tapkomet.springframework.recipe.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tapkomet.springframework.recipe.commands.IngredientCommand;
import tapkomet.springframework.recipe.converters.IngredientCommandToIngredient;
import tapkomet.springframework.recipe.converters.IngredientToIngredientCommand;
import tapkomet.springframework.recipe.domain.Ingredient;
import tapkomet.springframework.recipe.domain.Recipe;
import tapkomet.springframework.recipe.repositories.IngredientRepository;
import tapkomet.springframework.recipe.repositories.RecipeRepository;
import tapkomet.springframework.recipe.services.IngredientService;

import java.util.Optional;

/**
 * Created by Tapkomet on 2/17/2020
 */

@Service
@Slf4j
public class IngredientServiceImpl implements IngredientService {

    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;

    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand,
                                 RecipeRepository recipeRepository,
                                 IngredientRepository ingredientRepository,
                                 IngredientCommandToIngredient ingredientCommandToIngredient) {
        this.recipeRepository = recipeRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientRepository = ingredientRepository;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
    }


    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(long recipeId, long ingredientId) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);
        //TODO error handling

        Recipe recipe = optionalRecipe.get();

        Optional<IngredientCommand> optionalIngredientCommand = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredientToIngredientCommand::convert).findFirst();

        //TODO also error handling

        return optionalIngredientCommand.get();
    }

    @Override
    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {
        Ingredient detachedIngredient = ingredientCommandToIngredient.convert(command);

        Ingredient savedIngredient = ingredientRepository.save(detachedIngredient);
        log.debug("Saved IngredientId:" + savedIngredient.getId());
        return ingredientToIngredientCommand.convert(savedIngredient);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        ingredientRepository.deleteById(id);
    }
}
