package edu.bsuir.sneakersshop.domain.spec;

import edu.bsuir.sneakersshop.domain.entity.Brand;
import edu.bsuir.sneakersshop.domain.entity.Product;
import jakarta.persistence.criteria.Join;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

@UtilityClass
public class ProductSpecifications {

    public static Specification<Product> hasNameLike(String name) {
        return (root, query, builder) -> {
            if (name == null) {
                return builder.isTrue(builder.literal(true));
            }
            return builder.like(root.get("name"), "%" + name + "%");
        };
    }

    public static Specification<Product> hasDescriptionLike(String description) {
        return (root, query, builder) -> {
            if (description == null) {
                return builder.isTrue(builder.literal(true));
            }
            return builder.like(root.get("description"), "%" + description + "%");
        };
    }

    public static Specification<Product> hasBrandNameLike(String brandName) {
        return (root, query, builder) -> {
            if (brandName == null) {
                return builder.isTrue(builder.literal(true));
            }
            Join<Brand, Product> join = root.join("brand");
            return builder.like(join.get("name"), "%" + brandName + "%");
        };
    }

    public static Specification<Product> hasPriceBetween(BigDecimal min, BigDecimal max) {
        return (root, query, builder) -> {
            if (min == null || max == null) {
                return builder.isTrue(builder.literal(true));
            }
            return builder.between(root.get("price"), min, max);
        };
    }

}
