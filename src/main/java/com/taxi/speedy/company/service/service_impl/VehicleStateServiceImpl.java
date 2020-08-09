package com.taxi.speedy.company.service.service_impl;

import com.taxi.speedy.company.model.VehicleState;
import com.taxi.speedy.company.repository.VehicleStateRepository;
import com.taxi.speedy.company.service.VehicleStateService;
import com.taxi.speedy.company.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static com.taxi.speedy.company.util.ValidationUtil.checkNotFound;
import static com.taxi.speedy.company.util.ValidationUtil.checkNotFoundWithId;

@Service//("VehicleStateServiceImpl")
public class VehicleStateServiceImpl implements VehicleStateService {

    @Autowired
    private VehicleStateRepository vehicleStateRepository;

 /*   @Autowired
    public VehicleStateServiceImpl(VehicleStateRepository vehicleStateRepository) {
        this.vehicleStateRepository = vehicleStateRepository;
    }*/

    @Override
    public VehicleState create(VehicleState vehicleState) {
        Assert.notNull(vehicleState,"Не должно быть null");
        return vehicleStateRepository.save(vehicleState);
    }

    @Override
    public VehicleState update(VehicleState vehicleState) throws NotFoundException {
        Assert.notNull(vehicleState,"Не должно быть null");
        return vehicleStateRepository.save(vehicleState);
    }

    @Override
    public VehicleState get(int id) throws NotFoundException {
        return checkNotFoundWithId(vehicleStateRepository.get(id),id);
    }

    @Override
    public boolean delete(int id) throws NotFoundException {
        return checkNotFoundWithId(Boolean.valueOf(vehicleStateRepository.delete(id)),id);
    }

    @Override
    public List<VehicleState> getAll() {
        return vehicleStateRepository.getAll();
    }

    @Override
    public VehicleState getByNameVS(String nameVS) throws NotFoundException {
        Assert.notNull(nameVS,"nameVS - Не должно быть null");
        return checkNotFound(vehicleStateRepository.getByNameVS(nameVS),"nameVS - "+nameVS);
    }
}
