package com.training.distance.exceptions;

import lombok.Getter;

@Getter
public class AppException extends RuntimeException {

    private final AppExceptionCodes info;
    private final Object[] params;

    public AppException(AppExceptionCodes info) {
        this.info = info;
        this.params = new Object[0];
    }

    public AppException(AppExceptionCodes info, Object... params) {
        this.info = info;
        this.params = params;
    }
}