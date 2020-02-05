package tapkomet.springframework.recipe.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Tapkomet on 1/31/2020
 */
@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;

}
