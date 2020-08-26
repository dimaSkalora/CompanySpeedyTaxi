package test.com.taxi.speedy.company.repository.spring_jdbc;

import com.taxi.speedy.company.model.TaxiDispatcher;
import com.taxi.speedy.company.model.User;
import com.taxi.speedy.company.repository.TaxiDispatcherRepository;
import com.taxi.speedy.company.repository.UserRepository;
import com.taxi.speedy.company.repository.spring_jdbc.JDBCUserRepositoryImpl;
import com.taxi.speedy.company.repository.spring_jdbc.JdbcTaxiDispatcherRepositoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class JdbcTaxiDispatcherRepositoryTest {
    private Logger log = LoggerFactory.getLogger(getClass());

    private ApplicationContext applicationContext;
    private TaxiDispatcherRepository taxiDispatcherRepository;
    private UserRepository userRepository;

    private TaxiDispatcher taxiDispatcher;
    private int idTaxiDispatcher;

    @Before
    public void setUp(){
        applicationContext = new ClassPathXmlApplicationContext("/spring/context.xml");
        taxiDispatcherRepository = (JdbcTaxiDispatcherRepositoryImpl) applicationContext.getBean("jdbcTaxiDispatcherRepositoryImpl");
        userRepository = (JDBCUserRepositoryImpl)applicationContext.getBean("JDBCUserRepositoryImpl");
    }

    @Test
    public void create(){
        User user = userRepository.get(100010);
        log.info("user {}",user);

        TaxiDispatcher taxiDispatcher = new TaxiDispatcher();
        taxiDispatcher.setIdUser(user);

        taxiDispatcherRepository.save(taxiDispatcher);
        log.info("create {}",taxiDispatcher);
        idTaxiDispatcher = taxiDispatcher.getId();
    }

    @Test
    public void createNot(){
        User user = null;//userRepository.get(100010);
        log.info("user {}",user);

        TaxiDispatcher taxiDispatcher = new TaxiDispatcher();
        taxiDispatcher.setIdUser(user);

        taxiDispatcherRepository.save(taxiDispatcher);
        log.info("createNot {}",taxiDispatcher);
    }

    @Test
    public void update(){
        create();
        TaxiDispatcher taxiDispatcher = taxiDispatcherRepository.get(idTaxiDispatcher);
        log.info("taxiDispatcher {}",taxiDispatcher);

        User user = userRepository.get(100011);
        log.info("user {}",user);

        taxiDispatcher.setIdUser(user);

        taxiDispatcherRepository.save(taxiDispatcher);
        log.info("update {}",taxiDispatcher);
    }

    @Test
    public void updateNot(){
        create();
        TaxiDispatcher taxiDispatcher = taxiDispatcherRepository.get(idTaxiDispatcher);
        log.info("taxiDispatcher {}",taxiDispatcher);

        User user = null;
        log.info("user {}",user);

        taxiDispatcher.setIdUser(user);

        taxiDispatcherRepository.save(taxiDispatcher);
        log.info("updateNot {}",taxiDispatcher);
    }

    @Test
    public void get(){
        create();
        TaxiDispatcher taxiDispatcher = taxiDispatcherRepository.get(idTaxiDispatcher);
        log.info("get {}",taxiDispatcher);
    }

    @Test
    public void delete(){
        create();
        TaxiDispatcher taxiDispatcher = taxiDispatcherRepository.get(idTaxiDispatcher);
        log.info("delete {}",taxiDispatcherRepository.delete(taxiDispatcher.getId()));
    }

    @Test
    public void deleteNot(){
        log.info("delete {}",taxiDispatcherRepository.delete(0));
    }

    @Test
    public void getNot(){
        TaxiDispatcher taxiDispatcher = taxiDispatcherRepository.get(0);
        log.info("getNot {}",taxiDispatcher);
    }

    @Test
    public void getAll(){
        List<TaxiDispatcher> taxiDispatcherList = taxiDispatcherRepository.getAll();
        taxiDispatcherList.forEach(td -> log.info("getAll {}",td));
    }

}
