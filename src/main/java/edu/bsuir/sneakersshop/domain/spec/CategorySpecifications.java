package edu.bsuir.sneakersshop.domain.spec;

import edu.bsuir.sneakersshop.domain.entity.Category;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

@UtilityClass
public class CategorySpecifications {

    public static Specification<Category> hasNameLike(String name) {
        return (root, query, builder) -> {
            if (name == null) {
                return builder.isTrue(builder.literal(true));
            }
            return builder.like(root.get("name"), "%" + name + "%");
        };
    }

    public static Specification<Category> hasDescriptionLike(String description) {
        return (root, query, builder) -> {
            if (description == null) {
                return builder.isTrue(builder.literal(true));
            }
            return builder.like(root.get("description"), "%" + description + "%");
        };
    }

}
