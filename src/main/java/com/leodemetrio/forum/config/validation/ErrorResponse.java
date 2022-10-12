package com.leodemetrio.forum.config.validation;

public class ErrorResponse {

    private String field;
    private String error;

    public ErrorResponse(String field, String error) {
        this.field = field;
        this.error = error;
    }

    public String getField() {
        return field;
    }

    public String getError() {
        return error;
    }
}
