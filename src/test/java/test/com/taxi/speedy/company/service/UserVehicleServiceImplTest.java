package test.com.taxi.speedy.company.service;

import com.taxi.speedy.company.model.User;
import com.taxi.speedy.company.model.UserVehicle;
import com.taxi.speedy.company.model.Vehicle;
import com.taxi.speedy.company.service.UserService;
import com.taxi.speedy.company.service.UserVehicleService;
import com.taxi.speedy.company.service.VehicleService;
import com.taxi.speedy.company.service.service_impl.UserServiceImpl;
import com.taxi.speedy.company.service.service_impl.UserVehicleServiceImpl;
import com.taxi.speedy.company.service.service_impl.VehicleServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class UserVehicleServiceImplTest {
    private Logger logger = LoggerFactory.getLogger(UserVehicleServiceImplTest.class);

    private ApplicationContext applicationContext;
    private UserVehicleService userVehicleService;
    private UserService userService;
    private VehicleService vehicleService;

    private UserVehicle userVehicle;
    private int idUserVehicle;

    @Before
    public void setUp(){
        applicationContext = new ClassPathXmlApplicationContext("spring/context.xml");
        userVehicleService = (UserVehicleService) applicationContext.getBean("userVehicleServiceImpl");
        //userVehicleService =  applicationContext.getBean(UserVehicleServiceImpl.class);
        userService = applicationContext.getBean(UserServiceImpl.class);
        vehicleService = applicationContext.getBean(VehicleServiceImpl.class);
    }

    @Test
    public void create(){
        User user = userService.get(100010);
        Vehicle vehicle = vehicleService.get(5);

        logger.info("user {}",user);
        logger.info("vehicle {}",vehicle);

        UserVehicle newUserVehicle = new UserVehicle();
        newUserVehicle.setStartDate(LocalDateTime.now());
        //newUserVehicle.setEndDate(LocalDateTime.now().plusDays(5));
        newUserVehicle.setIdUser(user);
        newUserVehicle.setIdVehicle(vehicle);
        newUserVehicle.setIsCurrentUserMachine(0);

        userVehicleService.create(newUserVehicle);

        logger.info("create {}",newUserVehicle);
        idUserVehicle = newUserVehicle.getId();
    }

    @Test
    public void update(){
        create();
        userVehicle = userVehicleService.get(idUserVehicle);
        logger.info("get userVehicle {}",userVehicle);
        userVehicle.setEndDate(LocalDateTime.now().plusDays(5));
        userVehicle.setIsCurrentUserMachine(1);

        userVehicleService.update(userVehicle);
        logger.info("update {}",userVehicle);
    }

    @Test
    public void get(){
        create();
        userVehicle = userVehicleService.get(idUserVehicle);
        logger.info("get userVehicle {}",userVehicle);
    }

    @Test
    public void delete(){
        create();
        userVehicle = userVehicleService.get(idUserVehicle);
        logger.info("get userVehicle {}",userVehicle);
        boolean isDelete = userVehicleService.delete(userVehicle.getId());
        logger.info("delete userVehicle {}",isDelete);
    }

    @Test
    public void getAll(){
        List<UserVehicle> userVehicles = userVehicleService.getAll();
        logger.info("userVehicles {}",userVehicles);
        //userVehicles.forEach(uv -> logger.info("userVehicle {}",uv));
    }

    @Test
    public void getAllByUser(){
        User user = userService.get(100010);
        List<UserVehicle> userVehicles = userVehicleService.getAllByUser(user.getId());
        //logger.info("getAllByUser {}", userVehicles);
        userVehicles.forEach(uv -> logger.info("getAllByUser userVehicle {}",uv));
    }

    @Test
    public void getAllByVehicle(){
        Vehicle vehicle = vehicleService.get(4);
        List<UserVehicle> userVehicles = userVehicleService.getAllByVehicle(vehicle.getId());
        //logger.info("getAllByVehicle {}", userVehicles);
        userVehicles.forEach(uv -> logger.info(" getAllByVehicle userVehicle {}",uv));
    }

    @Test
    public void getByIsCurrentUserMachine(){
        List<UserVehicle> userVehicles = userVehicleService.getByIsCurrentUserMachine(1);
        //logger.info("getByIsCurrentUserMachine {}",userVehicles);
        userVehicles.forEach(uv -> logger.info("getByIsCurrentUserMachine uv {}",uv));
    }

    @Test
    public void getStartDateBetween(){
        LocalDate start = LocalDate.of(2020,06,01);
        LocalDate end = LocalDate.of(2020,07,13);
        List<UserVehicle> userVehicles = userVehicleService.getStartDateBetween(start,end);
        //logger.info("getStartDateBetween {}",userVehicles);
        userVehicles.forEach(uv -> logger.info("getStartDateBetween uv {}",uv));
    }

    @Test
    public void getEndDateBetween(){
        LocalDate start = LocalDate.of(2020,06,01);
        LocalDate end = LocalDate.of(2020,07,20);
        List<UserVehicle> userVehicles = userVehicleService.getEndDateBetween(start,end);
        //logger.info("getEndDateBetween {}",userVehicles);
        userVehicles.forEach(uv->logger.info("getEndDateBetween uv {}",uv));
    }

}
