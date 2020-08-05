package com.taxi.speedy.company.service.service_impl;

import com.taxi.speedy.company.model.UserState;
import com.taxi.speedy.company.repository.UserStateRepository;
import com.taxi.speedy.company.service.UserStateService;
import com.taxi.speedy.company.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static com.taxi.speedy.company.util.ValidationUtil.checkNotFound;
import static com.taxi.speedy.company.util.ValidationUtil.checkNotFoundWithId;

@Service
public class UserStateServiceImpl implements UserStateService {

    UserStateRepository userStateRepository;

    @Autowired
    public UserStateServiceImpl(UserStateRepository userStateRepository) {
        this.userStateRepository = userStateRepository;
    }

    @Override
    public UserState create(UserState userState) {
        Assert.notNull(userState,"Не должно быть null");
        return userStateRepository.save(userState);
    }

    @Override
    public UserState update(UserState userState) throws NotFoundException {
        Assert.notNull(userState,"Не должно быть null");
        return userStateRepository.save(userState);
    }

    @Override
    public boolean delete(int id) throws NotFoundException {
        return checkNotFoundWithId(Boolean.valueOf(userStateRepository.delete(id)),id);
    }

    @Override
    public UserState get(int id) throws NotFoundException {
        return checkNotFoundWithId(userStateRepository.get(id),id);
    }

    @Override
    public List<UserState> getAll() {
        return userStateRepository.getAll();
    }

    @Override
    public UserState getByNameUS(String nameUS) throws NotFoundException {
        Assert.notNull(nameUS,"nameUS - Не должно быть null");
        return checkNotFound(userStateRepository.getByNameUS(nameUS),"nameUS = "+nameUS);
    }
}
