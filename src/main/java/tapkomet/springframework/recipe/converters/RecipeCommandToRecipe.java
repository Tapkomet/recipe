package tapkomet.springframework.recipe.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import tapkomet.springframework.recipe.commands.RecipeCommand;
import tapkomet.springframework.recipe.domain.Recipe;

import java.util.HashSet;

/**
 * Created by Tapkomet on 2/12/2020
 */
@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {


    private final CategoryCommandToCategory categoryConverter;
    private final IngredientCommandToIngredient ingredientConverter;
    private final NotesCommandToNotes notesConverter;

    public RecipeCommandToRecipe(CategoryCommandToCategory categoryConverter,
                                 IngredientCommandToIngredient ingredientConverter, NotesCommandToNotes notesConverter) {
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
        this.notesConverter = notesConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand source) {
        if (source == null) {
            return null;
        }
        final Recipe recipe = Recipe.builder().id(source.getId()).cookTime(source.getCookTime())
                .prepTime(source.getPrepTime()).description(source.getDescription()).difficulty(source.getDifficulty())
                .directions(source.getDirections()).servings(source.getServings()).source(source.getSource())
                .url(source.getUrl()).categories(new HashSet<>()).image(source.getImage())
                .ingredients(new HashSet<>()).notes(notesConverter.convert(source.getNotes())).build();


        if (source.getCategories() != null && source.getCategories().size() > 0) {
            source.getCategories()
                    .forEach(category -> recipe.getCategories().add(categoryConverter.convert(category)));
        }

        if (source.getIngredients() != null && source.getIngredients().size() > 0) {
            source.getIngredients()
                    .forEach(ingredient -> recipe.getIngredients().add(ingredientConverter.convert(ingredient)));
        }
        return recipe;
    }
}
