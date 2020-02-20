package tapkomet.springframework.recipe.services.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tapkomet.springframework.recipe.commands.IngredientCommand;
import tapkomet.springframework.recipe.commands.UnitOfMeasureCommand;
import tapkomet.springframework.recipe.converters.IngredientCommandToIngredient;
import tapkomet.springframework.recipe.converters.IngredientToIngredientCommand;
import tapkomet.springframework.recipe.converters.UnitOfMeasureCommandToUnitOfMeasure;
import tapkomet.springframework.recipe.converters.UnitOfMeasureToUnitOfMeasureCommand;
import tapkomet.springframework.recipe.domain.Ingredient;
import tapkomet.springframework.recipe.domain.Recipe;
import tapkomet.springframework.recipe.repositories.IngredientRepository;
import tapkomet.springframework.recipe.repositories.RecipeRepository;
import tapkomet.springframework.recipe.services.IngredientService;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class IngredientServiceImplTest {

    private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();
    private final UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure = new UnitOfMeasureCommandToUnitOfMeasure();

    private final IngredientToIngredientCommand ingredientToIngredientCommand = new IngredientToIngredientCommand(unitOfMeasureToUnitOfMeasureCommand);

    private final IngredientCommandToIngredient ingredientCommandToIngredient = new IngredientCommandToIngredient(unitOfMeasureCommandToUnitOfMeasure);

    private static final Long ID_1 = 1L;
    private static final Long ID_2 = 2L;
    private static final Long ID_3 = 3L;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    IngredientRepository ingredientRepository;


    private IngredientService ingredientService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        ingredientService = new IngredientServiceImpl(ingredientToIngredientCommand, recipeRepository,
                ingredientRepository, ingredientCommandToIngredient);
    }

    @Test
    void findByRecipeIdAndIngredientId() {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(ID_1);
        recipe.setDescription("test Recipe");

        Ingredient ingredient1 = Ingredient.builder().id(ID_1).build();
        Ingredient ingredient2 = Ingredient.builder().id(ID_2).build();
        Ingredient ingredient3 = Ingredient.builder().id(ID_3).build();

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);
        Optional<Recipe> optionalRecipe = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);

        //when
        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(ID_1, ID_3);

        //then
        assertEquals(ID_3, ingredientCommand.getId());
        assertEquals(ID_1, ingredientCommand.getRecipeId());
        verify(recipeRepository, times(1)).findById(anyLong());
    }


    @Test
    void saveIngredientCommand() {
        //given
        IngredientCommand command = IngredientCommand.builder().id(ID_3).build();
        Ingredient ingredient = ingredientCommandToIngredient.convert(command);

        when(ingredientRepository.save(any())).thenReturn(ingredient);

        //when
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

        //then
        assertEquals(ID_3, savedCommand.getId());

    }

    @Test
    void deleteById() throws Exception {

        //given

        //when
        ingredientService.deleteById(ID_2);

        //then
        verify(ingredientRepository, times(1)).deleteById(anyLong());
    }
}