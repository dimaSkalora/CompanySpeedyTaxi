package com.taxi.speedy.company.repository;

import com.taxi.speedy.company.model.AccessToRoute;

import java.util.List;

public interface AccessToRouteRepository {
    AccessToRoute save(AccessToRoute accessToRoute, int idUserVehicle);
    boolean delete(int id, int idUserVehicle);
    AccessToRoute get (int id, int idUserVehicle);
    List<AccessToRoute> getAll(int idUserVehicle);
    List<AccessToRoute> getAll();
    AccessToRoute getByUserVehicle(int idUserVehicle);
    AccessToRoute getByIsAccess(int isAccess, int idUserVehicle);
}
