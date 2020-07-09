package com.taxi.speedy.company.service.service_impl;

import com.taxi.speedy.company.model.Vehicle;
import com.taxi.speedy.company.repository.VehicleRepository;
import com.taxi.speedy.company.service.VehicleService;
import com.taxi.speedy.company.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import static com.taxi.speedy.company.util.ValidationUtil.checkNotFound;
import static com.taxi.speedy.company.util.ValidationUtil.checkNotFoundWithId;
import java.util.List;

@Service/*("vehicleServiceImpl")*/
public class VehicleServiceImpl implements VehicleService {

    private VehicleRepository vehicleRepository;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Vehicle create(Vehicle vehicle) {
        Assert.notNull(vehicle,"Не должно быть null");
        return vehicleRepository.save(vehicle);
    }

    @Override
    public void update(Vehicle vehicle) throws NotFoundException {
        Assert.notNull(vehicle,"Не должно быть null");
        checkNotFoundWithId(vehicleRepository.save(vehicle),vehicle.getId());
    }

    @Override
    public boolean delete(int id) throws NotFoundException {
        return checkNotFoundWithId(Boolean.valueOf(vehicleRepository.delete(id)), id);
    }

    @Override
    public Vehicle get(int id) throws NotFoundException {
        return checkNotFoundWithId(vehicleRepository.get(id), id);
    }

    @Override
    public List<Vehicle> getAll() {
        return vehicleRepository.getAll();
    }

    @Override
    public List<Vehicle> getByNameCar(String nameCar) throws NotFoundException {
        Assert.notNull(nameCar, "Не должно быть null");
        return checkNotFound(vehicleRepository.getByNameCar(nameCar), "nameCar = "+ nameCar);
    }

    @Override
    public List<Vehicle> getVehicleNumber(String vehicleNumber) throws NotFoundException {
        Assert.notNull(vehicleNumber, "Не должно быть null");
        return checkNotFound(vehicleRepository.getByVehicleNumber(vehicleNumber),"vehicleNumber = "+vehicleNumber);
    }
}
