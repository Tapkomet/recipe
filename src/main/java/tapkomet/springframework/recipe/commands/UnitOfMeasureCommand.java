package tapkomet.springframework.recipe.commands;

import lombok.*;

/**
 * Created by Tapkomet on 2/12/2020
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UnitOfMeasureCommand {
    private Long id;
    private String name;
}
