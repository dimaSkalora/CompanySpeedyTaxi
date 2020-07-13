package com.taxi.speedy.company.web.user;

import com.taxi.speedy.company.model.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(AdminRestUserController.REST_URL)
public class AdminRestUserController extends AbstractUserController{

    static final String REST_URL = "/rest/admin/users";

    @Override
    public User create(User user) {
        return super.create(user);
    }

    @Override
    public void update(User user, int id) {
        super.update(user, id);
    }

    @Override
    public boolean delete(int id) {
        return super.delete(id);
    }

    @Override
    public User get(int id) {
        return super.get(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    //produces - Воспроизводимые типы носителей запрошенного запроса, сужающие первичное отображение.
    @Override
    public List<User> getAll() {
        return super.getAll();
    }

    @Override
    public User getByEmail(String email) {
        return super.getByEmail(email);
    }

    @Override
    public User getByPhone(String phone) {
        return super.getByPhone(phone);
    }

    @Override
    public void setUserEnable(int id, boolean enabled) {
        super.setUserEnable(id, enabled);
    }
}
