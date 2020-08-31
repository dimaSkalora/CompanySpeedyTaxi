package test.com.taxi.speedy.company.repository.spring_jdbc;

import com.taxi.speedy.company.model.TaxiDispatcher;
import com.taxi.speedy.company.model.TaxiDispatcherOrder;
import com.taxi.speedy.company.repository.TaxiDispatcherOrderRepository;
import com.taxi.speedy.company.repository.TaxiDispatcherRepository;
import com.taxi.speedy.company.repository.spring_jdbc.JdbcTaxiDispatcherRepositoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDateTime;
import java.util.List;

public class JdbcTaxiDispatcherOrderRepositoryImplTest {
    private Logger log = LoggerFactory.getLogger(JdbcTaxiDispatcherOrderRepositoryImplTest.class);

    private ApplicationContext applicationContext;
    private TaxiDispatcherOrderRepository taxiDispatcherOrderRepository;
    private TaxiDispatcherRepository taxiDispatcherRepository;

    private TaxiDispatcherOrder taxiDispatcherOrder;
    private int taxiDispatcherOrderId;

    @Before
    public void setUp(){
        applicationContext = new ClassPathXmlApplicationContext("/spring/context.xml");
        taxiDispatcherOrderRepository = (TaxiDispatcherOrderRepository) applicationContext.getBean("jdbcTaxiDispatcherOrderRepositoryImpl");
        taxiDispatcherRepository = applicationContext.getBean(JdbcTaxiDispatcherRepositoryImpl.class);
    }

    @Test
    public void create(){
        TaxiDispatcher taxiDispatcher = taxiDispatcherRepository.get(1);
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

        taxiDispatcherOrderRepository.save(taxiDispatcherOrder);
        log.info("create {}",taxiDispatcherOrder);

        taxiDispatcherOrderId = taxiDispatcherOrder.getId();
    }

    @Test
    public void createNot(){
        TaxiDispatcher taxiDispatcher = taxiDispatcherRepository.get(1);
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

        taxiDispatcherOrderRepository.save(taxiDispatcherOrder);
        log.info("taxiDispatcherOrder {}",taxiDispatcherOrder);

        taxiDispatcherOrderId = taxiDispatcherOrder.getId();
    }

    @Test
    public void update(){
        create();
        taxiDispatcherOrder = taxiDispatcherOrderRepository.get(taxiDispatcherOrderId);
        log.info("taxiDispatcherOrder {}", taxiDispatcherOrder);

        taxiDispatcherOrder.setUserName("");
        taxiDispatcherOrder.setUserPhone("");
        taxiDispatcherOrder.setFulfilled(0);         //Выполнен заказ: 1 - да, 0 - нет

        taxiDispatcherOrderRepository.save(taxiDispatcherOrder);
        log.info("update {}", taxiDispatcherOrder);
    }

    @Test
    public void updateNot(){
        create();
        taxiDispatcherOrder = taxiDispatcherOrderRepository.get(taxiDispatcherOrderId);
        log.info("taxiDispatcherOrder {}", taxiDispatcherOrder);

        taxiDispatcherOrder = null;

        taxiDispatcherOrderRepository.save(taxiDispatcherOrder);
        log.info("update {}", taxiDispatcherOrder);
    }

    @Test
    public void get(){
        create();
        taxiDispatcherOrder = taxiDispatcherOrderRepository.get(taxiDispatcherOrderId);
        log.info("get {}", taxiDispatcherOrder);
    }

    @Test
    public void getNot(){
        taxiDispatcherOrder = taxiDispatcherOrderRepository.get(0);
        log.info("get {}", taxiDispatcherOrder);
    }

    @Test
    public void delete(){
        create();
        log.info("delete {}", taxiDispatcherOrderRepository.delete(taxiDispatcherOrderId));
    }

    @Test
    public void deleteNet(){
        log.info("delete {}", taxiDispatcherOrderRepository.delete(0));
    }

    @Test
    public void getAll(){
        taxiDispatcherOrderRepository.getAll().forEach(tdo -> log.info("getAll {}",tdo));
    }

    @Test
    public void getByIdTaxiDispatcher(){
        taxiDispatcherOrderRepository.getByIdTaxiDispatcher(2).forEach(tdo -> log.info("getByIdTaxiDispatcher {}", tdo));
    }

    @Test
    public void getByIdTaxiDispatcherNot(){
        taxiDispatcherOrderRepository.getByIdTaxiDispatcher(0).forEach(tdo -> log.info("getByIdTaxiDispatcherNot {}", tdo));
    }

    @Test
    public void getByAddressDeparture(){
        create();
        taxiDispatcherOrder = taxiDispatcherOrderRepository.get(taxiDispatcherOrderId);

        taxiDispatcherOrderRepository.getByAddressDeparture(taxiDispatcherOrder.getAddressDeparture()).forEach(tdo -> log.info("getByAddressDeparture {}", tdo));
    }

    @Test
    public void getByAddressDepartureNot(){
        taxiDispatcherOrderRepository.getByAddressDeparture("").forEach(tdo -> log.info("getByAddressDepartureNot {}", tdo));
    }

    @Test
    public void getByAddressArrival(){
        create();
        taxiDispatcherOrder = taxiDispatcherOrderRepository.get(taxiDispatcherOrderId);

        taxiDispatcherOrderRepository.getByAddressArrival(taxiDispatcherOrder.getAddressArrival()).forEach(tdo -> log.info("getByAddressArrival {}", tdo));
    }

