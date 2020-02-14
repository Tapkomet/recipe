package tapkomet.springframework.recipe.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import tapkomet.springframework.recipe.commands.CategoryCommand;
import tapkomet.springframework.recipe.domain.Category;

/**
 * Created by Tapkomet on 2/12/2020
 */
@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {

    @Synchronized
    @Nullable
    @Override
    public CategoryCommand convert(Category source) {
        if (source == null) {
            return null;
        }

        return CategoryCommand.builder().id(source.getId()).name(source.getName()).build();
    }
}
