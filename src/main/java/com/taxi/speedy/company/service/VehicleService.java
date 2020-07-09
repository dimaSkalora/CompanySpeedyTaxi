package com.taxi.speedy.company.service;

import com.taxi.speedy.company.model.Vehicle;
import com.taxi.speedy.company.util.exception.NotFoundException;

import java.util.List;

public interface VehicleService {
    Vehicle create(Vehicle vehicle);
    void update(Vehicle vehicle) throws NotFoundException;
    boolean delete(int id) throws NotFoundException;
    Vehicle get(int id) throws NotFoundException;
    List<Vehicle> getAll();
    List<Vehicle> getByNameCar(String nameCar) throws NotFoundException;
    List<Vehicle> getVehicleNumber(String vehicleNumber) throws NotFoundException;
}
