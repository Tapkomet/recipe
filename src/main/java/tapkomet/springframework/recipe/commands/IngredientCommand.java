package tapkomet.springframework.recipe.commands;

import lombok.*;
import tapkomet.springframework.recipe.domain.Recipe;
import tapkomet.springframework.recipe.domain.UnitOfMeasure;

import java.math.BigDecimal;

/**
 * Created by Tapkomet on 2/12/2020
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IngredientCommand {
    private Long id;
    private Long recipeId;
    private String description;
    private BigDecimal amount;
    private UnitOfMeasureCommand unitOfMeasure;
}
