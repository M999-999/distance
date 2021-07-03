package com.training.distance.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AppExceptionCodes {
    DUPLICATED_PROPERTY_VALUE(HttpStatus.BAD_REQUEST, "duplicated_property_value"),
    EMPTY_PROPERTY_VALUE(HttpStatus.BAD_REQUEST, "empty_property_value"),
    ENTITY_NOT_FOUND(HttpStatus.NOT_FOUND, "entity_not_found"),
    ROUTER_NOT_FOUND(HttpStatus.BAD_REQUEST, "router_not_found");

    private HttpStatus status;
    private String messageKey;
}