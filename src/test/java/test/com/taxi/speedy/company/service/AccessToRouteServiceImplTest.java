package test.com.taxi.speedy.company.service;

import com.taxi.speedy.company.model.AccessToRoute;
import com.taxi.speedy.company.model.UserState;
import com.taxi.speedy.company.model.UserVehicle;
import com.taxi.speedy.company.model.VehicleState;
import com.taxi.speedy.company.service.AccessToRouteService;
import com.taxi.speedy.company.service.UserStateService;
import com.taxi.speedy.company.service.UserVehicleService;
import com.taxi.speedy.company.service.VehicleStateService;
import com.taxi.speedy.company.service.service_impl.AccessToRouteServiceImpl;
import com.taxi.speedy.company.service.service_impl.UserStateServiceImpl;
import com.taxi.speedy.company.service.service_impl.UserVehicleServiceImpl;
import com.taxi.speedy.company.service.service_impl.VehicleStateServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDateTime;
import java.util.List;

public class AccessToRouteServiceImplTest {
    private Logger log = LoggerFactory.getLogger(getClass());

    private ApplicationContext applicationContext;
    private AccessToRouteService accessToRouteService;
    private UserVehicleService userVehicleService;
    private UserStateService userStateService;
    private VehicleStateService vehicleStateService;

    private AccessToRoute accessToRoute;
    private int idAccessToRoute;

    @Before
    public void setUp(){
        applicationContext = new ClassPathXmlApplicationContext("spring/context.xml");
        accessToRouteService = applicationContext.getBean(AccessToRouteServiceImpl.class);
        userVehicleService = applicationContext.getBean(UserVehicleServiceImpl.class);
        userStateService = applicationContext.getBean(UserStateServiceImpl.class);
        vehicleStateService = applicationContext.getBean(VehicleStateServiceImpl.class);
    }

    @Test
    public void create(){
        UserVehicle userVehicle = userVehicleService.get(1);
        UserState userState = userStateService.get(1);
        VehicleState vehicleState = vehicleStateService.get(1);

        log.info("userVehicle {}",userVehicle);
        log.info("userState {}",userState);
        log.info("vehicleState {}",vehicleState);

        AccessToRoute accessToRoute = new AccessToRoute();
        accessToRoute.setChecksDateTime(LocalDateTime.now());
        accessToRoute.setIdUserVehicle(userVehicle);
        accessToRoute.setIdUserState(userState);
        accessToRoute.setIdVehicleState(vehicleState);
        accessToRoute.setIsAccess(1);       //Допуск: 1 - yes, 0 - no

        accessToRouteService.create(accessToRoute);
        log.info("create {}",accessToRoute);

        idAccessToRoute = accessToRoute.getId();
    }

    @Test
    public void createNot(){
        UserVehicle userVehicle = userVehicleService.get(1);
        UserState userState = userStateService.get(1);
        VehicleState vehicleState = vehicleStateService.get(1);

        log.info("userVehicle {}",userVehicle);
        log.info("userState {}",userState);
        log.info("vehicleState {}",vehicleState);

        AccessToRoute accessToRoute = new AccessToRoute();
        accessToRoute.setChecksDateTime(LocalDateTime.now());
        accessToRoute.setIsAccess(1);       //Допуск: 1 - yes, 0 - no

        accessToRouteService.create(accessToRoute);
        log.info("create {}",accessToRoute);

        idAccessToRoute = accessToRoute.getId();
    }

    @Test
    public void update(){
        create();

        UserVehicle userVehicle = userVehicleService.get(2);
        log.info("userVehicle {}",userVehicle);

        accessToRoute = accessToRouteService.get(idAccessToRoute);
        log.info("get {}",accessToRoute);

        accessToRoute.setIdUserVehicle(userVehicle);
        accessToRouteService.update(accessToRoute);
        log.info("update {}",accessToRoute);
    }

