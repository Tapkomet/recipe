package tapkomet.springframework.recipe.bootstrap;

import tapkomet.springframework.recipe.domain.Category;
import tapkomet.springframework.recipe.domain.UnitOfMeasure;
import tapkomet.springframework.recipe.repositories.CategoryRepository;
import tapkomet.springframework.recipe.repositories.RecipeRepository;
import tapkomet.springframework.recipe.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile({"dev", "prod"})
public class BootstrapMySQL implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public BootstrapMySQL(CategoryRepository categoryRepository,
                          UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        if (categoryRepository.count() == 0L){
            log.debug("Loading Categories");
            loadCategories();
        }

        if (unitOfMeasureRepository.count() == 0L){
            log.debug("Loading UOMs");
            loadUom();
        }
    }

    private void loadCategories(){
        Category cat1 = new Category();
        cat1.setName("American");
        categoryRepository.save(cat1);

        Category cat2 = new Category();
        cat2.setName("Italian");
        categoryRepository.save(cat2);

        Category cat3 = new Category();
        cat3.setName("Mexican");
        categoryRepository.save(cat3);

        Category cat4 = new Category();
        cat4.setName("Fast Food");
        categoryRepository.save(cat4);
    }

    private void loadUom(){
        UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setName("Teaspoon");
        unitOfMeasureRepository.save(uom1);

        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom2.setName("Tablespoon");
        unitOfMeasureRepository.save(uom2);

        UnitOfMeasure uom3 = new UnitOfMeasure();
        uom3.setName("Cup");
        unitOfMeasureRepository.save(uom3);

        UnitOfMeasure uom4 = new UnitOfMeasure();
        uom4.setName("Pinch");
        unitOfMeasureRepository.save(uom4);

        UnitOfMeasure uom5 = new UnitOfMeasure();
        uom5.setName("Ounce");
        unitOfMeasureRepository.save(uom5);

        UnitOfMeasure uom6 = new UnitOfMeasure();
        uom6.setName("Each");
        unitOfMeasureRepository.save(uom6);

        UnitOfMeasure uom7 = new UnitOfMeasure();
        uom7.setName("Pint");
        unitOfMeasureRepository.save(uom7);

        UnitOfMeasure uom8 = new UnitOfMeasure();
        uom8.setName("Dash");
        unitOfMeasureRepository.save(uom8);
    }
}
