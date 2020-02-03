package tapkomet.springframework.recipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import tapkomet.springframework.recipe.domain.Category;
import tapkomet.springframework.recipe.domain.UnitOfMeasure;
import tapkomet.springframework.recipe.repositories.CategoryRepository;
import tapkomet.springframework.recipe.repositories.UnitOfMeasureRepository;

import java.util.Optional;

/**
 * Created by Tapkomet on 12/18/2019
 */
@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(){

        Optional<Category> categoryOptional = categoryRepository.findByName("American");
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByName("Teaspoon");

        System.out.println("Cat id is "+categoryOptional.get().getId());
        System.out.println("UoM id is "+unitOfMeasureOptional.get().getId());

        return "index";
    }
}
