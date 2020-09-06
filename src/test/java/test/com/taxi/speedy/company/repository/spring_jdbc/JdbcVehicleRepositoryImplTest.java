package test.com.taxi.speedy.company.repository.spring_jdbc;

import com.taxi.speedy.company.model.Vehicle;
import com.taxi.speedy.company.repository.spring_jdbc.JdbcVehicleRepositoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDateTime;
import java.util.List;

public class JdbcVehicleRepositoryImplTest {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private ApplicationContext context = null;
    private JdbcVehicleRepositoryImpl jdbcVehicleRepositoryImpl = null;

    private int vehicleId;
    private Vehicle vehicle;

    @Before
    public void setUp(){
        context = new ClassPathXmlApplicationContext("spring/context.xml");
        jdbcVehicleRepositoryImpl = context.getBean(JdbcVehicleRepositoryImpl.class);
    }

    @Test
    public void save(){
        Vehicle vehicleNew = new Vehicle();
        vehicleNew.setNameCar(LocalDateTime.now().toLocalTime().toString());
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
        log.info("vehicle update {}",vehicle);
        vehicle.setNameCar(LocalDateTime.now().toLocalTime().toString());
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
    public void delete(){
        save();
        vehicle = jdbcVehicleRepositoryImpl.get(vehicleId);
        log.info("vehicle delete {}", vehicle);
        boolean isDelete = jdbcVehicleRepositoryImpl.delete(vehicle.getId());
        log.info("isDelete {}",isDelete);
    }

    @Test
    public void getAll(){
        List<Vehicle> vehicles = jdbcVehicleRepositoryImpl.getAll();
        log.info("vehicle getAll {}", vehicles);
    }

    @Test
    public void getByNameCar(){
        List<Vehicle> vehicles = jdbcVehicleRepositoryImpl.getByNameCar("Toyota Colora");
        log.info("vehicle getByNameCar {}",vehicles);
    }

    @Test
    public void getByVehicleNumber(){
        List<Vehicle> vehicles = jdbcVehicleRepositoryImpl.getByVehicleNumber("AA3334BQ");
        log.info("vehicle getByVehicleNumber {}", vehicles);
    }
}
