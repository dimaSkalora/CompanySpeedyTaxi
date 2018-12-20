package com.taxi.speedy.company.repository;

import com.taxi.speedy.company.model.VehicleState;

import java.util.List;

public interface VehicleStateRepository {
    VehicleState save(VehicleState vehicleState);
    boolean delete(int id);
    VehicleState get(int id);
    List<VehicleState> getAll();
    VehicleState getByNameVS(String nameVS);
}
