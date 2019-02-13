package com.taxi.speedy.company.repository;

import com.taxi.speedy.company.model.ComingConsuptionFuel;

import java.util.List;

public interface ComingConsuptionFuelRepository {
    ComingConsuptionFuel save(ComingConsuptionFuel comingConsuptionFuel);
    boolean delete(int id);
    ComingConsuptionFuel get(int id);
    ComingConsuptionFuel getIdUserVehicle(int idUserVehicle);
    ComingConsuptionFuel getDateCCF(String dateCCF);
    List<ComingConsuptionFuel> getAll();
    List<ComingConsuptionFuel> getAllIdUserVehicle(int idUserVehicle);
}
