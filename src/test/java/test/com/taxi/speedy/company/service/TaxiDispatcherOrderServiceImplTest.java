package test.com.taxi.speedy.company.service;

import com.taxi.speedy.company.model.TaxiDispatcher;
import com.taxi.speedy.company.model.TaxiDispatcherOrder;
import com.taxi.speedy.company.service.TaxiDispatcherOrderService;
import com.taxi.speedy.company.service.TaxiDispatcherService;
import com.taxi.speedy.company.service.service_impl.TaxiDispatcherServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDateTime;

public class TaxiDispatcherOrderServiceImplTest {
    private Logger log = LoggerFactory.getLogger(TaxiDispatcherOrderServiceImplTest.class);

    private ApplicationContext applicationContext;
    private TaxiDispatcherOrderService taxiDispatcherOrderService;
    private TaxiDispatcherService taxiDispatcherService;

    private TaxiDispatcherOrder taxiDispatcherOrder;
    private int taxiDispatcherOrderId;

    @Before
    public void setUp(){
        applicationContext = new ClassPathXmlApplicationContext("/spring/context.xml");
        taxiDispatcherOrderService = (TaxiDispatcherOrderService) applicationContext.getBean("taxiDispatcherOrderServiceImpl");
        taxiDispatcherService = applicationContext.getBean(TaxiDispatcherServiceImpl.class);
    }

    @Test
    public void create(){
        TaxiDispatcher taxiDispatcher = taxiDispatcherService.get(1);
        log.info("taxiDispatcher {}",taxiDispatcher);

        TaxiDispatcherOrder taxiDispatcherOrder = new TaxiDispatcherOrder();
        taxiDispatcherOrder.setDateTimeOrder(LocalDateTime.now());
        taxiDispatcherOrder.setIdTaxiDispatcher(taxiDispatcher);
        taxiDispatcherOrder.setUserName("User test");
        taxiDispatcherOrder.setUserPhone("+380151165");
        taxiDispatcherOrder.setAddressDeparture("address departure test");
        taxiDispatcherOrder.setAddressArrival("address arrival test");
        taxiDispatcherOrder.setStartDate(LocalDateTime.now().plusHours(1));
        taxiDispatcherOrder.setEndDate(LocalDateTime.now().plusHours(1).plusMinutes(30));
        taxiDispatcherOrder.setFulfilled(1);                //Выполнен заказ: 1 - да, 0 - нет

        taxiDispatcherOrderService.create(taxiDispatcherOrder);
        log.info("create {}",taxiDispatcherOrder);

        taxiDispatcherOrderId = taxiDispatcherOrder.getId();
    }

    @Test
    public void createNot(){
        TaxiDispatcher taxiDispatcher = taxiDispatcherService.get(1);
        log.info("taxiDispatcher {}",taxiDispatcher);

        TaxiDispatcherOrder taxiDispatcherOrder = new TaxiDispatcherOrder();
        taxiDispatcherOrder.setDateTimeOrder(LocalDateTime.now());
        taxiDispatcherOrder.setIdTaxiDispatcher(null);
        taxiDispatcherOrder.setUserName("User test");
        taxiDispatcherOrder.setUserPhone("+380151165");
        taxiDispatcherOrder.setAddressDeparture("address departure test");
        taxiDispatcherOrder.setAddressArrival("address arrival test");
        taxiDispatcherOrder.setStartDate(LocalDateTime.now().plusHours(1));
        taxiDispatcherOrder.setEndDate(LocalDateTime.now().plusHours(1).plusMinutes(30));
        taxiDispatcherOrder.setFulfilled(1);                //Выполнен заказ: 1 - да, 0 - нет

        taxiDispatcherOrderService.create(taxiDispatcherOrder);
        log.info("taxiDispatcherOrder {}",taxiDispatcherOrder);

        taxiDispatcherOrderId = taxiDispatcherOrder.getId();
    }

