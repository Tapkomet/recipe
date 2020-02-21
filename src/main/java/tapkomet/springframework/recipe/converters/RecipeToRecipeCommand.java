package tapkomet.springframework.recipe.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import tapkomet.springframework.recipe.commands.CategoryCommand;
import tapkomet.springframework.recipe.commands.RecipeCommand;
import tapkomet.springframework.recipe.domain.Recipe;

import java.util.HashSet;

/**
 * Created by Tapkomet on 2/12/2020
 */
@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {


    private final CategoryToCategoryCommand categoryConverter;
    private final IngredientToIngredientCommand ingredientConverter;
    private final NotesToNotesCommand notesConverter;

    public RecipeToRecipeCommand(CategoryToCategoryCommand categoryConverter,
                                 IngredientToIngredientCommand ingredientConverter, NotesToNotesCommand notesConverter) {
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
        this.notesConverter = notesConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe source) {
        if (source == null) {
            return null;
        }
        final RecipeCommand command = RecipeCommand.builder().id(source.getId()).cookTime(source.getCookTime())
                .prepTime(source.getPrepTime()).description(source.getDescription()).difficulty(source.getDifficulty())
                .directions(source.getDirections()).servings(source.getServings()).source(source.getSource())
                .url(source.getUrl()).categories(new HashSet<>()).image(source.getImage())
                .ingredients(new HashSet<>()).notes(notesConverter.convert(source.getNotes())).build();


        if (source.getCategories() != null && source.getCategories().size() > 0) {

            source.getCategories()
                    .forEach(category ->
                            command.getCategories()
                                    .add(new CategoryToCategoryCommand()
                                            .convert(category)));
        }

        if (source.getIngredients() != null && source.getIngredients().size() > 0) {
            source.getIngredients()
                    .forEach(ingredient -> command.getIngredients().add(ingredientConverter.convert(ingredient)));
        }
        return command;
    }
}