    @Test
    public void updateNot(){
        create();

        UserVehicle userVehicle = null;
        log.info("userVehicle {}",userVehicle);

        accessToRoute = accessToRouteService.get(idAccessToRoute);
        log.info("get {}",accessToRoute);

        accessToRoute.setIdUserVehicle(userVehicle);
        accessToRouteService.update(accessToRoute);
        log.info("updateNot {}",accessToRoute);
    }

    @Test
    public void get(){
        AccessToRoute accessToRoute = accessToRouteService.get(1);
        log.info("get {}",accessToRoute);
    }

    @Test
    public void getNot(){
        AccessToRoute accessToRoute = accessToRouteService.get(0);
        log.info("getNot {}",accessToRoute);
    }

    @Test
    public void delete(){
        create();

        accessToRoute = accessToRouteService.get(idAccessToRoute);
        log.info("get {}",accessToRoute);

        log.info("delete {}",accessToRouteService.delete(accessToRoute.getId()));
    }

    @Test
    public void deleteNot(){
        log.info("deleteNot {}",accessToRouteService.delete(0));
    }

    @Test
    public void getAll(){
        log.info("getAll");

        List<AccessToRoute> accessToRouteList = accessToRouteService.getAll();
        accessToRouteList.forEach(a -> log.info("accessToRoute {}",a));
    }

    @Test
    public void getByUserVehicle(){
        log.info("getByUserVehicle");

        UserVehicle userVehicle = userVehicleService.get(2);
        log.info("userVehicle {}",userVehicle);

        List<AccessToRoute> accessToRouteList = accessToRouteService.getByUserVehicle(userVehicle.getId());
        accessToRouteList.forEach(a -> log.info("accessToRoute {}",a));
    }

    @Test
    public void getByUserVehicleNot(){
        log.info("getByUserVehicleNot");

        UserVehicle userVehicle = userVehicleService.get(0);
        log.info("userVehicle {}",userVehicle);

        List<AccessToRoute> accessToRouteList = accessToRouteService.getByUserVehicle(userVehicle.getId());
        accessToRouteList.forEach(a -> log.info("accessToRoute {}",a));
    }

    @Test
    public void getByUserState(){
        log.info("getByUserState");

        UserState userState = userStateService.get(3);
        log.info("userState {}",userState);

        List<AccessToRoute> accessToRouteList = accessToRouteService.getByUserState(userState.getId());
        accessToRouteList.forEach(a->log.info("accessToRoute {}",a));
    }

    @Test
    public void getByUserStateNot(){
        log.info("getByUserStateNot");

        UserState userState = userStateService.get(0);
        log.info("userState {}",userState);

        List<AccessToRoute> accessToRouteList = accessToRouteService.getByUserState(userState.getId());
        accessToRouteList.forEach(a->log.info("accessToRoute {}",a));
    }

    @Test
    public void getByVehicleState(){
        log.info("getByVehicleState");

        VehicleState vehicleState = vehicleStateService.get(2);
        log.info("vehicleState {}",vehicleState);

        List<AccessToRoute> accessToRouteList = accessToRouteService.getByVehicleState(vehicleState.getId());
        accessToRouteList.forEach(a->log.info("accessToRoute {}",a));
    }

    @Test
    public void getByVehicleStateNot(){
        log.info("getByVehicleStateNot");

        VehicleState vehicleState = vehicleStateService.get(0);
        log.info("vehicleState {}",vehicleState);

        List<AccessToRoute> accessToRouteList = accessToRouteService.getByVehicleState(vehicleState.getId());
        accessToRouteList.forEach(a->log.info("accessToRoute {}",a));
    }

    @Test
    public void getByIsAccess(){
        log.info("getByIsAccess");

        List<AccessToRoute> accessToRouteList = accessToRouteService.getByIsAccess(0); //Допуск: 1 - yes, 0 - no
        accessToRouteList.forEach(a->log.info("accessToRoute {}",a));
    }

    @Test
    public void getByIsAccessNot(){
        log.info("getByIsAccessNot");

        List<AccessToRoute> accessToRouteList = accessToRouteService.getByIsAccess(10); //Допуск: 1 - yes, 0 - no
        accessToRouteList.forEach(a->log.info("accessToRoute {}",a));
    }
}