    @Test
    public void getByAddressArrivalNot(){
        taxiDispatcherOrderRepository.getByAddressArrival("").forEach(tdo -> log.info("getByAddressArrivalNot {}", tdo));
    }

    @Test
    public void getByUserName(){
        create();
        taxiDispatcherOrder = taxiDispatcherOrderRepository.get(taxiDispatcherOrderId);

        taxiDispatcherOrderRepository.getByUserName(taxiDispatcherOrder.getUserName()).forEach(tdo -> log.info("getByUserName {}", tdo));
    }

    @Test
    public void getByUserNameNot(){
        taxiDispatcherOrderRepository.getByUserName("").forEach(tdo -> log.info("getByUserNameNot {}", tdo));
    }

    @Test
    public void getByUserPhone(){
        create();
        taxiDispatcherOrder = taxiDispatcherOrderRepository.get(taxiDispatcherOrderId);

        taxiDispatcherOrderRepository.getByUserPhone(taxiDispatcherOrder.getUserPhone()).forEach(tdo -> log.info("getByUserPhone {}", tdo));
    }

    @Test
    public void getByUserPhoneNot(){
        taxiDispatcherOrderRepository.getByUserPhone("").forEach(tdo -> log.info("getByUserPhoneNot {}", tdo));
    }

    @Test
    public void getByFulfilled(){
        create();
        taxiDispatcherOrder = taxiDispatcherOrderRepository.get(taxiDispatcherOrderId);

        taxiDispatcherOrderRepository.getByFulfilled(taxiDispatcherOrder.getFulfilled()).forEach(tdo -> log.info("getByFulfilled {}", tdo));
    }

    @Test
    public void getByFulfilledNot(){
        taxiDispatcherOrderRepository.getByFulfilled(-1).forEach(tdo -> log.info("getByFulfilledNot {}", tdo));
    }

    @Test
    public void getByBetweenStartDate(){
        create();
        taxiDispatcherOrder = taxiDispatcherOrderRepository.get(taxiDispatcherOrderId);

        taxiDispatcherOrderRepository.getByBetweenStartDate(LocalDateTime.MIN, taxiDispatcherOrder.getStartDate())
                .forEach(tdo -> log.info("getByBetweenStartDate {}", tdo));
    }

    @Test
    public void getByBetweenStartDateNot(){
        taxiDispatcherOrderRepository.getByBetweenStartDate(null,null).forEach(tdo -> log.info("getByBetweenStartDateNot {}", tdo));
    }

    @Test
    public void getByBetweenEndDate(){
        create();
        taxiDispatcherOrder = taxiDispatcherOrderRepository.get(taxiDispatcherOrderId);

        taxiDispatcherOrderRepository.getByBetweenEndDate(taxiDispatcherOrder.getEndDate(), taxiDispatcherOrder.getEndDate())
                .forEach(tdo -> log.info("getByBetweenEndDate {}", tdo));
    }

    @Test
    public void getByBetweenEndDateNot(){
        taxiDispatcherOrderRepository.getByBetweenEndDate(null,null).forEach(tdo -> log.info("getByBetweenEndDateNot {}", tdo));
    }

    @Test
    public void getFilterTaxiDispatcherOrder(){
        //create();
        //taxiDispatcherOrder = taxiDispatcherOrderRepository.get(taxiDispatcherOrderId);

        TaxiDispatcher taxiDispatcher = taxiDispatcherRepository.get(1);
        taxiDispatcherOrder = new TaxiDispatcherOrder();
        taxiDispatcherOrder.setIdTaxiDispatcher(taxiDispatcher);
        taxiDispatcherOrder.setUserName("User test");

        taxiDispatcherOrderRepository.getFilterTaxiDispatcherOrder(taxiDispatcherOrder)
                .forEach(tdo -> log.info("getFilterTaxiDispatcherOrder {}", tdo));
    }

    @Test
    public void getFilterTaxiDispatcherOrderNot(){
        taxiDispatcherOrderRepository.getFilterTaxiDispatcherOrder(null).forEach(tdo -> log.info("getFilterTaxiDispatcherOrderNot {}", tdo));
    }

    @Test
    public void getFilterTaxiDispatcherOrderCondition(){
        //create();
        //taxiDispatcherOrder = taxiDispatcherOrderRepository.get(taxiDispatcherOrderId);

        TaxiDispatcher taxiDispatcher = taxiDispatcherRepository.get(1);
        taxiDispatcherOrder = new TaxiDispatcherOrder();
        taxiDispatcherOrder.setIdTaxiDispatcher(taxiDispatcher);

        String sqlCondition =" tdo.user_name = \'User test\'";
        taxiDispatcherOrderRepository.getFilterTaxiDispatcherOrder(taxiDispatcherOrder,sqlCondition)
                .forEach(tdo -> log.info("getFilterTaxiDispatcherOrder {}", tdo));
    }

    @Test
    public void getFilterTaxiDispatcherOrderConditionNot(){
        taxiDispatcherOrderRepository.getFilterTaxiDispatcherOrder(null,null).forEach(tdo -> log.info("getFilterTaxiDispatcherOrderConditionNot {}", tdo));
    }
}
