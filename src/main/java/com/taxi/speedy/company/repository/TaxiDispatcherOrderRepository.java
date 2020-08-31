package com.taxi.speedy.company.repository;

import com.taxi.speedy.company.model.TaxiDispatcherOrder;

import java.time.LocalDateTime;
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
    List<TaxiDispatcherOrder> getByUserPhone(String userPhone);
    List<TaxiDispatcherOrder> getByFulfilled(int fulfilled);
    List<TaxiDispatcherOrder> getByBetweenStartDate(LocalDateTime startDateTime, LocalDateTime endDateTime);
    List<TaxiDispatcherOrder> getByBetweenEndDate(LocalDateTime startDateTime, LocalDateTime endDateTime);
    List<TaxiDispatcherOrder> getFilterTaxiDispatcherOrder(TaxiDispatcherOrder taxiDispatcherOrder);
    List<TaxiDispatcherOrder> getFilterTaxiDispatcherOrder(TaxiDispatcherOrder taxiDispatcherOrder,String sqlCondition);
}
