package com.crossyf.dubbo.springtest.exception;

import lombok.Data;

@Data
public class GlobalException extends Exception {

    private static final long serialVersionUID = -238091758285157331L;

    private String code;
    private String message;

    public GlobalException() {
        super();
    }

    public GlobalException(String message) {
        super(message);
        this.message = message;
    }

    public GlobalException(String code, String message) {
        super(code + ": " + message);
        this.code = code;
        this.message = message;
    }

    public GlobalException(String message, Throwable throwable) {
        super(message, throwable);
        this.message = message;
    }

    public GlobalException(Throwable throwable) {
        super(throwable);
    }

    @Override
    public String toString() {
        return code + ": " + message;
    }
}
