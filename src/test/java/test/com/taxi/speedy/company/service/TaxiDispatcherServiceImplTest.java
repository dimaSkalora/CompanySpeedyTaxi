package test.com.taxi.speedy.company.service;

import com.taxi.speedy.company.model.TaxiDispatcher;
import com.taxi.speedy.company.model.User;
import com.taxi.speedy.company.service.TaxiDispatcherService;
import com.taxi.speedy.company.service.UserService;
import com.taxi.speedy.company.service.service_impl.TaxiDispatcherServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TaxiDispatcherServiceImplTest {
    private Logger log = LoggerFactory.getLogger(getClass());

    private ApplicationContext applicationContext;
    private TaxiDispatcherService taxiDispatcherService;
    private UserService userService;

    private TaxiDispatcher taxiDispatcher;
    private int idTaxiDispatcher;

    @Before
    public void setUp(){
        applicationContext = new ClassPathXmlApplicationContext("/spring/context.xml");
        taxiDispatcherService = applicationContext.getBean(TaxiDispatcherServiceImpl.class);
        userService = (UserService) applicationContext.getBean("userServiceImpl");
    }

    @Test
    public void create(){
        User user = userService.get(100010);
        log.info("user {}",user);

        TaxiDispatcher taxiDispatcher = new TaxiDispatcher();
        taxiDispatcher.setIdUser(user);

        taxiDispatcherService.create(taxiDispatcher);
        log.info("create {}",taxiDispatcher);
        idTaxiDispatcher = taxiDispatcher.getId();
    }

    @Test
    public void createNot(){
        User user = null;//userRepository.get(100010);
        log.info("user {}",user);

        TaxiDispatcher taxiDispatcher = new TaxiDispatcher();
        taxiDispatcher.setIdUser(user);

        taxiDispatcherService.create(taxiDispatcher);
        log.info("createNot {}",taxiDispatcher);
    }

    @Test
    public void update(){
        create();
        TaxiDispatcher taxiDispatcher = taxiDispatcherService.get(idTaxiDispatcher);
        log.info("taxiDispatcher {}",taxiDispatcher);

        User user = userService.get(100011);
        log.info("user {}",user);

        taxiDispatcher.setIdUser(user);

        taxiDispatcherService.update(taxiDispatcher);
        log.info("update {}",taxiDispatcher);
    }

    @Test
    public void updateNot(){
        create();
        TaxiDispatcher taxiDispatcher = taxiDispatcherService.get(idTaxiDispatcher);
        log.info("taxiDispatcher {}",taxiDispatcher);

        User user = null;
        log.info("user {}",user);

        taxiDispatcher.setIdUser(user);

        taxiDispatcherService.update(taxiDispatcher);
        log.info("updateNot {}",taxiDispatcher);
    }

    @Test
    public void get(){
        create();
        TaxiDispatcher taxiDispatcher = taxiDispatcherService.get(idTaxiDispatcher);
        log.info("get {}",taxiDispatcher);
    }

    @Test
    public void getNot(){
        TaxiDispatcher taxiDispatcher = taxiDispatcherService.get(0);
        log.info("getNot {}",taxiDispatcher);
    }

    @Test
    public void getAll(){
        List<TaxiDispatcher> taxiDispatcherList = taxiDispatcherService.getAll();
        taxiDispatcherList.forEach(td -> log.info("getAll {}",td));
    }


}
