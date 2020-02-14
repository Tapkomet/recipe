package tapkomet.springframework.recipe.commands;

import lombok.*;
import tapkomet.springframework.recipe.domain.Recipe;

/**
 * Created by Tapkomet on 2/12/2020
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotesCommand {
    private Long id;
    private String recipeNotes;
}
