package tapkomet.springframework.recipe.repositories;

import org.springframework.data.repository.CrudRepository;
import tapkomet.springframework.recipe.domain.Category;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    Optional<Category> findByName(String name);
}
