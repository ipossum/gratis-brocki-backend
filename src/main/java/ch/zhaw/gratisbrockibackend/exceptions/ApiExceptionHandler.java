package ch.zhaw.gratisbrockibackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.time.OffsetDateTime;

/**
 * Handles all custom-made exceptions
 * With the current setup, these exceptions may be handled differently
 */

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

    @ExceptionHandler(FileException.class)
    public ResponseEntity<Object> handleFileException (FileException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ApiException apiException= new ApiException(
                e.getMessage(),
                badRequest,
                OffsetDateTime.now()
        );
        return new ResponseEntity<>(apiException, badRequest);

    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<Object> handleMaxSizeException (MaxUploadSizeExceededException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        String message = "Maximum filesize exceeded";

        ApiException apiException= new ApiException(
                message,
                badRequest,
                OffsetDateTime.now()
        );
        return new ResponseEntity<>(apiException, badRequest);

    }

}
