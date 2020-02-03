package tapkomet.springframework.recipe.domain;

import java.io.Serializable;

/**
 * Created by Tapkomet on 11/12/2019
 */
public class BaseEntity implements Serializable {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
