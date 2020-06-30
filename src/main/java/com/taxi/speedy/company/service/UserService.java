package com.taxi.speedy.company.service;

import com.taxi.speedy.company.model.User;
import com.taxi.speedy.company.util.exception.NotFoundException;

import java.util.List;

public interface UserService {
    User create(User user);
    void update (User user) throws NotFoundException;
    boolean delete (int id) throws NotFoundException;
    User get(int id) throws NotFoundException;
    User getByEmail(String email) throws NotFoundException;
    User getByPhone(String phone) throws NotFoundException;
    List<User> getAll();
    User setUserEnable(int id, boolean enable) throws NotFoundException;
}
