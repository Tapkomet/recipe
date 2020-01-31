package tapkomet.springframework.recipe.repositories;

import org.springframework.data.repository.CrudRepository;
import tapkomet.springframework.recipe.domain.Recipe;

/**
 * Created by Tapkomet on 1/31/2020
 */
public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}
