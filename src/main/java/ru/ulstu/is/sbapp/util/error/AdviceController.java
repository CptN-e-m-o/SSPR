package ru.ulstu.is.sbapp.util.error;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.ulstu.is.sbapp.student.service.WorkerNotFoundException;
import ru.ulstu.is.sbapp.student.service.PositionNotFoundException;
import ru.ulstu.is.sbapp.student.service.DepartmentNotFoundException;
import ru.ulstu.is.sbapp.util.validation.ValidationException;

import java.util.stream.Collectors;

@ControllerAdvice
public class AdviceController {
    @ExceptionHandler({
            WorkerNotFoundException.class,
            PositionNotFoundException.class,
            DepartmentNotFoundException.class,
            ValidationException.class
    })
    public ResponseEntity<Object> handleException(Throwable e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleBindException(MethodArgumentNotValidException e) {
        final ValidationException validationException = new ValidationException(
                e.getBindingResult().getAllErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.toSet()));
        return handleException(validationException);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUnknownException(Throwable e) {
        e.printStackTrace();
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
