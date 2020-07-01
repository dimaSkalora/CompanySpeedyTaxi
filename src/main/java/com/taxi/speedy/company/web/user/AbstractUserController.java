package com.taxi.speedy.company.web.user;

import com.taxi.speedy.company.model.Role;
import com.taxi.speedy.company.model.User;
import com.taxi.speedy.company.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class AbstractUserController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    UserService userService;

    public User create(User user){
        log.info("create {}",user);
        if (!user.isNew())
            throw new IllegalArgumentException(user+" must be new (id==null)");
        //Добавляем роль для юзера по умолчанию
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ROLE_USER);
        return userService.create(user);
    }

    public void update(User user, int id){
        log.info("update {}, with id {}",user,id);
        if(!user.isNew()){
            user.setId(id);
            if(user.getRoles() == null){
                Set<Role> roles = new HashSet<>();
                roles.add(Role.ROLE_USER);
                user.setRoles(roles);
            }
        }else if(user.getId() != id)
            throw new IllegalArgumentException(user+"must be with id = "+id);
        userService.update(user);
    }

    public boolean delete(int id){
        log.info("delete {}",id);
        return userService.delete(id);
    }

    public User get(int id){
        log.info("user {}",id);
        return userService.get(id);
    }

    public List<User> getAll(){
        log.info("getAll");
        return userService.getAll();
    }

    public User getByEmail(String email){
        log.info("email {}",email);
        return userService.getByEmail(email);
    }

    public User getByPhone(String phone){
        log.info("phone {}",phone);
        return userService.getByPhone(phone);
    }

    public void setUserEnable(int id, boolean enabled){
        log.info((enabled ? "enable " : "disable ") + id);
        userService.setUserEnable(id,enabled);
    }

}
