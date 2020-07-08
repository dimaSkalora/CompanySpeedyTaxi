package test.com.taxi.speedy.company.repository.spring_jdbc;

import com.taxi.speedy.company.model.Vehicle;
import com.taxi.speedy.company.repository.spring_jdbc.JDBCVehicleRepositoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class JDBCVehicleRepositoryImplTest {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private ApplicationContext context = null;
    private JDBCVehicleRepositoryImpl jdbcVehicleRepositoryImpl = null;

    private int vehicleId;
    private Vehicle vehicle;

    @Before
    public void setUp(){
        context = new ClassPathXmlApplicationContext("spring/context.xml");
        jdbcVehicleRepositoryImpl = context.getBean(JDBCVehicleRepositoryImpl.class);
    }

    @Test
    public void save(){
        Vehicle vehicleNew = new Vehicle();
        vehicleNew.setNameCar("name test");
        vehicleNew.setVehicleNumber("bt 456 bt");
        vehicleNew.setYearIssue(2000);
        vehicleNew.setCategory("легковая");
        vehicleNew.setColor("blue");
        vehicleNew.setFuelConsumption(9);

        jdbcVehicleRepositoryImpl.save(vehicleNew);
        log.info("vehicle save {}",vehicleNew);
        vehicleId = vehicleNew.getId();
    }

    @Test
    public void update(){
        save();
        vehicle = jdbcVehicleRepositoryImpl.get(vehicleId);
        vehicle.setNameCar("name test update");
        jdbcVehicleRepositoryImpl.save(vehicle);
        log.info("vehicle update {}",vehicle);
    }

    @Test
    public void get(){
        save();
        vehicle = jdbcVehicleRepositoryImpl.get(vehicleId);
        log.info("vehicle get {}",vehicle);
    }

    @Test
    public void getAll(){
        List<Vehicle> vehicles = jdbcVehicleRepositoryImpl.getAll();
        log.info("vehicle getAll {}", vehicles);
    }

    @Test
    public void getByNameCar(){
        List<Vehicle> vehicles = jdbcVehicleRepositoryImpl.getByNameCar("name test");
        log.info("vehicle getByNameCar {}",vehicles);
    }

    @Test
    public void getByVehicleNumber(){
        List<Vehicle> vehicles = jdbcVehicleRepositoryImpl.getByVehicleNumber("bt 456 bt");
        log.info("vehicle getByVehicleNumber {}", vehicles);
    }
}
