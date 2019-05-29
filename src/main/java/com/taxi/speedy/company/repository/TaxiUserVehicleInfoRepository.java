package com.taxi.speedy.company.repository;

import com.taxi.speedy.company.model.TaxiUserVehicleInfo;

import java.util.List;

public interface TaxiUserVehicleInfoRepository {
    TaxiUserVehicleInfo save(TaxiUserVehicleInfo taxiUserVehicleInfo);
    boolean delete(int id);
    TaxiUserVehicleInfo get(int id);
    TaxiUserVehicleInfo getIdUserVehicle(int idUserVehicle);
    TaxiUserVehicleInfo getIdTaxiJobStatus(int idTaxiJobStatus);
    List<TaxiUserVehicleInfo> getAllIdUserVehicle(int idUserVehicle);
    List<TaxiUserVehicleInfo> getAllIdTaxiJobStatus(int idTaxiJobStatus);
    List<TaxiUserVehicleInfo> getAll();
}
