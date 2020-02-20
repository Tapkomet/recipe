package tapkomet.springframework.recipe.repositories;

import org.springframework.data.repository.CrudRepository;
import tapkomet.springframework.recipe.domain.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
}
