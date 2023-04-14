package edu.bsuir.onlineshop.web.criteria;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryCriteria {

    @Size(min = 1)
    private String name;

    @Size(min = 1)
    private String description;

}
