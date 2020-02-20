package tapkomet.springframework.recipe.services;

import tapkomet.springframework.recipe.commands.UnitOfMeasureCommand;
import tapkomet.springframework.recipe.domain.UnitOfMeasure;

import java.util.Set;

public interface UnitOfMeasureService {
    Set<UnitOfMeasure> getUnitsOfMeasure();
}
