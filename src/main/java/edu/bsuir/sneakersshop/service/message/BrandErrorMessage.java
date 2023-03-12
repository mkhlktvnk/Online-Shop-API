package edu.bsuir.sneakersshop.service.message;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Getter
@PropertySource("classpath:error-message.properties")
public class BrandErrorMessage {
    @Value("classpath:brand.not-found.message")
    private String notFoundMessage;

    @Value("classpath:brand.already-exists.message")
    private String alreadyExistsMessage;
}
