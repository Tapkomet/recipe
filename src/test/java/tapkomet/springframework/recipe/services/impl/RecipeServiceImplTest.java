package tapkomet.springframework.recipe.services.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tapkomet.springframework.recipe.domain.Recipe;
import tapkomet.springframework.recipe.repositories.RecipeRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;


    private static final Recipe TEST_RECIPE = new Recipe();
    private static final Recipe[] RECIPES_ARRAY = new Recipe[] {TEST_RECIPE, new Recipe()};
    private static final Set<Recipe> TEST_RECIPES = new HashSet<>(Arrays.asList(RECIPES_ARRAY));

    @Mock
    RecipeRepository recipeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    void getRecipes() {

        when(recipeRepository.findAll()).thenReturn(TEST_RECIPES);

        assertEquals(1, recipeService.getRecipes().size());
        verify(recipeRepository, times(1)).findAll();
    }
}