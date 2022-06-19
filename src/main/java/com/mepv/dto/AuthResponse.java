package com.mepv.dto;

public class AuthResponse {

    private String jwtToken;

    public AuthResponse() {
    }

    public AuthResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getJwtToken() {
        return jwtToken;
    }
}
