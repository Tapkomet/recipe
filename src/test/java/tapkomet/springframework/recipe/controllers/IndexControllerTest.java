package tapkomet.springframework.recipe.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import tapkomet.springframework.recipe.domain.Recipe;
import tapkomet.springframework.recipe.services.RecipeService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class IndexControllerTest {

    private static final Recipe TEST_RECIPE = new Recipe();
    private static final Recipe[] RECIPES_ARRAY = new Recipe[]{TEST_RECIPE, new Recipe()};
    private static final Set<Recipe> TEST_RECIPES = new HashSet<>(Arrays.asList(RECIPES_ARRAY));

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    IndexController indexController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(recipeService);
    }

    @Test
    void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController)
                .setControllerAdvice(new ExceptionHandlerController()).build();

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void getIndexPage() {
        when(recipeService.getRecipes()).thenReturn(TEST_RECIPES);
        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        //when(model.addAttribute("recipes", recipeService.getRecipes())).thenReturn(model);
        assertEquals("index", indexController.getIndexPage(model));
        verify(recipeService, times(1)).getRecipes();
        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
        assertEquals(TEST_RECIPES, argumentCaptor.getValue());
    }
}