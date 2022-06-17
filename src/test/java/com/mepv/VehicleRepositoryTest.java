package com.mepv;

import com.mepv.model.Vehicle;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class VehicleRepositoryTest {

    @Test
    @TestTransaction
    void shouldCreateAndFindAVehicle() {
        Vehicle vehicle = new Vehicle("Ferrari", "458 Italia", "Red", 2009);

        vehicle.persist();
        assertNotNull(vehicle.id);

        vehicle = Vehicle.findById(vehicle.id);
        assertEquals("Ferrari", vehicle.getMake());
    }
}