package tapkomet.springframework.recipe.services.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tapkomet.springframework.recipe.domain.UnitOfMeasure;
import tapkomet.springframework.recipe.repositories.UnitOfMeasureRepository;
import tapkomet.springframework.recipe.services.UnitOfMeasureService;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UnitOfMeasureServiceImplTest {

    private static final Long ID_1 = 1L;
    private static final Long ID_2 = 2L;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    @InjectMocks
    UnitOfMeasureServiceImpl service;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getUnitsOfMeasure() {
        //given
        Set<UnitOfMeasure> unitOfMeasures = new HashSet<>();
        UnitOfMeasure unitOfMeasure1 = UnitOfMeasure.builder().id(ID_1).build();
        UnitOfMeasure unitOfMeasure2 = UnitOfMeasure.builder().id(ID_2).build();

        unitOfMeasures.add(unitOfMeasure1);
        unitOfMeasures.add(unitOfMeasure2);

        when(unitOfMeasureRepository.findAll()).thenReturn(unitOfMeasures);

        //when
        Set<UnitOfMeasure> units = service.getUnitsOfMeasure();

        //then
        assertEquals(2, units.size());
        verify(unitOfMeasureRepository, times(1)).findAll();
    }
}