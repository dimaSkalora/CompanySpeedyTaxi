package com.taxi.speedy.company.service;

import com.taxi.speedy.company.model.TaxiDispatcherOrder;
import com.taxi.speedy.company.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

public interface TaxiDispatcherOrderService {
    TaxiDispatcherOrder create(TaxiDispatcherOrder taxiDispatcherOrder);
    TaxiDispatcherOrder update(TaxiDispatcherOrder taxiDispatcherOrder) throws NotFoundException;//NotFoundException - Об'экт не обнаружен
    boolean delete(int id) throws NotFoundException;
    TaxiDispatcherOrder get(int id) throws NotFoundException;
    List<TaxiDispatcherOrder> getAll();
    List<TaxiDispatcherOrder> getByIdTaxiDispatcher(int idTaxiDispatcher) throws NotFoundException;
    List<TaxiDispatcherOrder> getByAddressDeparture(String addressDeparture) throws NotFoundException;
    List<TaxiDispatcherOrder> getByAddressArrival(String addressArrival) throws NotFoundException;
    List<TaxiDispatcherOrder> getByUserName(String userName) throws NotFoundException;
    List<TaxiDispatcherOrder> getByUserPhone(String userPhone) throws NotFoundException;
    List<TaxiDispatcherOrder> getByFulfilled(int fulfilled) throws NotFoundException;
    List<TaxiDispatcherOrder> getByBetweenStartDate(LocalDateTime startDateTime, LocalDateTime endDateTime) throws NotFoundException;
    List<TaxiDispatcherOrder> getByBetweenEndDate(LocalDateTime startDateTime, LocalDateTime endDateTime) throws NotFoundException;
    List<TaxiDispatcherOrder> getFilterTaxiDispatcherOrder(TaxiDispatcherOrder taxiDispatcherOrder) throws NotFoundException;
    List<TaxiDispatcherOrder> getFilterTaxiDispatcherOrder(TaxiDispatcherOrder taxiDispatcherOrder,String sqlCondition) throws NotFoundException;
}
