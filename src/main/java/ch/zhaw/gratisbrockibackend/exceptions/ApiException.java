package ch.zhaw.gratisbrockibackend.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.OffsetDateTime;

/**
 * Template for all exceptions sent as an HTTP response
 */
@Getter
@AllArgsConstructor
public class ApiException {
    private final String message;
    private final HttpStatus httpStatus;
    private final OffsetDateTime timestamp;
}
