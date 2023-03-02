package com.rightyon.todoapp.exception;

import lombok.Getter;

@Getter
public class ToDoAppException extends  RuntimeException {

    private final ErrorType errorType;


    public ToDoAppException(ErrorType errorType) {
        this.errorType = errorType;
    }

    public ToDoAppException(ErrorType errorType, String customMessage) {
        super(customMessage);
        this.errorType = errorType;
    }
}
