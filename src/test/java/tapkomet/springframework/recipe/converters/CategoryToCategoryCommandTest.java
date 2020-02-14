package tapkomet.springframework.recipe.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tapkomet.springframework.recipe.commands.CategoryCommand;
import tapkomet.springframework.recipe.domain.Category;

import static org.junit.jupiter.api.Assertions.*;

class CategoryToCategoryCommandTest {
    private static final Long ID_VALUE = 1L;
    private static final String NAME = "descript";
    CategoryToCategoryCommand convter;

    @BeforeEach
    void setUp() throws Exception {
        convter = new CategoryToCategoryCommand();
    }

    @Test
    void testNullObject() throws Exception {
        assertNull(convter.convert(null));
    }

    @Test
    void testEmptyObject() throws Exception {
        assertNotNull(convter.convert(new Category()));
    }

    @Test
    void convert() throws Exception {
        //given
        Category category = new Category();
        category.setId(ID_VALUE);
        category.setName(NAME);

        //when
        CategoryCommand categoryCommand = convter.convert(category);

        //then
        assert categoryCommand != null;
        assertEquals(ID_VALUE, categoryCommand.getId());
        assertEquals(NAME, categoryCommand.getName());

    }
}