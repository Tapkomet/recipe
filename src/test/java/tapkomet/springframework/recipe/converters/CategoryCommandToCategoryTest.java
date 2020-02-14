package tapkomet.springframework.recipe.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tapkomet.springframework.recipe.commands.CategoryCommand;
import tapkomet.springframework.recipe.domain.Category;

import static org.junit.jupiter.api.Assertions.*;

class CategoryCommandToCategoryTest {

    private static final Long ID_VALUE = 1L;
    private static final String NAME = "name";
    private CategoryCommandToCategory converter;

    @BeforeEach
    void setUp() throws Exception {
        converter = new CategoryCommandToCategory();
    }

    @Test
    void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new CategoryCommand()));
    }

    @Test
    void convert() throws Exception {
        //given
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(ID_VALUE);
        categoryCommand.setName(NAME);

        //when
        Category category = converter.convert(categoryCommand);

        //then
        assert category != null;
        assertEquals(ID_VALUE, category.getId());
        assertEquals(NAME, category.getName());
    }
}