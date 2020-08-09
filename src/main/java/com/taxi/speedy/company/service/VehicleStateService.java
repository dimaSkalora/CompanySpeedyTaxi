package com.taxi.speedy.company.service;

import com.taxi.speedy.company.model.VehicleState;
import com.taxi.speedy.company.util.exception.NotFoundException;

import java.util.List;

public interface VehicleStateService {
    VehicleState create(VehicleState vehicleState);
    VehicleState update(VehicleState vehicleState) throws NotFoundException;
    VehicleState get(int id) throws NotFoundException;
    boolean delete(int id) throws NotFoundException;
    List<VehicleState> getAll();
    VehicleState getByNameVS(String nameVS) throws NotFoundException;
}
