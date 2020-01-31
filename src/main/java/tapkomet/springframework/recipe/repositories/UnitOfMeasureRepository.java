package tapkomet.springframework.recipe.repositories;

import org.springframework.data.repository.CrudRepository;
import tapkomet.springframework.recipe.domain.UnitOfMeasure;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {
}
