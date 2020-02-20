package tapkomet.springframework.recipe.services.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tapkomet.springframework.recipe.converters.RecipeCommandToRecipe;
import tapkomet.springframework.recipe.converters.RecipeToRecipeCommand;
import tapkomet.springframework.recipe.domain.Recipe;
import tapkomet.springframework.recipe.repositories.RecipeRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;


    private static final Recipe TEST_RECIPE = new Recipe();
    private static final Recipe[] RECIPES_ARRAY = new Recipe[]{TEST_RECIPE, new Recipe()};
    private static final Set<Recipe> TEST_RECIPES = new HashSet<>(Arrays.asList(RECIPES_ARRAY));
    private static final Long RECIPE_ID = 1L;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    void getRecipes() {

        when(recipeRepository.findAll()).thenReturn(TEST_RECIPES);

        assertEquals(1, recipeService.getRecipes().size());
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    void findRecipeById() throws Exception {

        Recipe recipe = new Recipe();
        recipe.setId(RECIPE_ID);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe returnedRecipe = recipeService.findById(RECIPE_ID);

        assertNotNull(returnedRecipe, "Null recipe returned");
        verify(recipeRepository, times(1)).findById(RECIPE_ID);
        verify(recipeRepository, never()).findAll();
    }

    @Test
    void deleteById() throws Exception {

        //given
        Long idToDelete = 2L;

        //when
        recipeService.deleteById(idToDelete);

        //then
        verify(recipeRepository, times(1)).deleteById(anyLong());
    }
}