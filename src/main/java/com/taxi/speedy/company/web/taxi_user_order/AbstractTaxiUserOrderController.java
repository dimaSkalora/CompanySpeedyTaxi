package com.taxi.speedy.company.web.taxi_user_order;

import com.taxi.speedy.company.model.Role;
import com.taxi.speedy.company.model.TaxiUserOrder;
import com.taxi.speedy.company.model.User;
import com.taxi.speedy.company.service.TaxiUserOrderService;
import com.taxi.speedy.company.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractTaxiUserOrderController {
    private Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private TaxiUserOrderService taxiUserOrderService;
    @Autowired
    private UserService userService;

    public TaxiUserOrder create(TaxiUserOrder taxiUserOrder){
        if (!taxiUserOrder.isNew())
            throw new IllegalArgumentException(taxiUserOrder+" должен быть новым (id == null)");
        log.info("create taxiUserOrder {}",taxiUserOrder);
        return taxiUserOrderService.create(taxiUserOrder);
    }

    public TaxiUserOrder update(TaxiUserOrder taxiUserOrder){
        if (taxiUserOrder.isNew())
            throw new IllegalArgumentException(taxiUserOrder+" должен быть не новым (id != null)");
        log.info("update taxiUserOrder {}"+taxiUserOrder);
        return taxiUserOrderService.update(taxiUserOrder);
    }

    public boolean delete(int id){
        log.info("delete id = {}", id);
        return taxiUserOrderService.delete(id);
    }

    public TaxiUserOrder get(int id){
        log.info("get id = {}",id);
        return taxiUserOrderService.get(id);
    }

    public List<TaxiUserOrder> getByIdUser(int idUser){
        log.info("getAllIdUser idUser = {}",idUser);
        return taxiUserOrderService.getAllIdUser(idUser);
    }

    public List<TaxiUserOrder> getAll(){
        log.info("getAll");
        return taxiUserOrderService.getAll();
    }

    public List<TaxiUserOrder> filterTaxiUserOrder(TaxiUserOrder taxiUserOrder){
        log.info("filterTaxiUserOrder taxiUserOrder = {}",taxiUserOrder);
        return taxiUserOrderService.filterTaxiUserOrder(taxiUserOrder);
    }

    public List<TaxiUserOrder> filterTaxiUserOrderSqlCondition(TaxiUserOrder taxiUserOrder, String sqlCondition){
        log.info("filterTaxiUserOrderSqlCondition taxiUserOrder = {}, sqlCondition = {}",taxiUserOrder,sqlCondition);
        return taxiUserOrderService.filterTaxiUserOrder(taxiUserOrder,sqlCondition);
    }

    public List<User> getAllUsers(){
        return userService.getAll();
    }

    public User getUser(int idUser){
        return userService.get(idUser);
    }
}
