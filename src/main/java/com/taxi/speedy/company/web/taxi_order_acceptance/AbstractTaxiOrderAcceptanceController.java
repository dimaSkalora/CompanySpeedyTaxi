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
    private TaxiOrderAcceptanceService taxiOrderAcceptanceService;
    @Autowired
    private UserVehicleService userVehicleService;
    @Autowired
    private TaxiDispatcherOrderService taxiDispatcherOrderService;
    @Autowired
    private TaxiUserOrderService taxiUserOrderService;

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
        UserVehicle userVehicle = null;
        if (idUserVehicle>0)
            userVehicle = userVehicleService.get(idUserVehicle);

        return userVehicle;
    }

    public List<UserVehicle> getAllUserVehicles(){
        return userVehicleService.getAll();
    }

    public TaxiDispatcherOrder getTaxiDispatcherOrder(int idTaxiDispatcherOrder){
        TaxiDispatcherOrder taxiDispatcherOrder = null;
        if (idTaxiDispatcherOrder > 0)
            taxiDispatcherOrder = taxiDispatcherOrderService.get(idTaxiDispatcherOrder);

        return taxiDispatcherOrder;
    }

    public List<TaxiDispatcherOrder> getAllTaxiDispatcherOrders(){
        return taxiDispatcherOrderService.getAll();
    }

    public TaxiUserOrder getTaxiUserOrder(int idTaxiUserOrder){
        TaxiUserOrder taxiUserOrder = null;
        if (idTaxiUserOrder > 0)
            taxiUserOrder = taxiUserOrderService.get(idTaxiUserOrder);

        return taxiUserOrder;
    }

    public List<TaxiUserOrder> getAllTaxiUserOrders(){
        return taxiUserOrderService.getAll();
    }

}
