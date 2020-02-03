package tapkomet.springframework.recipe.repositories;

import org.springframework.data.repository.CrudRepository;
import tapkomet.springframework.recipe.domain.UnitOfMeasure;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {

    Optional<UnitOfMeasure> findByName(String name);
}
