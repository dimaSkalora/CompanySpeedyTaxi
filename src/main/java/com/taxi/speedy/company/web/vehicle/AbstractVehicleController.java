package com.taxi.speedy.company.web.vehicle;

import com.taxi.speedy.company.model.Vehicle;
import com.taxi.speedy.company.service.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractVehicleController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private VehicleService vehicleService;

    public Vehicle create(Vehicle vehicle){
        log.info("vehicle {}", vehicle);
        if(!vehicle.isNew())
            throw new IllegalArgumentException(vehicle+" должен быть новым (id == null)");
        return vehicleService.create(vehicle);
    }

    public void update(Vehicle vehicle){
        log.info("vehicle {}", vehicle);
        if (vehicle.isNew())
            throw new IllegalArgumentException(vehicle+" должен быть не новым (id != null)");
        vehicleService.update(vehicle);
    }

    public boolean delete(int id){
        log.info("delete {}",id);
        return vehicleService.delete(id);
    }

    public Vehicle get(int id){
        log.info("get {}",id);
        return vehicleService.get(id);
    }

    public List<Vehicle> getAll(){
        log.info("getAll");
        return vehicleService.getAll();
    }

    public List<Vehicle> getByNameCar(String nameCar){
        log.info("getByNameCar {}",nameCar);
        return vehicleService.getByNameCar(nameCar);
    }

    public List<Vehicle> getByVehicleNumber(String vehicleNumber){
        log.info("getByVehicleNumber {}",vehicleNumber);
        return vehicleService.getVehicleNumber(vehicleNumber);
    }
}
