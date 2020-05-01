package com.eduprimehub.alpha.models.objects;

public class BusinessException extends Exception {
    private final int errorCode;
    private final String errorMessage;

    public BusinessException(int errorCode) {
        super();
        this.errorCode = errorCode;
        //Todo: Create a responseCodeHandler
//        this.errorMessage = ResponseCodeHandler.getMessage(errorCode);
        this.errorMessage = "error";
    }

    public BusinessException(String message) {
        super();
        this.errorCode = 0;
        this.errorMessage = message;
    }

    public BusinessException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.errorMessage = message;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public String getMessage() {
        return this.errorMessage;
    }
}
