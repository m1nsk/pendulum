package com.minsk.pendulum.util.exception;

import org.springframework.http.HttpStatus;

public class InvalidDeviceInstructionsExeption extends ApplicationException {
    public static final String INVALID_DEVICE_INSTRUCTIONS_EXCEPTION = "exception.common.dataStorage";

    public InvalidDeviceInstructionsExeption(String arg) {
        super(ErrorType.INVALID_DEVICE_INSTRUCTIONS_ERROR, INVALID_DEVICE_INSTRUCTIONS_EXCEPTION, HttpStatus.BAD_REQUEST, arg);
    }
}