package edu.bsuir.sneakersshop.web.specification;

import edu.bsuir.sneakersshop.domain.entity.Product;
import net.kaczmarzyk.spring.data.jpa.domain.Between;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Join;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Or;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

@Join(path = "brands", alias = "brands")
@Or({
        @Spec(path = "name", params = "name", spec = Like.class),
        @Spec(path = "description", params = "description", spec = Like.class),
        @Spec(path = "price", params = { "minPrice", "maxPrice" }, spec = Between.class),
        @Spec(path = "brands.name", params = "brandName", spec = Like.class)
})
public interface ProductSpecification extends Specification<Product> {
}
