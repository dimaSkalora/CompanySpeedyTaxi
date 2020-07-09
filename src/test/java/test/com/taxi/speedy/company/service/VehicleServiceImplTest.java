package test.com.taxi.speedy.company.service;

import com.taxi.speedy.company.model.Vehicle;
import com.taxi.speedy.company.service.service_impl.VehicleServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDateTime;
import java.util.List;

public class VehicleServiceImplTest {
    private Logger log = LoggerFactory.getLogger(VehicleServiceImplTest.class);

    private ApplicationContext applicationContext;
    private VehicleServiceImpl vehicleService;

    private int vehicleId;
    private Vehicle vehicle;

    @Before
    public void setUp(){
        applicationContext = new ClassPathXmlApplicationContext("spring/context.xml");
        vehicleService = applicationContext.getBean(VehicleServiceImpl.class);
    }

    @Test
    public void create(){
        Vehicle vehicleNew = new Vehicle();
        vehicleNew.setNameCar(LocalDateTime.now().toLocalTime().toString());
        vehicleNew.setVehicleNumber("bt 456 bt");
        vehicleNew.setYearIssue(2000);
        vehicleNew.setCategory("легковая");
        vehicleNew.setColor("blue");
        vehicleNew.setFuelConsumption(9);

        vehicleService.create(vehicleNew);
        log.info("vehicle create {}",vehicleNew);
        vehicleId = vehicleNew.getId();
    }

    @Test
    public void createNull(){
        Vehicle vehicleNew = null;
        log.info("vehicle create {}",vehicleNew);
        vehicleService.create(vehicleNew);
    }
    @Test
    public void update(){
        create();
        vehicle = vehicleService.get(vehicleId);
        log.info("vehicle get {}", vehicle);
        vehicle.setNameCar(LocalDateTime.now().toLocalTime().toString());
        vehicleService.update(vehicle);
        log.info("vehicle update {}",vehicle);
    }

    @Test
    public void updateNull(){
        vehicle = null;
        log.info("vehicle get {}", vehicle);
        vehicleService.update(vehicle);
    }

    @Test
    public void delete(){
        create();
        vehicle = vehicleService.get(vehicleId);
        log.info("delete {}",vehicle);
        boolean isDelete = vehicleService.delete(vehicle.getId());
        log.info("is delete {}",isDelete);
    }

    @Test
    public void deleteWithOutId(){
        vehicle = null;
        log.info("delete {}",vehicle);
        boolean isDelete = vehicleService.delete(vehicle.getId());
    }

    @Test
    public void get(){
        create();
        vehicle = vehicleService.get(vehicleId);
        log.info("get {}",vehicle);
    }

    @Test
    public void getWithOutId(){
        vehicle = vehicleService.get(0);
    }

    @Test
    public void getByNameCar(){
        List<Vehicle> vehicles = vehicleService.getByNameCar("Toyota Colora");
        log.info("vehicles getByNameCar {}", vehicles);
    }

    @Test
    public void getByNameCarNull(){
        List<Vehicle> vehicles = vehicleService.getByNameCar(null);
    }

    @Test
    public void getByNumberCar(){
        List<Vehicle> vehicles = vehicleService.getVehicleNumber("AA3334BQ");
        log.info("vehicles getByNumberCar {}",vehicles);
    }

    @Test
    public void getByNumberCarNull(){
        List<Vehicle> vehicles = vehicleService.getVehicleNumber(null);
    }
}
