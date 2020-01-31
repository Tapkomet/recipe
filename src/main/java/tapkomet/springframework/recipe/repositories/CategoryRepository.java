package tapkomet.springframework.recipe.repositories;

import org.springframework.data.repository.CrudRepository;
import tapkomet.springframework.recipe.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
