package test.com.taxi.speedy.company.repository.spring_jdbc;

import com.taxi.speedy.company.model.UserState;
import com.taxi.speedy.company.repository.spring_jdbc.JdbcUserStateRepositoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JdbcUserStateRepositoryImplTest {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private ApplicationContext applicationContext;
    private JdbcUserStateRepositoryImpl jdbcUserStateRepositoryImpl = null;

    private UserState userState;
    private int idUserState;

    @Before
    public void setUp(){
        applicationContext = new ClassPathXmlApplicationContext("spring/context.xml");
        jdbcUserStateRepositoryImpl = applicationContext.getBean(JdbcUserStateRepositoryImpl.class);
    }

    @Test
    public void create(){
        UserState userStateNew = new UserState();
        userStateNew.setNameUS("Test");

        jdbcUserStateRepositoryImpl.save(userStateNew);
        log.info("create {}",userStateNew);

        idUserState = userStateNew.getId();
    }

    @Test
    public void get(){
        userState = jdbcUserStateRepositoryImpl.get(2);
        log.info("get {}",userState);
    }






}
