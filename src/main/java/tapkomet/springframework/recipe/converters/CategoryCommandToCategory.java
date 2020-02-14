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
    public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {

    @Synchronized
    @Nullable
    @Override
    public Category convert(CategoryCommand source) {
        if (source == null) {
            return null;
        }

        return Category.builder().id(source.getId()).name(source.getName()).build();
    }
}
