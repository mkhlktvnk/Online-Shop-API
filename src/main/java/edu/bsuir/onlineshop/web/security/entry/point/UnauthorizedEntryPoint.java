package edu.bsuir.onlineshop.web.security.entry.point;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.bsuir.onlineshop.service.message.MessageKey;
import edu.bsuir.onlineshop.service.message.MessagesSource;
import edu.bsuir.onlineshop.web.model.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UnauthorizedEntryPoint implements AuthenticationEntryPoint {
    private final MessagesSource messages;
    private final ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) {
        response.setCharacterEncoding("UTF8");
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);

        String message = messages.getMessage(MessageKey.UNAUTHORIZED);

        ApiError apiError = ApiError.builder()
                .details(List.of(message, authException.getMessage()))
                .status(HttpStatus.UNAUTHORIZED.value())
                .build();

        response.getWriter().write(objectMapper.writeValueAsString(apiError));
    }
}
