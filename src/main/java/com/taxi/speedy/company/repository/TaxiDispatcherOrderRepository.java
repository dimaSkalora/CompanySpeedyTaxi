package com.taxi.speedy.company.repository;

import com.taxi.speedy.company.model.TaxiDispatcherOrder;

import java.time.LocalDate;
import java.util.List;

public interface TaxiDispatcherOrderRepository {
    TaxiDispatcherOrder save(TaxiDispatcherOrder taxiDispatcherOrder);
    boolean delete(int id);
    TaxiDispatcherOrder get(int id);
    List<TaxiDispatcherOrder> getAll();
    List<TaxiDispatcherOrder> getByIdTaxiDispatcher(int idTaxiDispatcher);
    List<TaxiDispatcherOrder> getByAddressDeparture(String addressDeparture);
    List<TaxiDispatcherOrder> getByAddressArrival(String addressArrival);
    List<TaxiDispatcherOrder> getByUserName(String userName);
    List<TaxiDispatcherOrder> getByPhoneUser(String phoneUser);
    List<TaxiDispatcherOrder> getByFulfilled(int fulfilled);
    List<TaxiDispatcherOrder> getByAllFulfilled(int fulfilled);
    List<TaxiDispatcherOrder> getByBetween(LocalDate startDate, LocalDate endDate);
    List<TaxiDispatcherOrder> getFilterTaxiDispatcherOrder(TaxiDispatcherOrder taxiDispatcherOrder);
}
