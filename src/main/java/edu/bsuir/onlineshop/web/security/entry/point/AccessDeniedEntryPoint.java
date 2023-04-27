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
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AccessDeniedEntryPoint implements AccessDeniedHandler {
    private final ObjectMapper objectMapper;
    private final MessagesSource messages;

    @Override
    @SneakyThrows
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) {
        response.setCharacterEncoding("UTF8");
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);

        String message = messages.getMessage(MessageKey.FORBIDDEN);

        ApiError apiError = ApiError.builder()
                .details(List.of(message, accessDeniedException.getMessage()))
                .status(HttpStatus.FORBIDDEN.value())
                .build();

        response.getWriter().write(objectMapper.writeValueAsString(apiError));
    }
}
