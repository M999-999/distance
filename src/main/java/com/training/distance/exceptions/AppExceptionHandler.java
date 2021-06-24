package com.training.distance.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.util.CollectionUtils.isEmpty;

/**
 * The global exception handler of application.
 */
@ControllerAdvice
@Slf4j
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    private AppExceptionResolver resolver;

    @Autowired
    public AppExceptionHandler(AppExceptionResolver resolver) {
        this.resolver = resolver;
    }

    /**
     * This methods handle all {@link AppException} exceptions.
     *
     * @param exception - the {@link AppException} exception object
     * @return {@link ResponseExceptionInfo} object with information.
     */
    @ExceptionHandler(AppException.class)
    public ResponseEntity<ResponseExceptionInfo> handleRequestValidationException(
            AppException exception) {
        ResponseEntity<ResponseExceptionInfo> responseExceptionInfo = resolver.resolve(exception);
        log.error(responseExceptionInfo.getBody().getMessage());

        return responseExceptionInfo;
    }

    /**
     * This method handle all {@link Exception} exceptions if there are no specified handlers for
     * them.
     *
     * @param ex - the {@link Exception} exception object
     * @param body - the body for the response
     * @param headers - the headers for the response
     * @param status - the response status
     * @param request - the current request
     * @return {@link ResponseExceptionInfo} object with information.
     */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(
                new ResponseExceptionInfo(ex.getMessage(), LocalDateTime.now()), status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        log.error(exception.getMessage(), exception);
        List<FieldError> errors = exception.getBindingResult().getFieldErrors();

        String message = exception.getMessage();
        //if (isNotEmpty(errors)) {
        //if (errors!=null && errors.size()>0) {
        if (!isEmpty(errors)) {
            message = errors.get(0).getDefaultMessage();
        }

        return new ResponseEntity<>(
                new ResponseExceptionInfo(message, LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }
}