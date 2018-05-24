package com.minsk.pendulum.util.exception;

public enum ErrorType {
    APP_ERROR("error.appError"),
    DATA_STORAGE_ERROR("error.dataStorageError"),
    INVALID_DEVICE_INSTRUCTIONS_ERROR("error.invalidDeviceInstructions"),
    WRONG_FILE_FORMAT_ERROR("error.wrongFileFormatError"),
    DATA_NOT_FOUND("error.dataNotFound"),
    DATA_ERROR("error.dataError"),
    VALIDATION_ERROR("error.validationError"),
    WRONG_REQUEST("error.wrongRequest");

    private final String errorCode;

    ErrorType(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
