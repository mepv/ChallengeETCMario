package com.mepv.dto;

import java.util.UUID;

public class VehicleDTO {

    private String make;
    private String model;
    private String color;
    private int year;
    private UUID uuid;

    public VehicleDTO() {
    }

    public VehicleDTO(String make, String model, String color, int year, UUID uuid) {
        this.make = make;
        this.model = model;
        this.color = color;
        this.year = year;
        this.uuid = uuid;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }
}