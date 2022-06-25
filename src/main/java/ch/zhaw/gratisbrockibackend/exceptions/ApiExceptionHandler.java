package ch.zhaw.gratisbrockibackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.OffsetDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<Object> handleUserException (UserException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ApiException apiException= new ApiException(
                e.getMessage(),
                badRequest,
                OffsetDateTime.now()
        );
        return new ResponseEntity<>(apiException, badRequest);

    }

    @ExceptionHandler(ItemException.class)
    public ResponseEntity<Object> handleItemException (ItemException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ApiException apiException= new ApiException(
                e.getMessage(),
                badRequest,
                OffsetDateTime.now()
        );
        return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler(PictureException.class)
    public ResponseEntity<Object> handlePictureException (PictureException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ApiException apiException = new ApiException(
                e.getMessage(),
                badRequest,
                OffsetDateTime.now()
        );
        return new ResponseEntity<>(apiException, badRequest);
    }

}
