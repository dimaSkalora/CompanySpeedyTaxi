package com.taxi.speedy.company.web.taxi_dispatcher_order;

import com.taxi.speedy.company.model.TaxiDispatcher;
import com.taxi.speedy.company.model.TaxiDispatcherOrder;
import com.taxi.speedy.company.service.TaxiDispatcherOrderService;
import com.taxi.speedy.company.service.TaxiDispatcherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

public abstract class AbstractTaxiDispatcherOrderController {
    private Logger log = LoggerFactory.getLogger(AbstractTaxiDispatcherOrderController.class);

    @Autowired
    private TaxiDispatcherOrderService taxiDispatcherOrderService;
    @Autowired
    private TaxiDispatcherService taxiDispatcherService;


    public TaxiDispatcherOrder create(TaxiDispatcherOrder taxiDispatcherOrder){
        if (!taxiDispatcherOrder.isNew())
            throw new IllegalArgumentException(taxiDispatcherOrder+" должен быть новым (id == null)");
        log.info("create taxiDispatcherOrder {}",taxiDispatcherOrder);
        return taxiDispatcherOrderService.create(taxiDispatcherOrder);
    }

    public TaxiDispatcherOrder update(TaxiDispatcherOrder taxiDispatcherOrder){
        if (taxiDispatcherOrder.isNew())
            throw new IllegalArgumentException(taxiDispatcherOrder+" должен быть не новым (id != null)");
        log.info("update {}",taxiDispatcherOrder);
        return taxiDispatcherOrderService.update(taxiDispatcherOrder);
    }

    public boolean delete(int id){
        log.info("delete id = {}",id);
        return taxiDispatcherOrderService.delete(id);
    }

    public TaxiDispatcherOrder get(int id){
        log.info("get id = {}",id);
        return taxiDispatcherOrderService.get(id);
    }

    public List<TaxiDispatcherOrder> getAll(){
        log.info("getAll");
        return taxiDispatcherOrderService.getAll();
    }

    public List<TaxiDispatcherOrder> getByIdTaxiDispatcher(int idTaxiDispatcher){
        log.info("getByIdTaxiDispatcher idTaxiDispatcher = {}",idTaxiDispatcher);
        return taxiDispatcherOrderService.getByIdTaxiDispatcher(idTaxiDispatcher);
    }

    public List<TaxiDispatcherOrder> getByAddressDeparture(String addressDeparture){
        log.info("getByAddressDeparture addressDeparture = {}",addressDeparture);
        return taxiDispatcherOrderService.getByAddressDeparture(addressDeparture);
    }

    public List<TaxiDispatcherOrder> getByAddressArrival(String addressArrival) {
        log.info("getByAddressArrival addressArrival = {}",addressArrival);
        return taxiDispatcherOrderService.getByAddressArrival(addressArrival);
    }

    public List<TaxiDispatcherOrder> getByUserName(String userName){
        log.info("getByUserName userName = {}",userName);
        return taxiDispatcherOrderService.getByUserName(userName);
    }

    public List<TaxiDispatcherOrder> getByUserPhone(String userPhone){
        log.info("getByUserPhone userPhone = {}",userPhone);
        return taxiDispatcherOrderService.getByUserPhone(userPhone);
    }

    public List<TaxiDispatcherOrder> getByFulfilled(int fulfilled){
        log.info("getByFulfilled fulfilled = {}",fulfilled);
        return taxiDispatcherOrderService.getByFulfilled(fulfilled);
    }

    public List<TaxiDispatcherOrder> getByBetweenStartDate(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        log.info("getByBetweenStartDate startDateTime = {}, endDateTime = {}",startDateTime,endDateTime);
        return taxiDispatcherOrderService.getByBetweenStartDate(startDateTime,endDateTime);
    }

    public List<TaxiDispatcherOrder> getByBetweenEndDate(LocalDateTime startDateTime, LocalDateTime endDateTime){
        log.info("getByBetweenEndDate startDateTime = {}, endDateTime = {}",startDateTime,endDateTime);
        return taxiDispatcherOrderService.getByBetweenEndDate(startDateTime,endDateTime);
    }

    public List<TaxiDispatcherOrder> getFilterTaxiDispatcherOrder(TaxiDispatcherOrder taxiDispatcherOrder){
        log.info("getFilterTaxiDispatcherOrder taxiDispatcherOrder = {}",taxiDispatcherOrder);
        return taxiDispatcherOrderService.getFilterTaxiDispatcherOrder(taxiDispatcherOrder);
    }

    public List<TaxiDispatcherOrder> getFilterTaxiDispatcherOrder(TaxiDispatcherOrder taxiDispatcherOrder,String sqlCondition){
        log.info("getFilterTaxiDispatcherOrder taxiDispatcherOrder = {}, taxiDispatcherOrder = {}",taxiDispatcherOrder,sqlCondition);
        return taxiDispatcherOrderService.getFilterTaxiDispatcherOrder(taxiDispatcherOrder,sqlCondition);
    }

    public TaxiDispatcher getTaxiDispatcher(int idTaxiDispatcher){
        log.info("getTaxiDispatcher idTaxiDispatcher = {}",idTaxiDispatcher);
        return taxiDispatcherService.get(idTaxiDispatcher);
    }

    public List<TaxiDispatcher> getAllTaxiDispatcher(){
        log.info("getFilterTaxiDispatcherOrder");
        return taxiDispatcherService.getAll();
    }


}
