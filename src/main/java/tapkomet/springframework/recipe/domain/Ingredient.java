package tapkomet.springframework.recipe.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Tapkomet on 1/31/2020
 */
@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
@Table(
        name="INGREDIENT",
        uniqueConstraints=
        @UniqueConstraint(columnNames={"DESCRIPTION", "RECIPE_ID"})
)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal amount;

    @OneToOne
    private UnitOfMeasure unitOfMeasure;

    @ManyToOne
    private Recipe recipe;

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure unitOfMeasure) {
        this.description = description;
        this.amount = amount;
        this.unitOfMeasure = unitOfMeasure;
    }

    public String toString() {
        return "Ingredient(id=" + this.getId() + ", description=" + this.getDescription()
                + ", amount=" + this.getAmount() + ", unitOfMeasure=" + this.getUnitOfMeasure()
                + ", recipe=" + this.getRecipe().getDescription() + ")";
    }

}
