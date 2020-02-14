package tapkomet.springframework.recipe.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tapkomet.springframework.recipe.commands.NotesCommand;
import tapkomet.springframework.recipe.domain.Notes;

import static org.junit.jupiter.api.Assertions.*;

class NotesToNotesCommandTest {
    private static final Long ID_VALUE = 1L;
    private static final String RECIPE_NOTES = "Notes";
    private NotesToNotesCommand converter;

    @BeforeEach
    void setUp() throws Exception {
        converter = new NotesToNotesCommand();
    }

    @Test
    void convert() throws Exception {
        //given
        Notes notes = Notes.builder().id(ID_VALUE).recipeNotes(RECIPE_NOTES).build();

        //when
        NotesCommand notesCommand = converter.convert(notes);

        //then
        assert notesCommand != null;
        assertEquals(ID_VALUE, notesCommand.getId());
        assertEquals(RECIPE_NOTES, notesCommand.getRecipeNotes());
    }

    @Test
    void testNull() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Notes()));
    }
}