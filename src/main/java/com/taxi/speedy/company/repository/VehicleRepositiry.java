package com.taxi.speedy.company.repository;

import com.taxi.speedy.company.model.Vehicle;

import java.util.List;

public interface VehicleRepositiry {
    Vehicle save(Vehicle vehicle);
    // false if not found
    boolean delete(int id);
    // null if not found
    Vehicle get(int id);
    List<Vehicle> getAll();
    // null if not found
    Vehicle getByNameCar(String nameCar);
    Vehicle getByVehicleNumber(String vehicleNumber);
}
