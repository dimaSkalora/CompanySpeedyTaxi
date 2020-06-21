package com.taxi.speedy.company.repository.spring_jdbc;

import com.taxi.speedy.company.model.User;
import com.taxi.speedy.company.repository.UserRepository;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getByEmail(String email) {
        return null;
    }

    @Override
    public User getByPhone(String phone) {
        return null;
    }
}
