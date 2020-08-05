package com.taxi.speedy.company.service;

import com.taxi.speedy.company.model.UserState;
import com.taxi.speedy.company.util.exception.NotFoundException;

import java.util.List;

public interface UserStateService {
    UserState create(UserState userState);
    UserState update (UserState userState) throws NotFoundException;
    boolean delete(int id) throws NotFoundException;
    UserState get(int id) throws NotFoundException;
    List<UserState> getAll();
    UserState getByNameUS(String nameUS)throws NotFoundException;
}
