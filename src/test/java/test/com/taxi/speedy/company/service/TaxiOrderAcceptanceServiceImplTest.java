package test.com.taxi.speedy.company.service;

import com.taxi.speedy.company.model.TaxiDispatcherOrder;
import com.taxi.speedy.company.model.TaxiOrderAcceptance;
import com.taxi.speedy.company.model.UserVehicle;
import com.taxi.speedy.company.service.TaxiDispatcherOrderService;
import com.taxi.speedy.company.service.TaxiOrderAcceptanceService;
import com.taxi.speedy.company.service.UserVehicleService;
import com.taxi.speedy.company.service.service_impl.TaxiDispatcherOrderServiceImpl;
import com.taxi.speedy.company.service.service_impl.UserVehicleServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TaxiOrderAcceptanceServiceImplTest {
    private Logger log = LoggerFactory.getLogger(getClass());

    private ApplicationContext applicationContext;
    TaxiOrderAcceptanceService taxiOrderAcceptanceService;
    UserVehicleService userVehicleService;
    TaxiDispatcherOrderService taxiDispatcherOrderService;

    TaxiOrderAcceptance taxiOrderAcceptance;
    int idTaxiOrderAcceptance;

    @Before
    public void setUp(){
        applicationContext = new ClassPathXmlApplicationContext("spring/context.xml");
        taxiOrderAcceptanceService = (TaxiOrderAcceptanceService) applicationContext.getBean("taxiOrderAcceptanceServiceImpl");
        userVehicleService = applicationContext.getBean(UserVehicleServiceImpl.class);
        taxiDispatcherOrderService = applicationContext.getBean(TaxiDispatcherOrderServiceImpl.class);
      }

    @Test
    public void create(){
        UserVehicle userVehicle = userVehicleService.get(1);
        TaxiDispatcherOrder taxiDispatcherOrder = taxiDispatcherOrderService.get(1);

        TaxiOrderAcceptance taxiOrderAcceptance = new TaxiOrderAcceptance();
        taxiOrderAcceptance.setIdUserVehicle(userVehicle);
        taxiOrderAcceptance.setIdTaxiDispatcherOrder(taxiDispatcherOrder);
        taxiOrderAcceptance.setExecutionStatus(1);    //Статус выполнение: 1-выполнено, 0- не выполнено
        taxiOrderAcceptance.setAdoptionStatus(1);    //Статус принятие: 1-принятый заказ, 0- не принятый заказ

        log.info("create {}",taxiOrderAcceptance);

        taxiOrderAcceptanceService.create(taxiOrderAcceptance);

        idTaxiOrderAcceptance=taxiOrderAcceptance.getId();
    }

    @Test
    public void createNot(){
        UserVehicle userVehicle = userVehicleService.get(1);
        TaxiDispatcherOrder taxiDispatcherOrder = taxiDispatcherOrderService.get(0);

        TaxiOrderAcceptance taxiOrderAcceptance = new TaxiOrderAcceptance();
        taxiOrderAcceptance.setIdUserVehicle(userVehicle);
        taxiOrderAcceptance.setIdTaxiDispatcherOrder(taxiDispatcherOrder);

        log.info("createNot {}",taxiOrderAcceptance);

        taxiOrderAcceptanceService.create(taxiOrderAcceptance);

        idTaxiOrderAcceptance=taxiOrderAcceptance.getId();
    }

    @Test
    public void update(){
        create();

        taxiOrderAcceptance = taxiOrderAcceptanceService.get(idTaxiOrderAcceptance);
        TaxiDispatcherOrder taxiDispatcherOrder = taxiDispatcherOrderService.get(3);
        taxiOrderAcceptance.setIdTaxiDispatcherOrder(taxiDispatcherOrder);

        taxiOrderAcceptanceService.update(taxiOrderAcceptance);
        log.info("update {}",taxiOrderAcceptance);
    }

    @Test
    public void updateNot(){
        create();

        taxiOrderAcceptance = taxiOrderAcceptanceService.get(idTaxiOrderAcceptance);
        TaxiDispatcherOrder taxiDispatcherOrder = taxiDispatcherOrderService.get(0);
        taxiOrderAcceptance.setIdUserVehicle(null);
        taxiOrderAcceptance.setIdTaxiDispatcherOrder(taxiDispatcherOrder);
        taxiOrderAcceptance.setExecutionStatus(null);

        taxiOrderAcceptanceService.update(taxiOrderAcceptance);

        log.info("updateNot {}",taxiOrderAcceptance);
    }

    @Test
    public void get(){
        create();

        taxiOrderAcceptance = taxiOrderAcceptanceService.get(idTaxiOrderAcceptance);

        log.info("get {}",taxiOrderAcceptance);
    }

    @Test
    public void getNot(){
        create();

        taxiOrderAcceptance = taxiOrderAcceptanceService.get(1111111);

        log.info("getNot {}",taxiOrderAcceptance);
    }

    @Test
    public void delete(){
        create();

        taxiOrderAcceptance = taxiOrderAcceptanceService.get(idTaxiOrderAcceptance);

        log.info("delete {}",taxiOrderAcceptanceService.delete(taxiOrderAcceptance.getId()));
    }

    @Test
    public void deleteNot(){
        log.info("deleteNot {}",taxiOrderAcceptanceService.delete(0));
    }

    @Test
    public void getAll(){
        log.info("getAll");
        taxiOrderAcceptanceService.getAll();
    }

    @Test
    public void filterTaxiUserOrder(){
        taxiOrderAcceptance = new TaxiOrderAcceptance();
        taxiOrderAcceptance.setAdoptionStatus(0);

        List<TaxiOrderAcceptance> list = taxiOrderAcceptanceService.filterTaxiUserOrder(taxiOrderAcceptance);
        list.forEach(toa -> log.info("toa {}",toa));
    }

    @Test
    public void filterTaxiUserOrderNot(){
        taxiOrderAcceptance = null;

        List<TaxiOrderAcceptance> list = taxiOrderAcceptanceService.filterTaxiUserOrder(taxiOrderAcceptance);
        list.forEach(toa -> log.info("toa {}",toa));
    }

    @Test
    public void filterTaxiUserOrderSqlCondition(){
        taxiOrderAcceptance = new TaxiOrderAcceptance();

        String sqlCondition = " toa.adoption_status=1";

        List<TaxiOrderAcceptance> list = taxiOrderAcceptanceService.filterTaxiUserOrder(taxiOrderAcceptance,sqlCondition);
        list.forEach(toa -> log.info("toa {}",toa));
    }

    @Test
    public void filterTaxiUserOrderSqlConditionNot(){
        taxiOrderAcceptance =null;

        String sqlCondition = "";

        List<TaxiOrderAcceptance> list = taxiOrderAcceptanceService.filterTaxiUserOrder(taxiOrderAcceptance,sqlCondition);
        list.forEach(toa -> log.info("toa {}",toa));
    }
}
