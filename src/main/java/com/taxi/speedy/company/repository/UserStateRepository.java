package com.taxi.speedy.company.repository;

import com.taxi.speedy.company.model.UserState;

import java.util.List;

public interface UserStateRepository {
    UserState save(UserState userState);
    boolean delete(int id);
    UserState get(int id);
    List<UserState> getAll();
    UserState getByNameUS(String nameUS);
}
