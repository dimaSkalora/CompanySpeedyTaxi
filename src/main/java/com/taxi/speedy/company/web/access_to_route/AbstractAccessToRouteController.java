package com.taxi.speedy.company.web.access_to_route;

import com.taxi.speedy.company.model.AccessToRoute;
import com.taxi.speedy.company.model.UserState;
import com.taxi.speedy.company.model.UserVehicle;
import com.taxi.speedy.company.model.VehicleState;
import com.taxi.speedy.company.service.AccessToRouteService;
import com.taxi.speedy.company.service.UserStateService;
import com.taxi.speedy.company.service.UserVehicleService;
import com.taxi.speedy.company.service.VehicleStateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

public abstract class AbstractAccessToRouteController {
    private Logger log = LoggerFactory.getLogger(AbstractAccessToRouteController.class);

    @Autowired
    private AccessToRouteService accessToRouteService;
    @Autowired
    private UserVehicleService userVehicleService;
    @Autowired
    private UserStateService userStateService;
    @Autowired
    private VehicleStateService vehicleStateService;

    public AccessToRoute create(AccessToRoute accessToRoute){
        log.info("create {}",accessToRoute);

        if(!accessToRoute.isNew())
            throw new IllegalArgumentException(accessToRoute+" должен быть новым (id == null)");
        return accessToRouteService.create(accessToRoute);
    }

    public AccessToRoute update(AccessToRoute accessToRoute){
        log.info("update {}",accessToRoute);
        if(accessToRoute.isNew())
            throw new IllegalArgumentException(accessToRoute+" должен быть новым (id == null)");

        return accessToRouteService.update(accessToRoute);
    }

    public boolean delete(int id){
        log.info("delete {}",id);
        return accessToRouteService.delete(id);
    }

    public AccessToRoute get(int id){
        log.info("get {}", id);
        return accessToRouteService.get(id);
    }

    public List<AccessToRoute> getAll(){
        log.info("getAll");
        return accessToRouteService.getAll();
    }

    public List<AccessToRoute> getByChecksDateTime(LocalDate startDate, LocalDate endDate){
        log.info("getByChecksDateTime {}, {}",startDate,endDate);
        return accessToRouteService.getByChecksDateTime(startDate,endDate);
    }

    public List<AccessToRoute> getByIdUserVehicles(int idUserVehicle){
        log.info("getByIdUserVehicles {}",idUserVehicle);
        return accessToRouteService.getByUserVehicle(idUserVehicle);
    }

    public List<AccessToRoute> getByIdUserStates(int idUserState){
        log.info("getByIdUserStates {}",idUserState);
        return accessToRouteService.getByUserState(idUserState);
    }

    public List<AccessToRoute> getByIdVehicleStates(int idVehicleState){
        log.info("getByIdVehicleStates {}",idVehicleState);
        return accessToRouteService.getByVehicleState(idVehicleState);
    }

    public List<AccessToRoute> getByIsAccess(int isAccess){
        log.info("getByIsAccess {}",isAccess);
        return accessToRouteService.getByIsAccess(isAccess);
    }

    public UserVehicle getUserVehicle(int idUserVehicle){
        log.info("getUserVehicle {}",idUserVehicle);
        return userVehicleService.get(idUserVehicle);
    }

    public UserState getUserState(int idUserState){
        log.info("getUserState {}",idUserState);
        return userStateService.get(idUserState);
    }

    public VehicleState getVehicleState(int idVehicleState){
        log.info("getVehicleState {}",idVehicleState);
        return vehicleStateService.get(idVehicleState);
    }

    public List<UserVehicle> getAllUserVehicles(){
        log.info("getAllUserVehicles");
        return userVehicleService.getAll();
    }

    public List<UserState> getAllUserStates(){
        log.info("getAllUserStates");
        return userStateService.getAll();
    }

    public List<VehicleState> getAllVehicleStates(){
        log.info("getAllVehicleStates");
        return vehicleStateService.getAll();
    }



}
