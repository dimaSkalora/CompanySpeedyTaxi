package com.taxi.speedy.company.repository;

import com.taxi.speedy.company.model.TaxiDispatcherOrder;

import java.time.LocalDate;
import java.util.List;

public interface TaxiDispatcherOrderRepository {
    TaxiDispatcherOrder save(TaxiDispatcherOrder taxiDispatcherOrder, int idTaxiDispatcher);
    boolean delete(int id, int idTaxiDispatcher);
    boolean delete(int id);
    TaxiDispatcherOrder get(int id, int idTaxiDispatcher);
    List<TaxiDispatcherOrder> getAllIdTaxiDispatcher(int idTaxiDispatcher);
    List<TaxiDispatcherOrder> getAll();
    List<TaxiDispatcherOrder> getAllAddressDeparture(String addressDeparture);
    List<TaxiDispatcherOrder> getAllAddressArrival(String addressArrival);
    TaxiDispatcherOrder getUserName(String userName);
    TaxiDispatcherOrder getPhoneUser(String phoneUser);
    TaxiDispatcherOrder getFulfilled(int fulfilled, int idTaxiDispatcher);
    List<TaxiDispatcherOrder> getAllFulfilled(int fulfilled);
    List<TaxiDispatcherOrder> getBetween(LocalDate startDate, LocalDate endDate);
}
