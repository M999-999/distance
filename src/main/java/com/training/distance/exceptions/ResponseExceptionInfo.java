package com.training.distance.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ResponseExceptionInfo {

    private String message;
    private LocalDateTime dateTime;
}