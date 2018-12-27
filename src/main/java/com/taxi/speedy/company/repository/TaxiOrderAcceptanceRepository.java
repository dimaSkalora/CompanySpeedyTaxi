package com.taxi.speedy.company.repository;

import com.taxi.speedy.company.model.TaxiOrderAcceptance;

import java.util.List;

public interface TaxiOrderAcceptanceRepository {
    TaxiOrderAcceptance save(TaxiOrderAcceptance taxiOrderAcceptance, int idUserVehicle);
    boolean delete(int id, int idUserVehicle);
    TaxiOrderAcceptance get(int id, int idUserVehicle);
    TaxiOrderAcceptance getIdTaxiDispatcherOrder(int idTaxiDispatcherOrder);
    TaxiOrderAcceptance getIdTaxiUserOrder(int idTaxiUserOrder);
    List<TaxiOrderAcceptance> getAllIdUserVehicle(int idUserVehicle);
    List<TaxiOrderAcceptance> getAllIdTaxiDispatcherOrder(int idTaxiDispatcherOrder);
    List<TaxiOrderAcceptance> getAllIdTaxiUserOrder(int idTaxiUserOrder);
    List<TaxiOrderAcceptance> getAllExecutionStatus(int executionStatus);            //Статус выполнение: 1-выполнено, 0- не выполнено
    List<TaxiOrderAcceptance> getAllAdoptionStatus(int adoptionStatus);              //Статус принятие: 1-принятый заказ, 0- не принятый заказ
    List<TaxiOrderAcceptance> getAll();
}
