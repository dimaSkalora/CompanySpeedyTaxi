package com.taxi.speedy.company.repository;

import com.taxi.speedy.company.model.TaxiUserOrder;

import java.time.LocalDate;
import java.util.List;

public interface TaxiUserOrderRepository {
    TaxiUserOrder save(TaxiUserOrder taxiUserOrder, int idUser);
    boolean delete(int id, int idUser);
    boolean delete(int id);
    TaxiUserOrder get(int id, int idUser);
    List<TaxiUserOrder> getAllIdUser(int idUser);
    List<TaxiUserOrder> getAll();
    List<TaxiUserOrder> getAllAddressDeparture(String addressDeparture);
    List<TaxiUserOrder> getAllAddressArrival(String addressArrival);
    TaxiUserOrder getFulfilled(int fulfilled, int idUser);
    List<TaxiUserOrder> getAllFulfilled(int fulfilled);
    List<TaxiUserOrder> getBetween(LocalDate startDate, LocalDate endDate);
}