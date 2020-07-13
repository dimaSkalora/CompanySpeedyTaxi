package com.taxi.speedy.company.service.service_impl;

import com.taxi.speedy.company.model.UserVehicle;
import com.taxi.speedy.company.repository.UserVehicleRepository;
import com.taxi.speedy.company.service.UserVehicleService;
import com.taxi.speedy.company.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import static com.taxi.speedy.company.util.ValidationUtil.checkNotFound;
import static com.taxi.speedy.company.util.ValidationUtil.checkNotFoundWithId;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserVehicleServiceImpl implements UserVehicleService {

    private UserVehicleRepository userVehicleRepository;

    @Autowired
    public UserVehicleServiceImpl(UserVehicleRepository userVehicleRepository) {
        this.userVehicleRepository = userVehicleRepository;
    }

    @Override
    public UserVehicle create(UserVehicle userVehicle) {
        Assert.notNull(userVehicle,"не должно быть null");
        return userVehicleRepository.save(userVehicle);
    }

    @Override
    public void update(UserVehicle userVehicle) throws NotFoundException {
        Assert.notNull(userVehicle, "не должно быть null");
        checkNotFoundWithId(userVehicleRepository.save(userVehicle), userVehicle.getId());
    }

    @Override
    public boolean delete(int id) throws NotFoundException {
        return checkNotFoundWithId(Boolean.valueOf(userVehicleRepository.delete(id)),id);
    }

    @Override
    public UserVehicle get(int id) throws NotFoundException {
        return checkNotFoundWithId(userVehicleRepository.get(id),id);
    }

    @Override
    public List<UserVehicle> getAll() {
        return userVehicleRepository.getAll();
    }

    @Override
    public List<UserVehicle> getAllByUser(int idUser) throws NotFoundException {
        return checkNotFound(userVehicleRepository.getAllByUser(idUser),"idUser = "+idUser);
    }

    @Override
    public List<UserVehicle> getAllByVehicle(int idVehicle) throws NotFoundException {
        return checkNotFound(userVehicleRepository.getAllByVehicle(idVehicle),"idVehicle = "+idVehicle);
    }

    @Override
    public List<UserVehicle> getByIsCurrentUserMachine(int isCurrentUserMachine) throws NotFoundException {
        return checkNotFound(userVehicleRepository.getByIsCurrentUserMachine(isCurrentUserMachine),"isCurrentUserMachine = "+isCurrentUserMachine);
    }

    @Override
    public List<UserVehicle> getStartDateBetween(LocalDate startDate, LocalDate endDate) {
        Assert.notNull(startDate, "не должен быть null");
        Assert.notNull(endDate, "не должен быть null");
        return userVehicleRepository.getStartDateBetween(startDate,endDate);
    }

    @Override
    public List<UserVehicle> getEndDateBetween(LocalDate startDate, LocalDate endDate) {
        Assert.notNull(endDate, "не должен быть null");
        Assert.notNull(endDate, "не должен быть null");
        return userVehicleRepository.getEndDateBetween(startDate,endDate);
    }
}
