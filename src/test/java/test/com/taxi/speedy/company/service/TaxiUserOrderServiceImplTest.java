package test.com.taxi.speedy.company.service;

import com.taxi.speedy.company.model.Role;
import com.taxi.speedy.company.model.TaxiUserOrder;
import com.taxi.speedy.company.model.User;
import com.taxi.speedy.company.service.TaxiUserOrderService;
import com.taxi.speedy.company.service.UserService;
import com.taxi.speedy.company.service.service_impl.TaxiUserOrderServiceImpl;
import com.taxi.speedy.company.service.service_impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static com.taxi.speedy.company.model.Role.ROLE_USER;

public class TaxiUserOrderServiceImplTest {
    private Logger log = LoggerFactory.getLogger(getClass());

    private ApplicationContext applicationContext;
    private TaxiUserOrderService taxiUserOrderService;
    private UserService userServicese;

    private TaxiUserOrder taxiUserOrder;
    private int idTaxiUserOrder;

    @Before
    public void setUp(){
        applicationContext = new ClassPathXmlApplicationContext("spring/context.xml");
        taxiUserOrderService = applicationContext.getBean(TaxiUserOrderServiceImpl.class);
        userServicese = applicationContext.getBean(UserServiceImpl.class);
    }

    @Test
    public void create(){
        HashSet<Role> roles = new HashSet<>();
        roles.add(ROLE_USER);

        User newUser = new User();
        newUser.setName("Test user");
        newUser.setPassword("Test password");
        newUser.setAddress("Test address");
        newUser.setPhone(""+ LocalTime.now().toSecondOfDay());
        newUser.setEmail("Test"+ LocalTime.now().toSecondOfDay()+"@email.com");
        newUser.setEnabled(true);
        newUser.setRegistered(new Date());
        newUser.setRoles(roles);
        userServicese.create(newUser);
        log.info("newUser {}",newUser);

        TaxiUserOrder taxiUserOrder = new TaxiUserOrder();
        taxiUserOrder.setDateTimeOrder(LocalDateTime.now());
        taxiUserOrder.setIdUser(newUser);
        taxiUserOrder.setAddressDeparture("test addressDeparture");
        taxiUserOrder.setAddressArrival("test addressArrival");
        taxiUserOrder.setStartDate(LocalDateTime.now().plusMinutes(30));
        taxiUserOrder.setEndDate(LocalDateTime.now().plusHours(1));
        taxiUserOrder.setFulfilled(1);//Выполнен заказ: 1 - да, 0 - нет

        taxiUserOrderService.create(taxiUserOrder);
        log.info("create {}",taxiUserOrder);

        idTaxiUserOrder=taxiUserOrder.getId();
    }

    @Test
    public void createNot(){
        TaxiUserOrder taxiUserOrder = null;

        taxiUserOrderService.create(taxiUserOrder);
        log.info("create {}",taxiUserOrder);

        idTaxiUserOrder=taxiUserOrder.getId();
    }

    @Test
    public void update(){
        create();
        taxiUserOrder = taxiUserOrderService.get(idTaxiUserOrder);
        taxiUserOrder.setAddressDeparture("test update addressDeparture");
        taxiUserOrder.setAddressArrival("test update addressArrival");
        taxiUserOrder.setStartDate(LocalDateTime.now().plusMinutes(30));
        taxiUserOrder.setEndDate(LocalDateTime.now().plusHours(1));
        taxiUserOrder.setFulfilled(0);//Выполнен заказ: 1 - да, 0 - нет

        taxiUserOrderService.update(taxiUserOrder);
        log.info("update {}",taxiUserOrder);
    }

    @Test
    public void updateNot(){
        taxiUserOrder = taxiUserOrderService.get(idTaxiUserOrder);
        taxiUserOrder.setId(-1);
        taxiUserOrder.setAddressDeparture(null);
        taxiUserOrder.setAddressArrival(null);
        taxiUserOrder.setStartDate(LocalDateTime.now().plusMinutes(30));
        taxiUserOrder.setEndDate(LocalDateTime.now().plusHours(1));
        taxiUserOrder.setFulfilled(0);//Выполнен заказ: 1 - да, 0 - нет

        taxiUserOrderService.update(taxiUserOrder);
        log.info("updateNot {}",taxiUserOrder);
    }

    @Test
    public void delete(){
        create();
        taxiUserOrder = taxiUserOrderService.get(idTaxiUserOrder);
        log.info("delete {}",taxiUserOrderService.delete(taxiUserOrder.getId()));
    }

    @Test
    public void deleteNot(){
        log.info("deleteNot {}",taxiUserOrderService.delete(-1));
    }

    @Test
    public void get(){
        create();
        taxiUserOrder = taxiUserOrderService.get(idTaxiUserOrder);
        log.info("get {}",taxiUserOrder);
    }

    @Test
    public void getNot(){
        taxiUserOrder = taxiUserOrderService.get(-1);
        log.info("getNot {}",taxiUserOrder);
    }

    @Test
    public void getAll(){
        List<TaxiUserOrder> list = taxiUserOrderService.getAll();
        list.forEach(tuo -> log.info("getAll {}",tuo));
    }

    @Test
    public void getAllIdUser(){
        create();
        List<TaxiUserOrder> list = taxiUserOrderService.getAllIdUser(idTaxiUserOrder);
        list.forEach(tuo->log.info("getAllIdUser {}",tuo));
    }

    @Test
    public void getAllIdUserNot(){
        List<TaxiUserOrder> list = taxiUserOrderService.getAllIdUser(-10000);
        list.forEach(tuo->log.info("getAllIdUserNot {}",tuo));
    }

    @Test
    public void filterTaxiUserOrder(){
        taxiUserOrder = new TaxiUserOrder();
        taxiUserOrder.setAddressDeparture("test addressDeparture");

        List<TaxiUserOrder> list = taxiUserOrderService.filterTaxiUserOrder(taxiUserOrder);
        list.forEach(tuo->log.info("filterTaxiUserOrder {}",tuo));
    }

    @Test
    public void filterTaxiUserOrderNot(){
        taxiUserOrder = new TaxiUserOrder();
        taxiUserOrder.setAddressDeparture("");

        List<TaxiUserOrder> list = taxiUserOrderService.filterTaxiUserOrder(taxiUserOrder);
        list.forEach(tuo->log.info("filterTaxiUserOrderNot {}",tuo));
    }

    @Test
    public void filterTaxiUserOrderCondition(){
        taxiUserOrder = new TaxiUserOrder();
        taxiUserOrder.setAddressDeparture("test addressDeparture");

        String sqlCondition = " tuo.fulfilled = 0";
        List<TaxiUserOrder> list = taxiUserOrderService.filterTaxiUserOrder(taxiUserOrder,sqlCondition);
        list.forEach(tuo->log.info("filterTaxiUserOrderCondition {}",tuo));
    }

    @Test
    public void filterTaxiUserOrderConditionNot(){
        taxiUserOrder = new TaxiUserOrder();
        taxiUserOrder.setAddressDeparture(null);

        String sqlCondition = " tuo.fulfilled = 11";

        List<TaxiUserOrder> list = taxiUserOrderService.filterTaxiUserOrder(taxiUserOrder,sqlCondition);
        list.forEach(tuo->log.info("filterTaxiUserOrderConditionNot {}",tuo));
    }
}
