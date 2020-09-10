package com.taxi.speedy.company.service;

import com.taxi.speedy.company.model.TaxiOrderAcceptance;
import com.taxi.speedy.company.util.exception.NotFoundException;

import java.util.List;

public interface TaxiOrderAcceptanceService {
    TaxiOrderAcceptance create(TaxiOrderAcceptance taxiOrderAcceptance);
    void update(TaxiOrderAcceptance taxiOrderAcceptance);
    boolean delete(int id) throws NotFoundException;//NotFoundException - Об'экт не обнаружен
    TaxiOrderAcceptance get(int id) throws NotFoundException;//NotFoundException - Об'экт не обнаружен
    List<TaxiOrderAcceptance> getAll();
    List<TaxiOrderAcceptance> filterTaxiUserOrder(TaxiOrderAcceptance taxiOrderAcceptance);
    List<TaxiOrderAcceptance> filterTaxiUserOrder(TaxiOrderAcceptance taxiOrderAcceptance,String sqlCondition);
}
