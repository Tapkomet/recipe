package tapkomet.springframework.recipe.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tapkomet.springframework.recipe.commands.RecipeCommand;
import tapkomet.springframework.recipe.domain.Recipe;
import tapkomet.springframework.recipe.exceptions.NotFoundException;
import tapkomet.springframework.recipe.services.RecipeService;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class RecipeControllerTest {

    private static final Long RECIPE_ID = 1L;
    private static final Long RECIPE_ID2 = 2L;

    private MockMvc mockMvc;

    @Mock
    RecipeService recipeService;

    @InjectMocks
    RecipeController controller;

    private Recipe recipe;

    @BeforeEach
    void setUp() {
        controller = new RecipeController(recipeService);

        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ExceptionHandlerController()).build();
    }

    @Test
    void getRecipe() throws Exception {
        Recipe recipe = Recipe.builder().id(RECIPE_ID).build();

        when(recipeService.findById(anyLong())).thenReturn(recipe);

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("/recipe/show"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    void getRecipeNotFound() throws Exception {
        when(recipeService.findById(anyLong())).thenThrow(NotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/show"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("404error"));
    }

    @Test
    void getRecipeNumberFormatException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/asd/show"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("400error"));
    }

    @Test
    void getNewRecipeForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("/recipe/recipeform"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    void postNewRecipeForm() throws Exception {
        RecipeCommand command = RecipeCommand.builder().id(RECIPE_ID2).build();

        when(recipeService.saveRecipeCommand(any())).thenReturn(command);

        mockMvc.perform(post("/recipe")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("description", "some string")
                .param("directions", "some directions")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/2/show"));
    }

    @Test
    void postNewRecipeFormValidationFail() throws Exception {
        RecipeCommand command = RecipeCommand.builder().id(RECIPE_ID2).build();

        mockMvc.perform(post("/recipe")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
        )
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipe"))
                .andExpect(view().name("/recipe/recipeform"));
    }

    @Test
    void getUpdateView() throws Exception {
        RecipeCommand command = RecipeCommand.builder().id(RECIPE_ID2).build();

        when(recipeService.findCommandById(anyLong())).thenReturn(command);

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("/recipe/recipeform"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    void deleteRecipeById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/index"));

        verify(recipeService, times(1)).deleteById(anyLong());
    }
}