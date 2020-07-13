package com.taxi.speedy.company.service;

import com.taxi.speedy.company.model.UserVehicle;
import com.taxi.speedy.company.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface UserVehicleService {
    UserVehicle create(UserVehicle userVehicle);
    void update(UserVehicle userVehicle) throws NotFoundException;//NotFoundException - Об'экт не обнаружен
    boolean delete(int id) throws NotFoundException;
    UserVehicle get(int id) throws NotFoundException;
    List<UserVehicle> getAll();
    List<UserVehicle> getAllByUser(int idUser) throws NotFoundException;
    List<UserVehicle> getAllByVehicle(int idVehicle) throws NotFoundException;
    List<UserVehicle> getByIsCurrentUserMachine(int isCurrentUserMachine) throws NotFoundException;
    //ORDER BY DATE DESC - сортируем по убыванию
    List<UserVehicle> getStartDateBetween(LocalDate startDate, LocalDate endDate);
    List<UserVehicle> getEndDateBetween(LocalDate startDate, LocalDate endDate);
}
