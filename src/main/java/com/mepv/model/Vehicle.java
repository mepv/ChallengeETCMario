package com.mepv.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.Instant;

@Entity
public class Vehicle extends PanacheEntity {

    public String make;
    public String model;
    public String color;
    public int year;
    @Column(name = "created_at")
    public Instant createdAt = Instant.now();

    public Vehicle() {
    }

    public Vehicle(String make, String model, String color, int year) {
        this.make = make;
        this.model = model;
        this.color = color;
        this.year = year;
    }
}