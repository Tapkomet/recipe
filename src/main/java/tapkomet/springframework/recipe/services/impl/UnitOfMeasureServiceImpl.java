package tapkomet.springframework.recipe.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tapkomet.springframework.recipe.commands.UnitOfMeasureCommand;
import tapkomet.springframework.recipe.converters.UnitOfMeasureCommandToUnitOfMeasure;
import tapkomet.springframework.recipe.converters.UnitOfMeasureToUnitOfMeasureCommand;
import tapkomet.springframework.recipe.domain.UnitOfMeasure;
import tapkomet.springframework.recipe.repositories.UnitOfMeasureRepository;
import tapkomet.springframework.recipe.services.UnitOfMeasureService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public Set<UnitOfMeasure> getUnitsOfMeasure() {
        log.debug("Getting all units of measure");
        Set<UnitOfMeasure> unitOfMeasureSet = new HashSet<>();
        unitOfMeasureRepository.findAll().iterator().forEachRemaining(unitOfMeasureSet::add);
        return unitOfMeasureSet;
    }
}
