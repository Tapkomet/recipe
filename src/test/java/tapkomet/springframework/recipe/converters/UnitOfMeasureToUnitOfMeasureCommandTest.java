package tapkomet.springframework.recipe.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tapkomet.springframework.recipe.commands.UnitOfMeasureCommand;
import tapkomet.springframework.recipe.domain.UnitOfMeasure;

import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasureToUnitOfMeasureCommandTest {
    private static final String NAME = "name";
    private static final Long LONG_VALUE = 1L;

    private UnitOfMeasureToUnitOfMeasureCommand converter;

    @BeforeEach
    void setUp() throws Exception {
        converter = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    void testNullObjectConvert() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObj() throws Exception {
        assertNotNull(converter.convert(new UnitOfMeasure()));
    }

    @Test
    void convert() throws Exception {
        //given
        UnitOfMeasure uom = UnitOfMeasure.builder().id(LONG_VALUE).name(NAME).build();
        //when
        UnitOfMeasureCommand uomc = converter.convert(uom);

        //then
        assert uomc != null;
        assertEquals(LONG_VALUE, uomc.getId());
        assertEquals(NAME, uomc.getName());
    }
}