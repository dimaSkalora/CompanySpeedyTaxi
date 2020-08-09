package test.com.taxi.speedy.company.service;

import com.taxi.speedy.company.model.VehicleState;
import com.taxi.speedy.company.service.VehicleStateService;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class VehicleStateServiceImplTest {

    private Logger log = LoggerFactory.getLogger(VehicleStateServiceImplTest.class);

    private ApplicationContext applicationContext;
    private VehicleStateService vehicleStateService;

    private VehicleState vehicleState;
    private int idVehicleState;

    @Before
    public void setUp(){
        applicationContext = new ClassPathXmlApplicationContext("spring/context.xml");
        vehicleStateService = (VehicleStateService) applicationContext.getBean("vehicleStateServiceImpl");
    }

    @Test
    public void create(){
        VehicleState vehicleState = new VehicleState();
        vehicleState.setNameVS("Test state");

        vehicleStateService.create(vehicleState);
        log.info("create {}",vehicleState);
        idVehicleState = vehicleState.getId();
    }

    @Test
    public void createNot(){
        VehicleState vehicleState = null;

        vehicleStateService.create(vehicleState);
        log.info("create {}",vehicleState);
        idVehicleState = vehicleState.getId();
    }

    @Test
    public void update(){
        create();
        vehicleState = vehicleStateService.get(idVehicleState);
        log.info("get {}", vehicleState);

        vehicleState.setNameVS("Test update");
        vehicleStateService.update(vehicleState);

        log.info("update {}",vehicleState);
    }

    @Test
    public void updateNot(){
        create();
        vehicleState = vehicleStateService.get(idVehicleState);
        log.info("get {}", vehicleState);

        vehicleState = null;
        vehicleStateService.update(vehicleState);

        log.info("update {}",vehicleState);
    }

    @Test
    public void get(){
        create();
        vehicleState = vehicleStateService.get(idVehicleState);
        log.info("get {}", vehicleState);
    }

    @Test
    public void getNot(){
        vehicleState = vehicleStateService.get(0);
        log.info("get {}", vehicleState);
    }

    @Test
    public void getAll(){
        List<VehicleState> vehicleStateList = vehicleStateService.getAll();
        vehicleStateList.forEach(vs->log.info("vehicleState {}",vs));
    }

    @Test
    public void getByNameVS(){
        vehicleState = vehicleStateService.getByNameVS("Сломан");
        log.info("getByNameVS {}",vehicleState);
    }

    @Test
    public void getByNameVSNot(){
        vehicleState = vehicleStateService.getByNameVS("Сломан2");
        log.info("getByNameVSNot {}",vehicleState);
    }
}
