package tapkomet.springframework.recipe.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tapkomet.springframework.recipe.commands.RecipeCommand;
import tapkomet.springframework.recipe.domain.*;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class RecipeToRecipeCommandTest {
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
    private RecipeToRecipeCommand converter;

    @BeforeEach
    void setUp() throws Exception {
        converter = new RecipeToRecipeCommand(
                new CategoryToCategoryCommand(),
                new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
                new NotesToNotesCommand());
    }

    @Test
    void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Recipe()));
    }

    @Test
    void convert() throws Exception {
        //given

        Notes notes = Notes.builder().id(NOTES_ID).build();

        Category category = Category.builder().id(CAT_ID_1).build();

        Category category2 = Category.builder().id(CAT_ID_2).build();

        HashSet<Category> categorySet = new HashSet<>();
        categorySet.add(category);
        categorySet.add(category2);


        Ingredient ingredient = Ingredient.builder().id(INGRED_ID_1).build();

        Ingredient ingredient2 = Ingredient.builder().id(INGRED_ID_2).build();

        HashSet<Ingredient> ingredientSet = new HashSet<>();
        ingredientSet.add(ingredient);
        ingredientSet.add(ingredient2);

        Recipe recipe = Recipe.builder().id(RECIPE_ID).cookTime(COOK_TIME).prepTime(PREP_TIME)
                .description(DESCRIPTION).difficulty(DIFFICULTY).directions(DIRECTIONS).servings(SERVINGS)
                .source(SOURCE).url(URL).notes(notes).categories(categorySet).ingredients(ingredientSet).build();

        //when
        RecipeCommand command = converter.convert(recipe);

        //then
        assertNotNull(command);
        assertEquals(RECIPE_ID, command.getId());
        assertEquals(COOK_TIME, command.getCookTime());
        assertEquals(PREP_TIME, command.getPrepTime());
        assertEquals(DESCRIPTION, command.getDescription());
        assertEquals(DIFFICULTY, command.getDifficulty());
        assertEquals(DIRECTIONS, command.getDirections());
        assertEquals(SERVINGS, command.getServings());
        assertEquals(SOURCE, command.getSource());
        assertEquals(URL, command.getUrl());
        assertEquals(NOTES_ID, command.getNotes().getId());
        assertEquals(2, command.getCategories().size());
        assertEquals(2, command.getIngredients().size());

    }
}