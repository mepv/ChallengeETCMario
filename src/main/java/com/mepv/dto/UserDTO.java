package com.mepv.dto;

import java.util.UUID;

public class UserDTO {

    private String username;
    private String role;
    private UUID uuid;

    public UserDTO() {
    }

    public UserDTO(String username, String role, UUID uuid) {
        this.username = username;
        this.role = role;
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
