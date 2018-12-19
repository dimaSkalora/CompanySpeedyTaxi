package com.taxi.speedy.company.repository;

import com.taxi.speedy.company.model.UserVehicle;

import java.time.LocalDate;
import java.util.List;

public interface UserVehicleRepository {
    UserVehicle save(UserVehicle userVehicle, int idUser);
    boolean delete(int id, int idUser);
    UserVehicle getByUser(int id, int idUser);
    UserVehicle getByVehicle(int id, int idVehicle);
    List<UserVehicle> getAllByUser(int idUser);
    List<UserVehicle> getAllByVehicle(int idVehicle);
    List<UserVehicle> getByIsCurrentUserMachine(int isCurrentUserMachine);
    // ORDERED date desc
    List<UserVehicle> getBetween(LocalDate startDate, LocalDate endDate, int userId);
    List<UserVehicle> getBetween(LocalDate startDate, LocalDate endDate);
}
