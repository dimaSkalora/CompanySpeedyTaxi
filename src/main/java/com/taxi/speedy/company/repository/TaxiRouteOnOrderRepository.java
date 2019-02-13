package com.taxi.speedy.company.repository;

import com.taxi.speedy.company.model.TaxiRouteOnOrder;

import java.util.List;

public interface TaxiRouteOnOrderRepository {
    TaxiRouteOnOrder save(TaxiRouteOnOrder taxiRouteOnOrder);
    boolean delete(int id);
    TaxiRouteOnOrder get(int id);
    TaxiRouteOnOrder getIdUserVehicle(int idUserVehicle);
    TaxiRouteOnOrder getIdTaxiOrderAcceptance(int idTaxiOrderAcceptance);
    List<TaxiRouteOnOrder> getAllIdUserVehicle(int idUserVehicle);
    List<TaxiRouteOnOrder> getAll();
}
