package tapkomet.springframework.recipe.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    Category category;

    private static final Long TEST_ID = 10L;
    private static final String TEST_NAME = "TestName";
    private static final Recipe TEST_RECIPE = new Recipe();
    private static final Recipe[] RECIPES_ARRAY = new Recipe[] {TEST_RECIPE, new Recipe()};
    private static final Set<Recipe> TEST_RECIPES = new HashSet<>(Arrays.asList(RECIPES_ARRAY));

    @BeforeEach
    void setUp(){
        category = new Category();
    }

    @Test
    void getId() {
        category.setId(TEST_ID);
        assertEquals(category.getId(), TEST_ID);
    }

    @Test
    void getName() {
        category.setName(TEST_NAME);
        assertEquals(category.getName(), TEST_NAME);
    }

    @Test
    void getRecipes() {
        category.setRecipes(TEST_RECIPES);
        assertEquals(category.getRecipes(), TEST_RECIPES);
    }
}