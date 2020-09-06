package test.com.taxi.speedy.company.repository.spring_jdbc;

import com.taxi.speedy.company.model.User;
import com.taxi.speedy.company.model.UserVehicle;
import com.taxi.speedy.company.model.Vehicle;
import com.taxi.speedy.company.repository.UserRepository;
import com.taxi.speedy.company.repository.UserVehicleRepository;
import com.taxi.speedy.company.repository.VehicleRepository;
import com.taxi.speedy.company.repository.spring_jdbc.JdbcUserRepositoryImpl;
import com.taxi.speedy.company.repository.spring_jdbc.JdbcVehicleRepositoryImpl;
import com.taxi.speedy.company.repository.spring_jdbc.JdbcUserVehiclesRepositoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;
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
        userRepository = applicationContext.getBean(JdbcUserRepositoryImpl.class);
        vehicleRepository = applicationContext.getBean(JdbcVehicleRepositoryImpl.class);
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
        create();
        userVehicle = userVehicleRepository.get(idUserVehicle);
        logger.info("get userVehicle {}",userVehicle);
        userVehicle.setEndDate(LocalDateTime.now().plusDays(5));
        userVehicle.setIsCurrentUserMachine(1);

        userVehicleRepository.save(userVehicle);
        logger.info("update {}",userVehicle);
    }

    @Test
    public void get(){
        create();
        userVehicle = userVehicleRepository.get(idUserVehicle);
        logger.info("get userVehicle {}",userVehicle);
    }

    @Test
    public void delete(){
        create();
        userVehicle = userVehicleRepository.get(idUserVehicle);
        logger.info("get userVehicle {}",userVehicle);
        boolean isDelete = userVehicleRepository.delete(userVehicle.getId());
        logger.info("delete userVehicle {}",isDelete);
    }

    @Test
    public void getAll(){
        List<UserVehicle> userVehicles = userVehicleRepository.getAll();
        logger.info("userVehicles {}",userVehicles);
        //userVehicles.forEach(uv -> logger.info("userVehicle {}",uv));
    }

    @Test
    public void getAllByUser(){
        User user = userRepository.get(100012);
        List<UserVehicle> userVehicles = userVehicleRepository.getAllByUser(user.getId());
        //logger.info("getAllByUser {}", userVehicles);
        userVehicles.forEach(uv -> logger.info("getAllByUser userVehicle {}",uv));
    }

    @Test
    public void getAllByVehicle(){
        Vehicle vehicle = vehicleRepository.get(4);
        List<UserVehicle> userVehicles = userVehicleRepository.getAllByVehicle(vehicle.getId());
        //logger.info("getAllByVehicle {}", userVehicles);
        userVehicles.forEach(uv -> logger.info(" getAllByVehicle userVehicle {}",uv));
    }

    @Test
    public void getByIsCurrentUserMachine(){
        List<UserVehicle> userVehicles = userVehicleRepository.getByIsCurrentUserMachine(1);
        //logger.info("getByIsCurrentUserMachine {}",userVehicles);
        userVehicles.forEach(uv -> logger.info("getByIsCurrentUserMachine uv {}",uv));
    }

    @Test
    public void getStartDateBetween(){
        LocalDate start = LocalDate.of(2020,06,01);
        LocalDate end = LocalDate.of(2020,07,13);
        List<UserVehicle> userVehicles = userVehicleRepository.getStartDateBetween(start,end);
        //logger.info("getStartDateBetween {}",userVehicles);
        userVehicles.forEach(uv -> logger.info("getStartDateBetween uv {}",uv));
    }

    @Test
    public void getEndDateBetween(){
        LocalDate start = LocalDate.of(2020,06,01);
        LocalDate end = LocalDate.of(2020,07,20);
        List<UserVehicle> userVehicles = userVehicleRepository.getEndDateBetween(start,end);
        //logger.info("getEndDateBetween {}",userVehicles);
        userVehicles.forEach(uv->logger.info("getEndDateBetween uv {}",uv));
    }

}
