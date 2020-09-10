package test.com.taxi.speedy.company.repository.spring_jdbc;

import com.taxi.speedy.company.model.TaxiDispatcherOrder;
import com.taxi.speedy.company.model.TaxiOrderAcceptance;
import com.taxi.speedy.company.model.TaxiUserOrder;
import com.taxi.speedy.company.model.UserVehicle;
import com.taxi.speedy.company.repository.TaxiDispatcherOrderRepository;
import com.taxi.speedy.company.repository.TaxiOrderAcceptanceRepository;
import com.taxi.speedy.company.repository.TaxiUserOrderRepository;
import com.taxi.speedy.company.repository.UserVehicleRepository;
import com.taxi.speedy.company.repository.spring_jdbc.JdbcTaxiDispatcherOrderRepositoryImpl;
import com.taxi.speedy.company.repository.spring_jdbc.JdbcTaxiUserOrderRepositoryImpl;
import com.taxi.speedy.company.repository.spring_jdbc.JdbcUserVehiclesRepositoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class JdbcTaxiOrderAcceptanceRepositoryImplTest {
    private Logger log = LoggerFactory.getLogger(getClass());

    private ApplicationContext applicationContext;
    TaxiOrderAcceptanceRepository taxiOrderAcceptanceRepository;
    UserVehicleRepository userVehicleRepository;
    TaxiDispatcherOrderRepository taxiDispatcherOrderRepository;
    TaxiUserOrderRepository taxiUserOrderRepository;

    TaxiOrderAcceptance taxiOrderAcceptance;
    int idTaxiOrderAcceptance;

    @Before
    public void setUp(){
        applicationContext = new ClassPathXmlApplicationContext("spring/context.xml");
        taxiOrderAcceptanceRepository = (TaxiOrderAcceptanceRepository) applicationContext.getBean("jdbcTaxiOrderAcceptanceRepositoryImpl");
        userVehicleRepository = applicationContext.getBean(JdbcUserVehiclesRepositoryImpl.class);
        taxiDispatcherOrderRepository = applicationContext.getBean(JdbcTaxiDispatcherOrderRepositoryImpl.class);
        taxiUserOrderRepository = applicationContext.getBean(JdbcTaxiUserOrderRepositoryImpl.class);
    }

    @Test
    public void create(){
        UserVehicle userVehicle = userVehicleRepository.get(1);
        TaxiDispatcherOrder taxiDispatcherOrder = taxiDispatcherOrderRepository.get(2);

        TaxiOrderAcceptance taxiOrderAcceptance = new TaxiOrderAcceptance();
        taxiOrderAcceptance.setIdUserVehicle(userVehicle);
        taxiOrderAcceptance.setIdTaxiDispatcherOrder(taxiDispatcherOrder);
        taxiOrderAcceptance.setExecutionStatus(1);    //Статус выполнение: 1-выполнено, 0- не выполнено
        taxiOrderAcceptance.setAdoptionStatus(1);    //Статус принятие: 1-принятый заказ, 0- не принятый заказ

        log.info("create {}",taxiOrderAcceptance);

        taxiOrderAcceptanceRepository.save(taxiOrderAcceptance);

        idTaxiOrderAcceptance=taxiOrderAcceptance.getId();
    }

    @Test
    public void createNot(){
        UserVehicle userVehicle = userVehicleRepository.get(1);
        TaxiDispatcherOrder taxiDispatcherOrder = taxiDispatcherOrderRepository.get(1);

        TaxiOrderAcceptance taxiOrderAcceptance = new TaxiOrderAcceptance();
        taxiOrderAcceptance.setIdUserVehicle(userVehicle);
        taxiOrderAcceptance.setIdTaxiDispatcherOrder(taxiDispatcherOrder);

        log.info("createNot {}",taxiOrderAcceptance);

        taxiOrderAcceptanceRepository.save(taxiOrderAcceptance);

        idTaxiOrderAcceptance=taxiOrderAcceptance.getId();
    }

    @Test
    public void update(){
        create();

        taxiOrderAcceptance = taxiOrderAcceptanceRepository.get(idTaxiOrderAcceptance);
        TaxiDispatcherOrder taxiDispatcherOrder = taxiDispatcherOrderRepository.get(3);
        taxiOrderAcceptance.setIdTaxiDispatcherOrder(taxiDispatcherOrder);

        log.info("update {}",taxiOrderAcceptance);
    }

    @Test
    public void updateNot(){
        create();

        taxiOrderAcceptance = taxiOrderAcceptanceRepository.get(idTaxiOrderAcceptance);
        TaxiDispatcherOrder taxiDispatcherOrder = taxiDispatcherOrderRepository.get(3);
        taxiOrderAcceptance.setIdUserVehicle(null);
        taxiOrderAcceptance.setIdTaxiDispatcherOrder(taxiDispatcherOrder);
        taxiOrderAcceptance.setExecutionStatus(null);

        taxiOrderAcceptanceRepository.save(taxiOrderAcceptance);

        log.info("updateNot {}",taxiOrderAcceptance);
    }

    @Test
    public void get(){
        create();

        taxiOrderAcceptance = taxiOrderAcceptanceRepository.get(idTaxiOrderAcceptance);

        log.info("get {}",taxiOrderAcceptance);
    }

    @Test
    public void getNot(){
        create();

        taxiOrderAcceptance = taxiOrderAcceptanceRepository.get(1111111);

        log.info("getNot {}",taxiOrderAcceptance);
    }

    @Test
    public void delete(){
        create();

        taxiOrderAcceptance = taxiOrderAcceptanceRepository.get(idTaxiOrderAcceptance);

        log.info("delete {}",taxiOrderAcceptanceRepository.delete(taxiOrderAcceptance.getId()));
    }

    @Test
    public void deleteNot(){
        log.info("deleteNot {}",taxiOrderAcceptanceRepository.delete(0));
    }

    @Test
    public void filterTaxiUserOrder(){
        taxiOrderAcceptance = new TaxiOrderAcceptance();
        taxiOrderAcceptance.setAdoptionStatus(0);

        List<TaxiOrderAcceptance> list = taxiOrderAcceptanceRepository.filterTaxiUserOrder(taxiOrderAcceptance);
        list.forEach(toa -> log.info("toa {}",toa));
    }

    @Test
    public void filterTaxiUserOrderNot(){
        taxiOrderAcceptance = null;

        List<TaxiOrderAcceptance> list = taxiOrderAcceptanceRepository.filterTaxiUserOrder(taxiOrderAcceptance);
        list.forEach(toa -> log.info("toa {}",toa));
    }

    @Test
    public void filterTaxiUserOrderSqlCondition(){
        taxiOrderAcceptance = new TaxiOrderAcceptance();

        String sqlCondition = " toa.adoption_status=0";

        List<TaxiOrderAcceptance> list = taxiOrderAcceptanceRepository.filterTaxiUserOrder(taxiOrderAcceptance,sqlCondition);
        list.forEach(toa -> log.info("toa {}",toa));
    }

    @Test
    public void filterTaxiUserOrderSqlConditionNot(){
        taxiOrderAcceptance =null;

        String sqlCondition = "";

        List<TaxiOrderAcceptance> list = taxiOrderAcceptanceRepository.filterTaxiUserOrder(taxiOrderAcceptance,sqlCondition);
        list.forEach(toa -> log.info("toa {}",toa));
    }

}
