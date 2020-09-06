package com.taxi.speedy.company.repository;

import com.taxi.speedy.company.model.TaxiOrderAcceptance;

import java.util.List;

public interface TaxiOrderAcceptanceRepository {
    TaxiOrderAcceptance save(TaxiOrderAcceptance taxiOrderAcceptance);
    boolean delete(int id);
    TaxiOrderAcceptance get(int id);
    List<TaxiOrderAcceptance> getAll();
    List<TaxiOrderAcceptance> filterTaxiUserOrder(TaxiOrderAcceptance taxiOrderAcceptance);
    List<TaxiOrderAcceptance> filterTaxiUserOrder(TaxiOrderAcceptance taxiOrderAcceptance,String sqlCondition);

}
