package com.epam.homework3.model.exception;

import com.epam.homework3.model.enums.ErrorType;

public class EntityException extends ServiceException {
    private static final String DEFAULT_MESSAGE = "entity is not found!";

    public EntityException(String message) {
        super(message);
    }

    public EntityException() {
        super(DEFAULT_MESSAGE);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.VALIDATION_ERROR_TYPE;
    }
}
