package com.taxi.speedy.company.web.user_state;

import com.taxi.speedy.company.model.UserState;
import com.taxi.speedy.company.service.UserStateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractUserStateController {
    private Logger log = LoggerFactory.getLogger(AbstractUserStateController.class);

    @Autowired
    private UserStateService userStateService;

    public UserState create(UserState userState){
        log.info("create {}", userState);
        if(!userState.isNew())
            throw new IllegalArgumentException(userState+" должен быть новым (id == null)");

        return userStateService.create(userState);
    }

    public UserState update(UserState userState){
        log.info("update {}",userState);
        if(userState.isNew())
            throw new IllegalArgumentException(userState+" должен быть не новым (id != null)");

        return userStateService.update(userState);
    }

    public UserState get(int id){
        log.info("get {}", id);
        return userStateService.get(id);
    }

    public boolean delete(int id){
        log.info("delete {}",id);
        return userStateService.delete(id);
    }

    public List<UserState> getAll(){
        log.info("getAll");
        return userStateService.getAll();
    }

    public UserState getByNameUS(String nameUS){
        log.info("nameUS {}",nameUS);
        return userStateService.getByNameUS(nameUS);
    }
}
