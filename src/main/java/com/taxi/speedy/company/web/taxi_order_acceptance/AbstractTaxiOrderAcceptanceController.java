package com.taxi.speedy.company.web.taxi_order_acceptance;

import com.taxi.speedy.company.model.TaxiDispatcherOrder;
import com.taxi.speedy.company.model.TaxiOrderAcceptance;
import com.taxi.speedy.company.model.TaxiUserOrder;
import com.taxi.speedy.company.model.UserVehicle;
import com.taxi.speedy.company.service.TaxiDispatcherOrderService;
import com.taxi.speedy.company.service.TaxiOrderAcceptanceService;
import com.taxi.speedy.company.service.TaxiUserOrderService;
import com.taxi.speedy.company.service.UserVehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.taxi.speedy.company.util.ValidationUtil.checkNew;
import static com.taxi.speedy.company.util.ValidationUtil.checkNotNew;

public abstract class AbstractTaxiOrderAcceptanceController {
    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    TaxiOrderAcceptanceService taxiOrderAcceptanceService;
    @Autowired
    UserVehicleService userVehicleService;
    @Autowired
    TaxiDispatcherOrderService taxiDispatcherOrderService;
    @Autowired
    TaxiUserOrderService taxiUserOrderService;

    public TaxiOrderAcceptance create(TaxiOrderAcceptance taxiOrderAcceptance){
        checkNew(taxiOrderAcceptance);
        log.info("create {}",taxiOrderAcceptance);

        return taxiOrderAcceptanceService.create(taxiOrderAcceptance);
    }

    public void update(TaxiOrderAcceptance taxiOrderAcceptance){
        checkNotNew(taxiOrderAcceptance);

        log.info("update {}", taxiOrderAcceptance);
        taxiOrderAcceptanceService.update(taxiOrderAcceptance);
    }

    public boolean delete(int id){
        log.info("delete id ={}",id);
        return taxiOrderAcceptanceService.delete(id);
    }

    public TaxiOrderAcceptance get(int id){
        log.info("get id = {}",id);
        return taxiOrderAcceptanceService.get(id);
    }

    public List<TaxiOrderAcceptance> getAll(){
        log.info("getAll");
        return taxiOrderAcceptanceService.getAll();
    }

    public List<TaxiOrderAcceptance> filterTaxiUserOrder(TaxiOrderAcceptance taxiOrderAcceptance){
        log.info("filterTaxiUserOrder {}",taxiOrderAcceptance);
        return taxiOrderAcceptanceService.filterTaxiUserOrder(taxiOrderAcceptance);
    }

    public  List<TaxiOrderAcceptance> filterTaxiUserOrderSqlCondition(TaxiOrderAcceptance taxiOrderAcceptance,String sqlCondition){
        log.info("filterTaxiUserOrderSqlCondition taxiOrderAcceptance {}, sqlCondition {}",taxiOrderAcceptance,sqlCondition);
        return taxiOrderAcceptanceService.filterTaxiUserOrder(taxiOrderAcceptance,sqlCondition);
    }

    public UserVehicle getUserVehicle(int idUserVehicle){
        return userVehicleService.get(idUserVehicle);
    }

    public List<UserVehicle> getAllUserVehicles(){
        return userVehicleService.getAll();
    }

    public TaxiDispatcherOrder getTaxiDispatcherOrder(int idTaxiDispatcherOrder){
        return taxiDispatcherOrderService.get(idTaxiDispatcherOrder);
    }

    public List<TaxiDispatcherOrder> getAllTaxiDispatcherOrders(){
        return taxiDispatcherOrderService.getAll();
    }

    public TaxiUserOrder getTaxiUserOrder(int idTaxiUserOrder){
        return taxiUserOrderService.get(idTaxiUserOrder);
    }

    public List<TaxiUserOrder> getAllTaxiUserOrders(){
        return taxiUserOrderService.getAll();
    }

}