    @Test
    public void update(){
        create();
        taxiDispatcherOrder = taxiDispatcherOrderService.get(taxiDispatcherOrderId);
        log.info("taxiDispatcherOrder {}", taxiDispatcherOrder);

        taxiDispatcherOrder.setUserName("");
        taxiDispatcherOrder.setUserPhone("");
        taxiDispatcherOrder.setFulfilled(0);         //Выполнен заказ: 1 - да, 0 - нет

        taxiDispatcherOrderService.update(taxiDispatcherOrder);
        log.info("update {}", taxiDispatcherOrder);
    }

    @Test
    public void updateNot(){
        create();
        taxiDispatcherOrder = taxiDispatcherOrderService.get(taxiDispatcherOrderId);
        log.info("taxiDispatcherOrder {}", taxiDispatcherOrder);

        taxiDispatcherOrder = null;

        taxiDispatcherOrderService.update(taxiDispatcherOrder);
        log.info("update {}", taxiDispatcherOrder);
    }

    @Test
    public void get(){
        create();
        taxiDispatcherOrder = taxiDispatcherOrderService.get(taxiDispatcherOrderId);
        log.info("get {}", taxiDispatcherOrder);
    }

    @Test
    public void getNot(){
        taxiDispatcherOrder = taxiDispatcherOrderService.get(0);
        log.info("get {}", taxiDispatcherOrder);
    }

    @Test
    public void delete(){
        create();
        log.info("delete {}", taxiDispatcherOrderService.delete(taxiDispatcherOrderId));
    }

    @Test
    public void deleteNet(){
        log.info("delete {}", taxiDispatcherOrderService.delete(0));
    }

    @Test
    public void getAll(){
        taxiDispatcherOrderService.getAll().forEach(tdo -> log.info("getAll {}",tdo));
    }

    @Test
    public void getByIdTaxiDispatcher(){
        taxiDispatcherOrderService.getByIdTaxiDispatcher(1).forEach(tdo -> log.info("getByIdTaxiDispatcher {}", tdo));
    }

    @Test
    public void getByIdTaxiDispatcherNot(){
        taxiDispatcherOrderService.getByIdTaxiDispatcher(-1).forEach(tdo -> log.info("getByIdTaxiDispatcherNot {}", tdo));
    }

    @Test
    public void getByAddressDeparture(){
        create();
        taxiDispatcherOrder = taxiDispatcherOrderService.get(taxiDispatcherOrderId);

        taxiDispatcherOrderService.getByAddressDeparture(taxiDispatcherOrder.getAddressDeparture()).forEach(tdo -> log.info("getByAddressDeparture {}", tdo));
    }

    @Test
    public void getByAddressDepartureNot(){
        taxiDispatcherOrderService.getByAddressDeparture("").forEach(tdo -> log.info("getByAddressDepartureNot {}", tdo));
    }

    @Test
    public void getByAddressArrival(){
        create();
        taxiDispatcherOrder = taxiDispatcherOrderService.get(taxiDispatcherOrderId);

        taxiDispatcherOrderService.getByAddressArrival(taxiDispatcherOrder.getAddressArrival()).forEach(tdo -> log.info("getByAddressArrival {}", tdo));
    }

    @Test
    public void getByAddressArrivalNot(){
        taxiDispatcherOrderService.getByAddressArrival("").forEach(tdo -> log.info("getByAddressArrivalNot {}", tdo));
    }

    @Test
    public void getByUserName(){
        create();
        taxiDispatcherOrder = taxiDispatcherOrderService.get(taxiDispatcherOrderId);

        taxiDispatcherOrderService.getByUserName(taxiDispatcherOrder.getUserName()).forEach(tdo -> log.info("getByUserName {}", tdo));
    }

    @Test
    public void getByUserNameNot(){
        taxiDispatcherOrderService.getByUserName("").forEach(tdo -> log.info("getByUserNameNot {}", tdo));
    }

    @Test
    public void getByUserPhone(){
        create();
        taxiDispatcherOrder = taxiDispatcherOrderService.get(taxiDispatcherOrderId);

        taxiDispatcherOrderService.getByUserPhone(taxiDispatcherOrder.getUserPhone()).forEach(tdo -> log.info("getByUserPhone {}", tdo));
    }

