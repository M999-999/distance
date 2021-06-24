package com.training.distance.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * This class is used to resolve {@link AppException} code and parameters for readable
 * message and response status code.
 * @see AppException
 * @see AppExceptionCodes
 */
@Component
public class AppExceptionResolver {

    private ReloadableResourceBundleMessageSource messageSource;

    @Autowired
    public AppExceptionResolver(ReloadableResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * This method resolves {@link AppException} code and its parameters for readable
     * message and response status code.
     *
     * @param exception - {@link AppException} exception object.
     * @return {@link ResponseExceptionInfo} object with resolved message and status code for
     *     response.
     */
    public ResponseEntity<ResponseExceptionInfo> resolve(AppException exception) {
        String message =
                messageSource.getMessage(
                        exception.getInfo().getMessageKey(), exception.getParams(), null);
        ResponseExceptionInfo responseExceptionInfo =
                new ResponseExceptionInfo(message, LocalDateTime.now());
        return new ResponseEntity<>(responseExceptionInfo, exception.getInfo().getStatus());
    }
}
