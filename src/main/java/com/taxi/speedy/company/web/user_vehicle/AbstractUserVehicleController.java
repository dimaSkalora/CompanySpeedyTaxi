package com.taxi.speedy.company.web.user_vehicle;

import com.taxi.speedy.company.dto.UserVehicleFull;
import com.taxi.speedy.company.model.User;
import com.taxi.speedy.company.model.UserVehicle;
import com.taxi.speedy.company.model.Vehicle;
import com.taxi.speedy.company.service.UserService;
import com.taxi.speedy.company.service.UserVehicleService;
import com.taxi.speedy.company.service.VehicleService;
import com.taxi.speedy.company.util.UserVehiclesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

public abstract class AbstractUserVehicleController {
    private final Logger log = LoggerFactory.getLogger(AbstractUserVehicleController.class);

    @Autowired
    private UserVehicleService userVehicleService;
    @Autowired
    private UserService userService;
    @Autowired
    private VehicleService vehicleService;

    public UserVehicle create(UserVehicle userVehicle){
        log.info("create {}",userVehicle);
        if (!userVehicle.isNew())
            throw new IllegalArgumentException(userVehicle+" должен быть новым (id == null)");
        return userVehicleService.create(userVehicle);
    }

    public UserVehicleFull createUVFull(UserVehicleFull userVehicleFull){
        log.info("createUVFull {}", userVehicleFull);
        if (!userVehicleFull.isNew())
            throw new IllegalArgumentException(userVehicleFull+" должен быть новым (id == null)");
        UserVehicle userVehicle = new UserVehicle();
        if (userVehicleFull.getStartDate() != null)
            userVehicle.setStartDate(userVehicleFull.getStartDate());
        if (userVehicleFull.getEndDate() != null)
            userVehicle.setEndDate(userVehicleFull.getEndDate());
        userVehicle.setIdUser(userService.get(userVehicleFull.getIdUser()));
        userVehicle.setIdVehicle(vehicleService.get(userVehicleFull.getIdVehicle()));
        userVehicle.setIsCurrentUserMachine(userVehicleFull.getIsCurrentUserMachine());

        userVehicleService.create(userVehicle);

        userVehicleFull.setId(userVehicle.getId());

        return userVehicleFull;
    }

    public UserVehicleFull updateUVFull(UserVehicleFull userVehicleFull){
        log.info("updateUVFull {}", userVehicleFull);
        if (userVehicleFull.isNew())
            throw new IllegalArgumentException(userVehicleFull+" должен быть не новым (id != null)");
        UserVehicle userVehicle = new UserVehicle();
        userVehicle.setId(userVehicle.getId());
        if (userVehicleFull.getStartDate() != null)
            userVehicle.setStartDate(userVehicleFull.getStartDate());
        if (userVehicleFull.getEndDate() != null)
            userVehicle.setEndDate(userVehicleFull.getEndDate());
        userVehicle.setIdUser(userService.get(userVehicleFull.getIdUser()));
        userVehicle.setIdVehicle(vehicleService.get(userVehicleFull.getIdVehicle()));
        userVehicle.setIsCurrentUserMachine(userVehicleFull.getIsCurrentUserMachine());

        userVehicleService.update(userVehicle);

        return userVehicleFull;
    }

    public void update(UserVehicle userVehicle){
        log.info("update {}",userVehicle);
        if (userVehicle.isNew())
            throw new IllegalArgumentException(userVehicle+" должен быть не новым (id != null)");
        userVehicleService.update(userVehicle);
    }

    public boolean delete(int id){
        log.info("delete {}",id);
        return userVehicleService.delete(id);
    }

    public UserVehicleFull getUSFull(int id){
        log.info("get {}",id);

        UserVehicle userVehicle = userVehicleService.get(id);
        User user = userService.get(userVehicle.getIdUser().getId());
        Vehicle vehicle = vehicleService.get(userVehicle.getIdVehicle().getId());
        UserVehicleFull userVehicleFull = UserVehiclesUtil.createUserVehicle(userVehicle,user,vehicle);

        return userVehicleFull;
    }

    public UserVehicle get(int id){
        log.info("get {}",id);
        return userVehicleService.get(id);
    }

    public List<UserVehicle> getAll(){
        log.info("getAll");
        return userVehicleService.getAll();
    }

    public List<UserVehicle> getAllByUser(int idUser){
        log.info("getAllByUser idUser {}",idUser);
        return userVehicleService.getAllByUser(idUser);
    }

    public List<UserVehicle> getAllByVehicle(int idVehicle){
        log.info("getAllByVehicle idVehicle {}",idVehicle);
        return userVehicleService.getAllByVehicle(idVehicle);
    }

    public List<UserVehicle> getByIsCurrentUserMachine(int isCurrentUserMachine){
        log.info("getByIsCurrentUserMachine isCurrentUserMachine {}",isCurrentUserMachine);
        return userVehicleService.getByIsCurrentUserMachine(isCurrentUserMachine);
    }

    public List<UserVehicle> getStartDateBetween(LocalDate startDate, LocalDate endDate){
        log.info("getStartDateBetween startDate {}, endDate {}",startDate, endDate);
        return userVehicleService.getStartDateBetween(startDate, endDate);
    }

    public List<UserVehicle> getEndDateBetween(LocalDate startDate, LocalDate endDate){
        log.info("getEndDateBetween startDate {}, endDate {}",startDate, endDate);
        return userVehicleService.getEndDateBetween(startDate, endDate);
    }

    public User getUser(int idUser){
        log.info("getUser {}",idUser);
        return userService.get(idUser);
    }

    public Vehicle getVehicle(int idVehicle){
        log.info("getVehicle {}",idVehicle);
        return vehicleService.get(idVehicle);
    }

    public List<User> getAllUsers(){
        log.info("getAllUsers");
        return userService.getAll();
    }

    public List<Vehicle> getAllVehicles(){
        log.info("getAllVehicles");
        return vehicleService.getAll();
    }

}
