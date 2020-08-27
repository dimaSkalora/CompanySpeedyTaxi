package com.taxi.speedy.company.web.taxi_dispatcher;

import com.taxi.speedy.company.model.TaxiDispatcher;
import com.taxi.speedy.company.model.User;
import com.taxi.speedy.company.service.TaxiDispatcherService;
import com.taxi.speedy.company.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractTaxiDispatcherController {
    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private TaxiDispatcherService taxiDispatcherService;
    @Autowired
    private UserService userService;

    public TaxiDispatcher create(TaxiDispatcher taxiDispatcher){
        if (!taxiDispatcher.isNew())
            //Исключение недопустимого аргумента
            throw new IllegalArgumentException(taxiDispatcher+" должен быть новым (id == null)");
        log.info("create {}",taxiDispatcher);
        return taxiDispatcherService.create(taxiDispatcher);
    }

    public TaxiDispatcher update(TaxiDispatcher taxiDispatcher){
        if (taxiDispatcher.isNew())
            //Исключение недопустимого аргумента
            throw new IllegalArgumentException(taxiDispatcher+" должен быть не новым (id != null)");
        log.info("update {}",taxiDispatcher);
        return taxiDispatcherService.update(taxiDispatcher);
    }

    public TaxiDispatcher get(int id){
        log.info("get {}",id);
        return taxiDispatcherService.get(id);
    }

    public boolean delete(int id){
        log.info("delete {}",id);
        return taxiDispatcherService.delete(id);
    }

    public List<TaxiDispatcher> getAll(){
        log.info("getAll");
        return taxiDispatcherService.getAll();
    }

    public User getUser(int idUser){
        log.info("getUser {}",idUser);
        return userService.get(idUser);
    }

    public List<User> getAllUser(){
        log.info("getAllUser");
        return userService.getAll();
    }
}
