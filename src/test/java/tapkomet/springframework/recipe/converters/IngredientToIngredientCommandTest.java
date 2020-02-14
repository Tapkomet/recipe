package tapkomet.springframework.recipe.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tapkomet.springframework.recipe.commands.IngredientCommand;
import tapkomet.springframework.recipe.domain.Ingredient;
import tapkomet.springframework.recipe.domain.Recipe;
import tapkomet.springframework.recipe.domain.UnitOfMeasure;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class IngredientToIngredientCommandTest {
    private static final Recipe RECIPE = new Recipe();
    private static final BigDecimal AMOUNT = new BigDecimal("1");
    private static final String DESCRIPTION = "Cheeseburger";
    private static final Long UOM_ID = 2L;
    private static final Long ID_VALUE = 1L;


    IngredientToIngredientCommand converter;

    @BeforeEach
    void setUp() throws Exception {
        converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    void testNullConvert() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Ingredient()));
    }

    @Test
    void testConvertNullUOM() throws Exception {
        //given

        Ingredient ingredient = Ingredient.builder().id(ID_VALUE).amount(AMOUNT)
                .description(DESCRIPTION).unitOfMeasure(null).build();
        //when
        IngredientCommand ingredientCommand = converter.convert(ingredient);
        //then
        assert ingredientCommand != null;
        assertNull(ingredientCommand.getUnitOfMeasure());
        assertEquals(ID_VALUE, ingredientCommand.getId());
        // assertEquals(RECIPE, ingredientCommand.get);
        assertEquals(AMOUNT, ingredientCommand.getAmount());
        assertEquals(DESCRIPTION, ingredientCommand.getDescription());
    }

    @Test
    void testConvertWithUom() throws Exception {
        //given



        UnitOfMeasure uom = UnitOfMeasure.builder().id(UOM_ID).build();

        Ingredient ingredient = Ingredient.builder().id(ID_VALUE).amount(AMOUNT)
                .description(DESCRIPTION).unitOfMeasure(uom).build();
        //when
        IngredientCommand ingredientCommand = converter.convert(ingredient);
        //then
        assert ingredientCommand != null;
        assertEquals(ID_VALUE, ingredientCommand.getId());
        assertNotNull(ingredientCommand.getUnitOfMeasure());
        assertEquals(UOM_ID, ingredientCommand.getUnitOfMeasure().getId());
        // assertEquals(RECIPE, ingredientCommand.get);
        assertEquals(AMOUNT, ingredientCommand.getAmount());
        assertEquals(DESCRIPTION, ingredientCommand.getDescription());
    }
}