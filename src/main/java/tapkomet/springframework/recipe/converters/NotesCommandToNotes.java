package tapkomet.springframework.recipe.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import tapkomet.springframework.recipe.commands.NotesCommand;
import tapkomet.springframework.recipe.domain.Notes;

/**
 * Created by Tapkomet on 2/12/2020
 */
@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {

    @Synchronized
    @Nullable
    @Override
    public Notes convert(NotesCommand source) {
        if (source == null) {
            return null;
        }

        return Notes.builder().id(source.getId()).recipeNotes(source.getRecipeNotes()).build();
    }
}
