package com.taxi.speedy.company.repository;

import com.taxi.speedy.company.model.TaxiRoute;

import java.util.List;

public interface TaxiRouteRepository {
    TaxiRoute save(TaxiRoute taxiRoute);
    boolean delete(int id);
    TaxiRoute get(int id);
    TaxiRoute getIdUserVehicle(int idUserVehicle);
    TaxiRoute getIdUserBankCard(int idUserBankCard);
    TaxiRoute getIeparturePoint(String departurePoint);
    TaxiRoute getArrivalPoint(String arrivalPoint);
    List<TaxiRoute> getAllidUserVehicle(int idUserVehicle);
    List<TaxiRoute> getAllIdUserBankCard(int idUserBankCard);
    List<TaxiRoute> getAll();
}
