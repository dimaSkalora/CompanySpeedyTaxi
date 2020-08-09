package com.taxi.speedy.company.web.vehicle_state;

import com.taxi.speedy.company.model.VehicleState;
import com.taxi.speedy.company.service.VehicleStateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractVehicleStateController {
    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private VehicleStateService vehicleStateService;

    public VehicleState create(VehicleState vehicleState){
        log.info("create {}", vehicleState);
        if (!vehicleState.isNew())
            throw new IllegalArgumentException(vehicleState+" должен быть новым (id == null)");

        return vehicleStateService.create(vehicleState);
    }

    public VehicleState update(VehicleState vehicleState){
        log.info("update {}",vehicleState);
        if (vehicleState.isNew())
            throw new IllegalArgumentException(vehicleState+" должен быть не новым (id != null)");
        return vehicleStateService.update(vehicleState);
    }

    public VehicleState get(int id){
        log.info("get {}", id);
        return vehicleStateService.get(id);
    }

    public boolean delete(int id){
        log.info("delete {}",id);
        return vehicleStateService.delete(id);
    }

    public List<VehicleState> getAll(){
        log.info("getAll");
        return vehicleStateService.getAll();
    }

    public VehicleState getByNameVS(String nameVS){
        log.info("getByNameVS {}",nameVS);
        return vehicleStateService.getByNameVS(nameVS);
    }


}
