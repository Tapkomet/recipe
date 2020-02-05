package tapkomet.springframework.recipe.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Tapkomet on 1/31/2020
 */
@Data
@Entity
public class UnitOfMeasure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
