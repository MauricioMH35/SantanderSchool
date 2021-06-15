package com.school.santander.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ObjectTranscriptException extends RuntimeException {
    public ObjectTranscriptException(String message) {
        super(message);
    }
}