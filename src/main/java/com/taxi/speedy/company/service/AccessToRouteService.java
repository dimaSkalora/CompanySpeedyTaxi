package com.taxi.speedy.company.service;

import com.taxi.speedy.company.model.AccessToRoute;
import com.taxi.speedy.company.util.exception.NotFoundException;

import java.util.List;

public interface AccessToRouteService {
    AccessToRoute create(AccessToRoute accessToRoute);
    AccessToRoute update(AccessToRoute accessToRoute) throws NotFoundException;//NotFoundException - Об'экт не обнаружен
    AccessToRoute get(int id) throws NotFoundException;
    boolean delete(int id) throws NotFoundException;
    List<AccessToRoute> getAll();
    List<AccessToRoute> getByUserVehicle(int idUserVehicle) throws NotFoundException;
    List<AccessToRoute> getByUserState(int idUserState) throws NotFoundException;
    List<AccessToRoute> getByVehicleState(int idVehicleState) throws NotFoundException;
    List<AccessToRoute> getByIsAccess(int isAccess) throws NotFoundException;
}
