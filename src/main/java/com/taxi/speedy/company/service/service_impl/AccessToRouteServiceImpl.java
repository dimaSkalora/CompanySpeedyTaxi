package com.taxi.speedy.company.service.service_impl;

import com.taxi.speedy.company.model.AccessToRoute;
import com.taxi.speedy.company.repository.AccessToRouteRepository;
import com.taxi.speedy.company.service.AccessToRouteService;
import com.taxi.speedy.company.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

import static com.taxi.speedy.company.util.ValidationUtil.checkNotFound;
import static com.taxi.speedy.company.util.ValidationUtil.checkNotFoundWithId;

@Service//("accessToRouteServiceImpl")
public class AccessToRouteServiceImpl implements AccessToRouteService {

    private AccessToRouteRepository accessToRouteRepository;

    @Autowired
    public AccessToRouteServiceImpl(AccessToRouteRepository accessToRouteRepository) {
        this.accessToRouteRepository = accessToRouteRepository;
    }

    @Override
    public AccessToRoute create(AccessToRoute accessToRoute) {
        Assert.notNull(accessToRoute,"не должно быть null");
        return accessToRouteRepository.save(accessToRoute);
    }

    @Override
    public AccessToRoute update(AccessToRoute accessToRoute) throws NotFoundException {
        Assert.notNull(accessToRoute,"не должно быть null");
        //Проверка - не найден с идентификатором
        return checkNotFoundWithId(accessToRouteRepository.save(accessToRoute),accessToRoute.getId());
    }

    @Override
    public AccessToRoute get(int id) throws NotFoundException {
        return checkNotFoundWithId(accessToRouteRepository.get(id),id);
    }

    @Override
    public boolean delete(int id) throws NotFoundException {
        return checkNotFoundWithId(Boolean.valueOf(accessToRouteRepository.delete(id)),id);
    }

    @Override
    public List<AccessToRoute> getAll() {
        return accessToRouteRepository.getAll();
    }

    @Override
    public List<AccessToRoute> getByChecksDateTime(LocalDate startDate, LocalDate endDate){
        Assert.notNull(startDate, "не должен быть null");
        Assert.notNull(endDate, "не должен быть null");

        return accessToRouteRepository.getByChecksDateTime(startDate,endDate);
    }

    @Override
    public List<AccessToRoute> getByUserVehicle(int idUserVehicle) throws NotFoundException {
        //Проверка - не найден
        return checkNotFound(accessToRouteRepository.getByUserVehicle(idUserVehicle),"idUserVehicle = "+idUserVehicle);
    }

    @Override
    public List<AccessToRoute> getByUserState(int idUserState) throws NotFoundException {
        //Проверка - не найден
        return checkNotFound(accessToRouteRepository.getByUserState(idUserState),"idUserState = "+idUserState);
    }

    @Override
    public List<AccessToRoute> getByVehicleState(int idVehicleState) throws NotFoundException {
        //Проверка - не найден
        return checkNotFound(accessToRouteRepository.getByVehicleState(idVehicleState),"idVehicleState = "+idVehicleState);
    }

    @Override
    public List<AccessToRoute> getByIsAccess(int isAccess) throws NotFoundException {
        //Проверка - не найден
        return checkNotFound(accessToRouteRepository.getByIsAccess(isAccess),"isAccess = "+isAccess);
    }
}
