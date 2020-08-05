package test.com.taxi.speedy.company.service;

import com.taxi.speedy.company.model.UserState;
import com.taxi.speedy.company.service.UserStateService;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserStateServiceImplTest {
    private Logger logger = LoggerFactory.getLogger(UserStateServiceImplTest.class);

    private ApplicationContext applicationContext;
    private UserStateService userStateService;

    private UserState userState;
    private int idUserState;

    @Before
    public void setUp(){
        applicationContext = new ClassPathXmlApplicationContext("spring/context.xml");
        userStateService = (UserStateService) applicationContext.getBean("userStateServiceImpl");
    }

    @Test
    public void create(){
        UserState userStateNew = new UserState();
        userStateNew.setNameUS("Test");

        userStateService.create(userStateNew);
        logger.info("create {}",userStateNew);

        idUserState = userStateNew.getId();
    }

    @Test
    public void createNull(){
        UserState userState = null;
        userStateService.create(userState);
    }

    @Test
    public void get(){
        create();
        userState = userStateService.get(idUserState);
        logger.info("get {}",userState);
    }

    @Test
    public void getNotId(){
        userState = userStateService.get(0);
        logger.info("get {}",userState);
    }

    @Test
    public void update(){
        create();
        userState = userStateService.get(idUserState);
        logger.info("get {}",userState);

        userState.setNameUS("Test update");
        userStateService.create(userState);
        logger.info("update {}",userState);
    }

    @Test
    public void updateNot(){
        UserState userState = null;
        userStateService.update(userState);
    }

    @Test
    public void delete(){
        create();
        userState = userStateService.get(idUserState);
        logger.info("get {}",userState);

        logger.info("delete {}",userStateService.delete(userState.getId()));
    }

    @Test
    public void deleteNot(){
        logger.info("delete {}",userStateService.delete(0));
    }

    @Test
    public void getByNameUS(){
        userState = userStateService.getByNameUS("Здоровый");
        logger.info("getByNameUS {}",userState);
    }

    @Test
    public void getByNameUSNot(){
        userState = userStateService.getByNameUS("not");
        logger.info("getByNameUS {}",userState);
    }
}
