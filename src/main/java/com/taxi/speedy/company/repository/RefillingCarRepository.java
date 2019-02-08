package com.taxi.speedy.company.repository;

import com.taxi.speedy.company.model.RefillingCar;

import java.time.LocalDate;
import java.util.List;

public interface RefillingCarRepository {
    RefillingCar save(RefillingCar refillingCar);
    boolean delete(int id);
    RefillingCar get(int id);
    RefillingCar getIdUserVehicle(int idUserVehicle);
    RefillingCar getIdUserBankCard(int idUserBankCard);
    List<RefillingCar> getAllIdUserVehicle( int idUserVehicle);
    List<RefillingCar> getAll();
    List<RefillingCar> getBetween(LocalDate startDate, LocalDate endDate);
}