    @Test
    public void getByUserPhoneNot(){
        taxiDispatcherOrderService.getByUserPhone("").forEach(tdo -> log.info("getByUserPhoneNot {}", tdo));
    }

    @Test
    public void getByFulfilled(){
        create();
        taxiDispatcherOrder = taxiDispatcherOrderService.get(taxiDispatcherOrderId);

        taxiDispatcherOrderService.getByFulfilled(taxiDispatcherOrder.getFulfilled()).forEach(tdo -> log.info("getByFulfilled {}", tdo));
    }

    @Test
    public void getByFulfilledNot(){
        taxiDispatcherOrderService.getByFulfilled(-1).forEach(tdo -> log.info("getByFulfilledNot {}", tdo));
    }

    @Test
    public void getByBetweenStartDate(){
        create();
        taxiDispatcherOrder = taxiDispatcherOrderService.get(taxiDispatcherOrderId);

        taxiDispatcherOrderService.getByBetweenStartDate(LocalDateTime.MIN, taxiDispatcherOrder.getStartDate())
                .forEach(tdo -> log.info("getByBetweenStartDate {}", tdo));
    }

    @Test
    public void getByBetweenStartDateNot(){
        taxiDispatcherOrderService.getByBetweenStartDate(null,null).forEach(tdo -> log.info("getByBetweenStartDateNot {}", tdo));
    }

    @Test
    public void getByBetweenEndDate(){
        create();
        taxiDispatcherOrder = taxiDispatcherOrderService.get(taxiDispatcherOrderId);

        taxiDispatcherOrderService.getByBetweenEndDate(taxiDispatcherOrder.getEndDate(), taxiDispatcherOrder.getEndDate())
                .forEach(tdo -> log.info("getByBetweenEndDate {}", tdo));
    }

    @Test
    public void getByBetweenEndDateNot(){
        taxiDispatcherOrderService.getByBetweenEndDate(null,null).forEach(tdo -> log.info("getByBetweenEndDateNot {}", tdo));
    }

    @Test
    public void getFilterTaxiDispatcherOrder(){
        //create();
        //taxiDispatcherOrder = taxiDispatcherOrderService.get(taxiDispatcherOrderId);

        TaxiDispatcher taxiDispatcher = taxiDispatcherService.get(1);

        taxiDispatcherOrder = new TaxiDispatcherOrder();
        taxiDispatcherOrder.setIdTaxiDispatcher(taxiDispatcher);
        taxiDispatcherOrder.setUserName("User test");

        taxiDispatcherOrderService.getFilterTaxiDispatcherOrder(taxiDispatcherOrder)
                .forEach(tdo -> log.info("getFilterTaxiDispatcherOrder {}", tdo));
    }

    @Test
    public void getFilterTaxiDispatcherOrderNot(){
        taxiDispatcherOrderService.getFilterTaxiDispatcherOrder(null).forEach(tdo -> log.info("getFilterTaxiDispatcherOrderNot {}", tdo));
    }

    @Test
    public void getFilterTaxiDispatcherOrderCondition(){
        //create();
        //taxiDispatcherOrder = taxiDispatcherOrderService.get(taxiDispatcherOrderId);

        TaxiDispatcher taxiDispatcher = taxiDispatcherService.get(1);
        taxiDispatcherOrder = new TaxiDispatcherOrder();
        taxiDispatcherOrder.setIdTaxiDispatcher(taxiDispatcher);

        String sqlCondition =" tdo.user_name = \'User test\'";
        taxiDispatcherOrderService.getFilterTaxiDispatcherOrder(taxiDispatcherOrder,sqlCondition)
                .forEach(tdo -> log.info("getFilterTaxiDispatcherOrder {}", tdo));
    }

    @Test
    public void getFilterTaxiDispatcherOrderConditionNot(){
        taxiDispatcherOrderService.getFilterTaxiDispatcherOrder(null,null).forEach(tdo -> log.info("getFilterTaxiDispatcherOrderConditionNot {}", tdo));
    }
}
