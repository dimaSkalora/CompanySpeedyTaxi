package test.com.taxi.speedy.company.repository.spring_jdbc;

import com.taxi.speedy.company.model.User;
import com.taxi.speedy.company.model.UserVehicle;
import com.taxi.speedy.company.model.Vehicle;
import com.taxi.speedy.company.repository.UserRepository;
import com.taxi.speedy.company.repository.UserVehicleRepository;
import com.taxi.speedy.company.repository.VehicleRepository;
import com.taxi.speedy.company.repository.spring_jdbc.JDBCUserRepositoryImpl;
import com.taxi.speedy.company.repository.spring_jdbc.JDBCVehicleRepositoryImpl;
import com.taxi.speedy.company.repository.spring_jdbc.JdbcUserVehiclesRepositoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDateTime;
import java.util.List;

public class JdbcUserVehiclesRepositoryImplTest {

    private static Logger logger = LoggerFactory.getLogger(JdbcUserVehiclesRepositoryImplTest.class);

    private ApplicationContext applicationContext;
    private UserVehicleRepository userVehicleRepository;
    private UserRepository userRepository;
    private VehicleRepository vehicleRepository;

    private UserVehicle userVehicle;
    private int idUserVehicle;

    @Before
    public void setUp(){
        applicationContext = new ClassPathXmlApplicationContext("spring/context.xml");
        userVehicleRepository = (UserVehicleRepository)applicationContext.getBean(JdbcUserVehiclesRepositoryImpl.class);
        userRepository = applicationContext.getBean(JDBCUserRepositoryImpl.class);
        vehicleRepository = applicationContext.getBean(JDBCVehicleRepositoryImpl.class);
    }

    @Test
    public void create(){
        User user = userRepository.get(100012);
        Vehicle vehicle = vehicleRepository.get(5);

        logger.info("user {}",user);
        logger.info("vehicle {}",vehicle);

        UserVehicle newUserVehicle = new UserVehicle();
        newUserVehicle.setStartDate(LocalDateTime.now());
        //newUserVehicle.setEndDate(LocalDateTime.now().plusDays(5));
        newUserVehicle.setIdUser(user);
        newUserVehicle.setIdVehicle(vehicle);
        newUserVehicle.setIsCurrentUserMachine(0);

        userVehicleRepository.save(newUserVehicle);

        logger.info("create {}",newUserVehicle);
        idUserVehicle = newUserVehicle.getId();
    }

    @Test
    public void update(){
        //create();
/*        userVehicle = userVehicleRepository.get(100033);
        logger.info("get userVehicle {}",userVehicle);*/
        List<UserVehicle> userVehicles = userVehicleRepository.getAllByUser(100012);
        logger.info("get userVehicles {}",userVehicles);
/*        userVehicle.setEndDate(LocalDateTime.now());
        userVehicle.setIsCurrentUserMachine(1);

        userVehicleRepository.save(userVehicle);
        logger.info("update {}",userVehicle);*/
    }

}
