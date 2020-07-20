package com.taxi.speedy.company.util;

import com.taxi.speedy.company.dto.UserVehicleFull;
import com.taxi.speedy.company.model.User;
import com.taxi.speedy.company.model.UserVehicle;
import com.taxi.speedy.company.model.Vehicle;

public class UserVehiclesUtil {
    public UserVehiclesUtil() {
    }

    public static UserVehicleFull createUserVehicle(UserVehicle userVehicle, User user, Vehicle vehicle){
        return new UserVehicleFull(userVehicle.getId(), userVehicle.getStartDate(), userVehicle.getEndDate(),
                userVehicle.getIsCurrentUserMachine(), user.getId(), user.getName(), user.getEmail(), user.getPassword(),
                user.getPhone(), user.getAddress(), user.getRoles(), user.getRegistered(), user.isEnabled(),
                vehicle.getId(),vehicle.getNameCar(), vehicle.getVehicleNumber(), vehicle.getYearIssue(),
                vehicle.getCategory(), vehicle.getColor(), vehicle.getFuelConsumption());
    }
}
