package test.com.taxi.speedy.company.repository.spring_jdbc;

import com.taxi.speedy.company.model.VehicleState;
import com.taxi.speedy.company.repository.VehicleStateRepository;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class JdbcVehicleStateRepositoryImplTest {

    private Logger log = LoggerFactory.getLogger(JdbcVehicleStateRepositoryImplTest.class);

    private ApplicationContext applicationContext;
    private VehicleStateRepository vehicleStateRepository;

    private VehicleState vehicleState;
    private int idVehicleState;

    @Before
    public void setUp(){
        applicationContext = new ClassPathXmlApplicationContext("spring/context.xml");
        vehicleStateRepository = (VehicleStateRepository) applicationContext.getBean("jdbcVehicleStateRepositoryImpl");
    }

    @Test
    public void create(){
        VehicleState vehicleState = new VehicleState();
        vehicleState.setNameVS("Test state");

        vehicleStateRepository.save(vehicleState);
        log.info("create {}",vehicleState);
        idVehicleState = vehicleState.getId();
    }

    @Test
    public void createNot(){
        VehicleState vehicleState = null;

        vehicleStateRepository.save(vehicleState);
        log.info("create {}",vehicleState);
        idVehicleState = vehicleState.getId();
    }

    @Test
    public void update(){
        create();
        vehicleState = vehicleStateRepository.get(idVehicleState);
        log.info("get {}", vehicleState);

        vehicleState.setNameVS("Test update");
        vehicleStateRepository.save(vehicleState);

        log.info("update {}",vehicleState);
    }

    @Test
    public void updateNot(){
        create();
        vehicleState = vehicleStateRepository.get(idVehicleState);
        log.info("get {}", vehicleState);

        vehicleState = null;
        vehicleStateRepository.save(vehicleState);

        log.info("update {}",vehicleState);
    }

    @Test
    public void get(){
        create();
        vehicleState = vehicleStateRepository.get(idVehicleState);
        log.info("get {}", vehicleState);
    }

    @Test
    public void getNot(){
        vehicleState = vehicleStateRepository.get(0);
        log.info("get {}", vehicleState);
    }

    @Test
    public void getAll(){
        List<VehicleState> vehicleStateList = vehicleStateRepository.getAll();
        vehicleStateList.forEach(vs->log.info("vehicleState {}",vs));
    }

    @Test
    public void getByNameVS(){
        vehicleState = vehicleStateRepository.getByNameVS("Сломан");
        log.info("getByNameVS {}",vehicleState);
    }

    @Test
    public void getByNameVSNot(){
        vehicleState = vehicleStateRepository.getByNameVS("Сломан2");
        log.info("getByNameVSNot {}",vehicleState);
    }
}
