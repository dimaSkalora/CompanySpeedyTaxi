package test.com.taxi.speedy.company.repository.spring_jdbc;

import com.taxi.speedy.company.model.AccessToRoute;
import com.taxi.speedy.company.model.UserState;
import com.taxi.speedy.company.model.UserVehicle;
import com.taxi.speedy.company.model.VehicleState;
import com.taxi.speedy.company.repository.AccessToRouteRepository;
import com.taxi.speedy.company.repository.UserStateRepository;
import com.taxi.speedy.company.repository.UserVehicleRepository;
import com.taxi.speedy.company.repository.VehicleStateRepository;
import com.taxi.speedy.company.repository.spring_jdbc.JdbcAccessToRouteRepositoryImpl;
import com.taxi.speedy.company.repository.spring_jdbc.JdbcUserStateRepositoryImpl;
import com.taxi.speedy.company.repository.spring_jdbc.JdbcUserVehiclesRepositoryImpl;
import com.taxi.speedy.company.repository.spring_jdbc.JdbcVehicleStateRepositoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDateTime;
import java.util.List;

public class JdbcAccessToRouteRepositoryImplTest {
    private Logger log = LoggerFactory.getLogger(getClass());

    private ApplicationContext applicationContext;
    private AccessToRouteRepository accessToRouteRepository;
    private UserVehicleRepository userVehicleRepository;
    private UserStateRepository userStateRepository;
    private VehicleStateRepository  vehicleStateRepository;

    private AccessToRoute accessToRoute;
    private int idAccessToRoute;

    @Before
    public void setUp(){
        applicationContext = new ClassPathXmlApplicationContext("spring/context.xml");
        accessToRouteRepository = applicationContext.getBean(JdbcAccessToRouteRepositoryImpl.class);
        userVehicleRepository = applicationContext.getBean(JdbcUserVehiclesRepositoryImpl.class);
        userStateRepository = applicationContext.getBean(JdbcUserStateRepositoryImpl.class);
        vehicleStateRepository = applicationContext.getBean(JdbcVehicleStateRepositoryImpl.class);
    }

    @Test
    public void create(){
        UserVehicle userVehicle = userVehicleRepository.get(1);
        UserState userState = userStateRepository.get(1);
        VehicleState vehicleState = vehicleStateRepository.get(1);

        log.info("userVehicle {}",userVehicle);
        log.info("userState {}",userState);
        log.info("vehicleState {}",vehicleState);

        AccessToRoute accessToRoute = new AccessToRoute();
        accessToRoute.setChecksDateTime(LocalDateTime.now());
        accessToRoute.setIdUserVehicle(userVehicle);
        accessToRoute.setIdUserState(userState);
        accessToRoute.setIdVehicleState(vehicleState);
        accessToRoute.setIsAccess(1);       //Допуск: 1 - yes, 0 - no

        accessToRouteRepository.save(accessToRoute);
        log.info("create {}",accessToRoute);

        idAccessToRoute = accessToRoute.getId();
    }

    @Test
    public void createNot(){
        UserVehicle userVehicle = userVehicleRepository.get(1);
        UserState userState = userStateRepository.get(1);
        VehicleState vehicleState = vehicleStateRepository.get(1);

        log.info("userVehicle {}",userVehicle);
        log.info("userState {}",userState);
        log.info("vehicleState {}",vehicleState);

        AccessToRoute accessToRoute = new AccessToRoute();
        accessToRoute.setChecksDateTime(LocalDateTime.now());
        accessToRoute.setIsAccess(1);       //Допуск: 1 - yes, 0 - no

        accessToRouteRepository.save(accessToRoute);
        log.info("create {}",accessToRoute);

        idAccessToRoute = accessToRoute.getId();
    }

    @Test
    public void update(){
        create();

        UserVehicle userVehicle = userVehicleRepository.get(2);
        log.info("userVehicle {}",userVehicle);

        accessToRoute = accessToRouteRepository.get(idAccessToRoute);
        log.info("get {}",accessToRoute);

        accessToRoute.setIdUserVehicle(userVehicle);
        accessToRouteRepository.save(accessToRoute);
        log.info("update {}",accessToRoute);
    }

