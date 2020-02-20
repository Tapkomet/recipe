package tapkomet.springframework.recipe.domain;

import lombok.*;

import javax.persistence.*;

/**
 * Created by Tapkomet on 12/19/2019
 */
@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Recipe recipe;

    @Lob
    private String recipeNotes;

    public String toString() {
        return "Notes(id=" + this.getId() + ", recipe=" + this.getRecipe().getDescription() + ", recipeNotes="
                + this.getRecipeNotes() + ")";
    }
}
