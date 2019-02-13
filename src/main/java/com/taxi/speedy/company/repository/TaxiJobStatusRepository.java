package com.taxi.speedy.company.repository;

import com.taxi.speedy.company.model.TaxiJobStatus;

import java.util.List;

public interface TaxiJobStatusRepository {
    TaxiJobStatus save(TaxiJobStatus taxiJobStatus);
    boolean delete(int id);
    TaxiJobStatus get(int id);
    TaxiJobStatus getNameTJS(String name_tjs);
    List<TaxiJobStatus> getAll();
}
