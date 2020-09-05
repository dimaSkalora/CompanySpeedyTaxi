package test.com.taxi.speedy.company.repository.spring_jdbc;

import com.taxi.speedy.company.model.Role;
import com.taxi.speedy.company.model.TaxiUserOrder;
import com.taxi.speedy.company.model.User;
import com.taxi.speedy.company.repository.TaxiUserOrderRepository;
import com.taxi.speedy.company.repository.UserRepository;
import com.taxi.speedy.company.repository.spring_jdbc.JDBCUserRepositoryImpl;
import com.taxi.speedy.company.repository.spring_jdbc.JdbcTaxiUserOrderRepositoryImpl;
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

public class JdbcTaxiUserOrderRepositoryImplTest {
    private Logger log = LoggerFactory.getLogger(getClass());

    private ApplicationContext applicationContext;
    private TaxiUserOrderRepository taxiUserOrderRepository;
    private UserRepository userRepository;

    private TaxiUserOrder taxiUserOrder;
    private int idTaxiUserOrder;

    @Before
    public void setUp(){
        applicationContext = new ClassPathXmlApplicationContext("spring/context.xml");
        taxiUserOrderRepository = applicationContext.getBean(JdbcTaxiUserOrderRepositoryImpl.class);
        userRepository = applicationContext.getBean(JDBCUserRepositoryImpl.class);
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
        userRepository.save(newUser);
        log.info("newUser {}",newUser);

        TaxiUserOrder taxiUserOrder = new TaxiUserOrder();
        taxiUserOrder.setDateTimeOrder(LocalDateTime.now());
        taxiUserOrder.setIdUser(newUser);
        taxiUserOrder.setAddressDeparture("test addressDeparture");
        taxiUserOrder.setAddressArrival("test addressArrival");
        taxiUserOrder.setStartDate(LocalDateTime.now().plusMinutes(30));
        taxiUserOrder.setEndDate(LocalDateTime.now().plusHours(1));
        taxiUserOrder.setFulfilled(1);//Выполнен заказ: 1 - да, 0 - нет

        taxiUserOrderRepository.save(taxiUserOrder);
        log.info("create {}",taxiUserOrder);

        idTaxiUserOrder=taxiUserOrder.getId();
    }

    @Test
    public void createNot(){
        TaxiUserOrder taxiUserOrder = new TaxiUserOrder();
        taxiUserOrder.setDateTimeOrder(LocalDateTime.now());
        taxiUserOrder.setIdUser(null);
        taxiUserOrder.setAddressDeparture("test addressDeparture");
        taxiUserOrder.setAddressArrival("test addressArrival");
        taxiUserOrder.setStartDate(LocalDateTime.now().plusMinutes(30));
        taxiUserOrder.setEndDate(LocalDateTime.now().plusHours(1));
        taxiUserOrder.setFulfilled(1);//Выполнен заказ: 1 - да, 0 - нет

        taxiUserOrderRepository.save(taxiUserOrder);
        log.info("create {}",taxiUserOrder);

        idTaxiUserOrder=taxiUserOrder.getId();
    }

    @Test
    public void update(){
        create();
        taxiUserOrder = taxiUserOrderRepository.get(idTaxiUserOrder);
        taxiUserOrder.setAddressDeparture("test update addressDeparture");
        taxiUserOrder.setAddressArrival("test update addressArrival");
        taxiUserOrder.setStartDate(LocalDateTime.now().plusMinutes(30));
        taxiUserOrder.setEndDate(LocalDateTime.now().plusHours(1));
        taxiUserOrder.setFulfilled(0);//Выполнен заказ: 1 - да, 0 - нет

        taxiUserOrderRepository.save(taxiUserOrder);
        log.info("update {}",taxiUserOrder);
    }

    @Test
    public void updateNot(){
        taxiUserOrder = taxiUserOrderRepository.get(idTaxiUserOrder);
        taxiUserOrder.setAddressDeparture(null);
        taxiUserOrder.setAddressArrival(null);
        taxiUserOrder.setStartDate(LocalDateTime.now().plusMinutes(30));
        taxiUserOrder.setEndDate(LocalDateTime.now().plusHours(1));
        taxiUserOrder.setFulfilled(0);//Выполнен заказ: 1 - да, 0 - нет

        taxiUserOrderRepository.save(taxiUserOrder);
        log.info("updateNot {}",taxiUserOrder);
    }

    @Test
    public void delete(){
        create();
        taxiUserOrder = taxiUserOrderRepository.get(idTaxiUserOrder);
        log.info("delete {}",taxiUserOrderRepository.delete(taxiUserOrder.getId()));
    }

    @Test
    public void deleteNot(){
        log.info("deleteNot {}",taxiUserOrderRepository.delete(0));
    }

    @Test
    public void get(){
        create();
        taxiUserOrder = taxiUserOrderRepository.get(idTaxiUserOrder);
        log.info("get {}",taxiUserOrder);
    }

    @Test
    public void getNot(){
        taxiUserOrder = taxiUserOrderRepository.get(-1);
        log.info("getNot {}",taxiUserOrder);
    }

    @Test
    public void getAll(){
        List<TaxiUserOrder> list = taxiUserOrderRepository.getAll();
        list.forEach(tuo -> log.info("getAll {}",tuo));
    }

    @Test
    public void getAllIdUser(){
        create();
        List<TaxiUserOrder> list = taxiUserOrderRepository.getAllIdUser(idTaxiUserOrder);
        list.forEach(tuo->log.info("getAllIdUser {}",tuo));
    }

    @Test
    public void getAllIdUserNot(){
        List<TaxiUserOrder> list = taxiUserOrderRepository.getAllIdUser(-1);
        list.forEach(tuo->log.info("getAllIdUserNot {}",tuo));
    }

    @Test
    public void filterTaxiUserOrder(){
        taxiUserOrder = new TaxiUserOrder();
        taxiUserOrder.setAddressDeparture("test addressDeparture");

        List<TaxiUserOrder> list = taxiUserOrderRepository.filterTaxiUserOrder(taxiUserOrder);
        list.forEach(tuo->log.info("filterTaxiUserOrder {}",tuo));
    }

    @Test
    public void filterTaxiUserOrderNot(){
        taxiUserOrder = new TaxiUserOrder();
        taxiUserOrder.setAddressDeparture("");

        List<TaxiUserOrder> list = taxiUserOrderRepository.filterTaxiUserOrder(taxiUserOrder);
        list.forEach(tuo->log.info("filterTaxiUserOrderNot {}",tuo));
    }

    @Test
    public void filterTaxiUserOrderCondition(){
        taxiUserOrder = new TaxiUserOrder();
        taxiUserOrder.setAddressDeparture("test addressDeparture");

        String sqlCondition = " tuo.fulfilled = 0";
        List<TaxiUserOrder> list = taxiUserOrderRepository.filterTaxiUserOrder(taxiUserOrder,sqlCondition);
        list.forEach(tuo->log.info("filterTaxiUserOrderCondition {}",tuo));
    }

    @Test
    public void filterTaxiUserOrderConditionNot(){
        taxiUserOrder = new TaxiUserOrder();
        taxiUserOrder.setAddressDeparture(null);

        String sqlCondition = " tuo.fulfilled = sdff";

        List<TaxiUserOrder> list = taxiUserOrderRepository.filterTaxiUserOrder(taxiUserOrder,sqlCondition);
        list.forEach(tuo->log.info("filterTaxiUserOrderConditionNot {}",tuo));
    }

}
