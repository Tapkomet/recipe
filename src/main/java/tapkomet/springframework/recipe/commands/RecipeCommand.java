package tapkomet.springframework.recipe.commands;

import lombok.*;
import tapkomet.springframework.recipe.domain.Category;
import tapkomet.springframework.recipe.domain.Difficulty;
import tapkomet.springframework.recipe.domain.Ingredient;
import tapkomet.springframework.recipe.domain.Notes;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Tapkomet on 2/12/2020
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipeCommand {
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    private Set<IngredientCommand> ingredients = new HashSet<>();
    private byte[] image;
    private NotesCommand notes;
    private Difficulty difficulty;
    private Set<CategoryCommand> categories = new HashSet<>();
}
