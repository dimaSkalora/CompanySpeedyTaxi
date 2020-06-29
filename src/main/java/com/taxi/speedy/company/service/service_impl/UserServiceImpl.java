package com.taxi.speedy.company.service.service_impl;

import com.taxi.speedy.company.model.User;
import com.taxi.speedy.company.repository.UserRepository;
import com.taxi.speedy.company.service.UserService;
import com.taxi.speedy.company.util.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import static com.taxi.speedy.company.util.ValidationUtil.checkNotFound;
import static com.taxi.speedy.company.util.ValidationUtil.checkNotFoundWithId;

import java.util.List;

@Service//("userServiceImpl")
public class UserServiceImpl implements UserService {

    //private static Logger logger = LoggerFactory.getLogger(NotFoundException.class);

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        //Убедитесь, что объект отсутствует null.
        Assert.notNull(user,"user must not be null");
        //logger.info(user.toString());
        return userRepository.save(user);
    }

    @Override
    public void update(User user) throws NotFoundException {
        //Убедитесь, что объект отсутствует null.
        Assert.notNull(user,"user must not be null");
        checkNotFoundWithId(userRepository.save(user),user.getId());
    }

    @Override
    public boolean delete(int id) throws NotFoundException {
        return checkNotFoundWithId(Boolean.valueOf(userRepository.delete(id)),id);
    }

    @Override
    public User get(int id) throws NotFoundException {
        return checkNotFoundWithId(userRepository.get(id),id);
    }

    @Override
    public User getByEmail(String email) throws NotFoundException {
        Assert.notNull(email,"email must not be null");
        return checkNotFound(userRepository.getByEmail(email),"email = "+email);
    }

    @Override
    public User getByPhone(String phone) throws NotFoundException {
        Assert.notNull(phone,"phone must not be null");
        return checkNotFound(userRepository.getByPhone(phone),"phone = "+phone);
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public User setUserEnable(int id, boolean enable) throws NotFoundException {
        User user = get(id);
        user.setEnabled(enable);
        return userRepository.save(user);
    }
}
