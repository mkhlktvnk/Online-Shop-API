package edu.bsuir.sneakersshop.service.message;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Getter
@PropertySource("classpath:message.properties")
public class ProductErrorMessage {
    @Value("classpath:product.not-found.message")
    private String notFoundMessage;
}



