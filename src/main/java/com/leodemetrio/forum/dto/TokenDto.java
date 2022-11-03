package com.leodemetrio.forum.dto;

public class TokenDto {
    private final String token;
    private final String typeContentType;

    public TokenDto(String token, String typeContentType) {

        this.token = token;
        this.typeContentType = typeContentType;
    }

    public String getToken() {
        return token;
    }

    public String getTypeContentType() {
        return typeContentType;
    }
}
