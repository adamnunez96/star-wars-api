package com.anunez.conexa.star.wars.enums;

public enum ErrorMessage {
    INTERNAL_SERVER_ERROR("01", "Internal Server Error"),
    NOT_FOUND("02", "Resource Not Found"),
    BAD_REQUEST("03", "Bad Request"),
    UNAUTHORIZED("04", "Unauthorized"),
    FORBIDDEN("05", "Forbidden"),
    UNPROCESSABLE_ENTITY("06", "Unprocessable Entity");

    private final String code;
    private final String message;

    ErrorMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
    public static ErrorMessage fromCode(String code) {
        for (ErrorMessage errorMessage : values()) {
            if (errorMessage.getCode().equals(code)) {
                return errorMessage;
            }
        }
        return null;
    }
    public static ErrorMessage fromMessage(String message) {
        for (ErrorMessage errorMessage : values()) {
            if (errorMessage.getMessage().equals(message)) {
                return errorMessage;
            }
        }
        return null;
    }
}
