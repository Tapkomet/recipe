package tapkomet.springframework.recipe.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tapkomet.springframework.recipe.commands.CategoryCommand;
import tapkomet.springframework.recipe.commands.IngredientCommand;
import tapkomet.springframework.recipe.commands.NotesCommand;
import tapkomet.springframework.recipe.commands.RecipeCommand;
import tapkomet.springframework.recipe.domain.Difficulty;
import tapkomet.springframework.recipe.domain.Recipe;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class RecipeCommandToRecipeTest {
    private static final Long RECIPE_ID = 1L;
    private static final Integer COOK_TIME = Integer.valueOf("5");
    private static final Integer PREP_TIME = Integer.valueOf("7");
    private static final String DESCRIPTION = "My Recipe";
    private static final String DIRECTIONS = "Directions";
    private static final Difficulty DIFFICULTY = Difficulty.EASY;
    private static final Integer SERVINGS = Integer.valueOf("3");
    private static final String SOURCE = "Source";
    private static final String URL = "Some URL";
    private static final Long CAT_ID_1 = 1L;
    private static final Long CAT_ID_2 = 2L;
    private static final Long INGRED_ID_1 = 3L;
    private static final Long INGRED_ID_2 = 4L;
    private static final Long NOTES_ID = 9L;

    RecipeCommandToRecipe converter;


    @BeforeEach
    void setUp() throws Exception {
        converter = new RecipeCommandToRecipe(new CategoryCommandToCategory(),
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                new NotesCommandToNotes());
    }

    @Test
    void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new RecipeCommand()));
    }

    @Test
    void convert() throws Exception {
        //given

        NotesCommand notes = NotesCommand.builder().id(NOTES_ID).build();

        CategoryCommand category = CategoryCommand.builder().id(CAT_ID_1).build();

        CategoryCommand category2 = CategoryCommand.builder().id(CAT_ID_2).build();

        HashSet<CategoryCommand> categorySet = new HashSet<>();
        categorySet.add(category);
        categorySet.add(category2);


        IngredientCommand ingredient = IngredientCommand.builder().id(INGRED_ID_1).build();

        IngredientCommand ingredient2 = IngredientCommand.builder().id(INGRED_ID_2).build();

        HashSet<IngredientCommand> ingredientSet = new HashSet<>();
        ingredientSet.add(ingredient);
        ingredientSet.add(ingredient2);

        RecipeCommand recipeCommand = RecipeCommand.builder().id(RECIPE_ID).cookTime(COOK_TIME).prepTime(PREP_TIME)
                .description(DESCRIPTION).difficulty(DIFFICULTY).directions(DIRECTIONS).servings(SERVINGS)
                .source(SOURCE).url(URL).notes(notes).categories(categorySet).ingredients(ingredientSet).build();

        //when
        Recipe recipe = converter.convert(recipeCommand);

        assertNotNull(recipe);
        assertEquals(RECIPE_ID, recipe.getId());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(PREP_TIME, recipe.getPrepTime());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(URL, recipe.getUrl());
        assertEquals(NOTES_ID, recipe.getNotes().getId());
        assertEquals(2, recipe.getCategories().size());
        assertEquals(2, recipe.getIngredients().size());
    }
}