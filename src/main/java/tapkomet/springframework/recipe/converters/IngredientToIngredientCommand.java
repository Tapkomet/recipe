package tapkomet.springframework.recipe.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import tapkomet.springframework.recipe.commands.IngredientCommand;
import tapkomet.springframework.recipe.domain.Ingredient;

/**
 * Created by Tapkomet on 2/12/2020
 */
@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

    private final UnitOfMeasureToUnitOfMeasureCommand uomcConverter;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand uomcConverter) {
        this.uomcConverter = uomcConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredient source) {
        if (source == null) {
            return null;
        }

        return IngredientCommand.builder().id(source.getId()).description(source.getDescription())
                .amount(source.getAmount()).recipeId((source.getRecipe() == null)? null : source.getRecipe().getId() )
                .unitOfMeasure(uomcConverter.convert(source.getUnitOfMeasure())).build();
    }
}
