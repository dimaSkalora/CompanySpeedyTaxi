package com.taxi.speedy.company.repository;

import com.taxi.speedy.company.model.TaxiDispatcher;

import java.util.List;

public interface TaxiDispatcherRepository {
    TaxiDispatcher save (TaxiDispatcher taxiDispatcher);
    boolean delete(int id);
    TaxiDispatcher get(int id);
    List<TaxiDispatcher> getAll();
}
