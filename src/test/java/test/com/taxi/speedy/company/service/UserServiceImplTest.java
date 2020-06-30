package test.com.taxi.speedy.company.service;

import com.taxi.speedy.company.model.Role;
import com.taxi.speedy.company.model.User;
import com.taxi.speedy.company.service.UserService;
import com.taxi.speedy.company.service.service_impl.UserServiceImpl;
import com.taxi.speedy.company.util.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static com.taxi.speedy.company.model.Role.ROLE_USER;
import static org.junit.Assert.*;

public class UserServiceImplTest {

    private ApplicationContext applicationContext = null;
    private UserService userService = null;

    private static Logger logger = LoggerFactory.getLogger(UserServiceImplTest.class);

    private int userId = Integer.MIN_VALUE;
    private User user;

    @Before
    public void setUp() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("spring/context.xml");
        userService = (UserServiceImpl) applicationContext.getBean("userServiceImpl");
    }

    @Test
    public void create() {
        HashSet<Role> roles = new HashSet<>();
        roles.add(ROLE_USER);

        User userNew = new User();
        userNew.setName("Test user");
        userNew.setPassword("Test password");
        userNew.setAddress("Test address");
        userNew.setPhone(""+ LocalTime.now().toSecondOfDay());
        userNew.setEmail("Test"+ LocalTime.now().toSecondOfDay()+"@email.com");
        userNew.setEnabled(true);
        userNew.setRegistered(new Date());
        userNew.setRoles(roles);

        String strUserId = (userNew.getId() == null)? "null" : userNew.getId().toString();
        logger.info("userNew getId- "+strUserId);
        userService.create(userNew);
        logger.info("userNew - "+userNew.toString());
        userId =  userNew.getId();
    }

    @Test
    public void createNull(){
        User userNull = null;
        userService.create(userNull);
    }

    @Test
    public void update() {
        create();
        user = userService.get(userId);
        logger.info(user.toString());
        user.setName("Test user update");
        userService.update(user);
        logger.info(user.toString());
    }

    @Test
    public void delete() {
        create();
        user = userService.get(userId);
        logger.info(user.toString());
        logger.info(String.valueOf(userService.delete(user.getId())));
    }

    @Test
    public void get() {
        user = userService.get(100001);
        logger.info(user.toString());
    }

    @Test
    public void getByEmail() {
        user = userService.getByEmail("dispatcher1@gmail.com");
        logger.info(user.toString());
    }

    @Test
    public void getByPhone() {
        user = userService.getByPhone("+380-00-11-00-132");
        logger.info(user.toString());
    }

    @Test
    public void getAll() {
        List<User> userList = userService.getAll();
        //logger.info(userList.toString());
        userList.forEach(u -> logger.info(u.toString()));
    }

    @Test
    public void setUserEnable() {
        create();
        user = userService.get(userId);
        logger.info(user.getId() +" "+user.getName()+" "+user.getEmail()+" "+user.isEnabled());
        userService.setUserEnable(user.getId(),false);
        user = userService.get(userId);
        logger.info(user.getId() +" "+user.getName()+" "+user.getEmail()+" "+user.isEnabled());
    }
}