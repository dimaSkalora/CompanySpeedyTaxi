package com.taxi.speedy.company.repository;

import com.taxi.speedy.company.model.UserVehicle;

import java.time.LocalDate;
import java.util.List;

public interface UserVehicleRepository {
    UserVehicle save(UserVehicle userVehicle);
    boolean delete(int id);
    UserVehicle get(int id);
    List<UserVehicle> getAll();
    List<UserVehicle> getAllByUser(int idUser);
    List<UserVehicle> getAllByVehicle(int idVehicle);
    List<UserVehicle> getByIsCurrentUserMachine(int isCurrentUserMachine);
    // ORDERED date desc
    List<UserVehicle> getBetween(LocalDate startDate, LocalDate endDate);
}
