package com.taxi.speedy.company.repository;

import com.taxi.speedy.company.model.AccessToRoute;

import java.util.List;

public interface AccessToRouteRepository {
    AccessToRoute save(AccessToRoute accessToRoute);
    boolean delete(int id);
    AccessToRoute get (int id);
    List<AccessToRoute> getAll();
    List<AccessToRoute> getByUserVehicle(int idUserVehicle);
    List<AccessToRoute> getByUserState(int idUserState);
    List<AccessToRoute> getByVehicleState(int idVehicleState);
    List<AccessToRoute> getByIsAccess(int isAccess);
}
