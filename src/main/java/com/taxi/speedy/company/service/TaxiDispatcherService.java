package com.taxi.speedy.company.service;

import com.taxi.speedy.company.model.TaxiDispatcher;
import com.taxi.speedy.company.util.exception.NotFoundException;

import java.util.List;

public interface TaxiDispatcherService {
    TaxiDispatcher create(TaxiDispatcher taxiDispatcher);
    TaxiDispatcher update(TaxiDispatcher taxiDispatcher) throws NotFoundException; //NotFoundException - Об'экт не обнаружен
    TaxiDispatcher get(int id) throws NotFoundException;
    List<TaxiDispatcher> getAll();
}