    @Test
    public void updateNot(){
        create();

        UserVehicle userVehicle = null;
        log.info("userVehicle {}",userVehicle);

        accessToRoute = accessToRouteRepository.get(idAccessToRoute);
        log.info("get {}",accessToRoute);

        accessToRoute.setIdUserVehicle(userVehicle);
        accessToRouteRepository.save(accessToRoute);
        log.info("updateNot {}",accessToRoute);
    }

    @Test
    public void get(){
        AccessToRoute accessToRoute = accessToRouteRepository.get(1);
        log.info("get {}",accessToRoute);
    }

    @Test
    public void getNot(){
        AccessToRoute accessToRoute = accessToRouteRepository.get(0);
        log.info("getNot {}",accessToRoute);
    }

    @Test
    public void delete(){
       create();

       accessToRoute = accessToRouteRepository.get(idAccessToRoute);
       log.info("get {}",accessToRoute);

       log.info("delete {}",accessToRouteRepository.delete(accessToRoute.getId()));
    }

    @Test
    public void deleteNot(){
        log.info("deleteNot {}",accessToRouteRepository.delete(0));
    }

    @Test
    public void getAll(){
        log.info("getAll");

        List<AccessToRoute> accessToRouteList = accessToRouteRepository.getAll();
        accessToRouteList.forEach(a -> log.info("accessToRoute {}",a));
    }

    @Test
    public void getByUserVehicle(){
        log.info("getByUserVehicle");

        UserVehicle userVehicle = userVehicleRepository.get(2);
        log.info("userVehicle {}",userVehicle);

        List<AccessToRoute> accessToRouteList = accessToRouteRepository.getByUserVehicle(userVehicle.getId());
        accessToRouteList.forEach(a -> log.info("accessToRoute {}",a));
    }

    @Test
    public void getByUserVehicleNot(){
        log.info("getByUserVehicleNot");

        UserVehicle userVehicle = userVehicleRepository.get(0);
        log.info("userVehicle {}",userVehicle);

        List<AccessToRoute> accessToRouteList = accessToRouteRepository.getByUserVehicle(userVehicle.getId());
        accessToRouteList.forEach(a -> log.info("accessToRoute {}",a));
    }

    @Test
    public void getByUserState(){
        log.info("getByUserState");

        UserState userState = userStateRepository.get(3);
        log.info("userState {}",userState);

        List<AccessToRoute> accessToRouteList = accessToRouteRepository.getByUserState(userState.getId());
        accessToRouteList.forEach(a->log.info("accessToRoute {}",a));
    }

    @Test
    public void getByUserStateNot(){
        log.info("getByUserStateNot");

        UserState userState = userStateRepository.get(0);
        log.info("userState {}",userState);

        List<AccessToRoute> accessToRouteList = accessToRouteRepository.getByUserState(userState.getId());
        accessToRouteList.forEach(a->log.info("accessToRoute {}",a));
    }

    @Test
    public void getByVehicleState(){
        log.info("getByVehicleState");

        VehicleState vehicleState = vehicleStateRepository.get(2);
        log.info("vehicleState {}",vehicleState);

        List<AccessToRoute> accessToRouteList = accessToRouteRepository.getByVehicleState(vehicleState.getId());
        accessToRouteList.forEach(a->log.info("accessToRoute {}",a));
    }

    @Test
    public void getByVehicleStateNot(){
        log.info("getByVehicleStateNot");

        VehicleState vehicleState = vehicleStateRepository.get(0);
        log.info("vehicleState {}",vehicleState);

        List<AccessToRoute> accessToRouteList = accessToRouteRepository.getByVehicleState(vehicleState.getId());
        accessToRouteList.forEach(a->log.info("accessToRoute {}",a));
    }

    @Test
    public void getByIsAccess(){
        log.info("getByIsAccess");

        List<AccessToRoute> accessToRouteList = accessToRouteRepository.getByIsAccess(0); //Допуск: 1 - yes, 0 - no
        accessToRouteList.forEach(a->log.info("accessToRoute {}",a));
    }

    @Test
    public void getByIsAccessNot(){
        log.info("getByIsAccessNot");

        List<AccessToRoute> accessToRouteList = accessToRouteRepository.getByIsAccess(10); //Допуск: 1 - yes, 0 - no
        accessToRouteList.forEach(a->log.info("accessToRoute {}",a));
    }
}
