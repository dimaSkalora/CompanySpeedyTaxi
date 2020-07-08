package com.taxi.speedy.company.repository;

import com.taxi.speedy.company.model.Vehicle;

import java.util.List;

public interface VehicleRepository {
    Vehicle save(Vehicle vehicle);
    // false if not found
    boolean delete(int id);
    // null if not found
    Vehicle get(int id);
    List<Vehicle> getAll();
    List<Vehicle> getByNameCar(String nameCar);
    List<Vehicle> getByVehicleNumber(String vehicleNumber);
}
