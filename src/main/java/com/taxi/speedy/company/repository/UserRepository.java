package com.taxi.speedy.company.repository;

import com.taxi.speedy.company.model.User;

import java.util.List;

public interface UserRepository {
    User save(User user);
    // false if not found
    boolean delete(int id);
    // null if not found
    User get(int id);
    List<User> getAll();
    // null if not found
    User getByEmail(String email);          //получить юзера по email
    User getByPhone(String phone);          //получить юзера по номеру телефону
}
