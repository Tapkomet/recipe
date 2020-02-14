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
    public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

    private final UnitOfMeasureCommandToUnitOfMeasure uomConverter;

    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Ingredient convert(IngredientCommand source) {
        if (source == null) {
            return null;
        }

        return Ingredient.builder().id(source.getId()).description(source.getDescription())
                .amount(source.getAmount()).unitOfMeasure(uomConverter.convert(source.getUnitOfMeasure())).build();
    }
}
