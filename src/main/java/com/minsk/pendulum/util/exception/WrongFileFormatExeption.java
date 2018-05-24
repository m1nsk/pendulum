package com.minsk.pendulum.util.exception;

import org.springframework.http.HttpStatus;

public class WrongFileFormatExeption extends ApplicationException {
    public static final String WRONG_FILE_FORMAT_EXCEPTION = "exception.common.dataStorage";

    public WrongFileFormatExeption(String arg) {
        super(ErrorType.WRONG_FILE_FORMAT_ERROR, WRONG_FILE_FORMAT_EXCEPTION, HttpStatus.BAD_REQUEST, arg);
    }
}