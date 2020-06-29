package test.com.taxi.speedy.company.repository.spring_jdbc;

import com.taxi.speedy.company.model.Role;
import com.taxi.speedy.company.model.User;
import com.taxi.speedy.company.repository.spring_jdbc.JDBCUserRepositoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static com.taxi.speedy.company.model.Role.ROLE_USER;

public class JDBCUserRepositoryImplTest {
    private ApplicationContext context = null;
    private JDBCUserRepositoryImpl jdbcUserRepository = null;

    private int userId;
    private User user;

    @Before
    public void setUp(){
        context = new ClassPathXmlApplicationContext("spring/context.xml");
        jdbcUserRepository = (JDBCUserRepositoryImpl) context.getBean("JDBCUserRepositoryImpl");
    }

    @Test
    public void save(){
        HashSet<Role> roles = new HashSet<>();
        roles.add(ROLE_USER);

        User userNew = new User();
        userNew.setName("Test user");
        userNew.setPassword("Test password");
        userNew.setAddress("Test address");
        userNew.setPhone(""+LocalTime.now().toSecondOfDay());
        userNew.setEmail("Test"+ LocalTime.now().toSecondOfDay()+"@email.com");
        userNew.setEnabled(true);
        userNew.setRegistered(new Date());
        userNew.setRoles(roles);

        System.out.println(userNew.getId());
        jdbcUserRepository.save(userNew);
        System.out.println(userNew);
        userId =  userNew.getId();
    }

    @Test
    public void update(){
        save();
        user = jdbcUserRepository.get(userId);
        System.out.println(user);
        user.setName("Test user update");
        jdbcUserRepository.save(user);
        System.out.println(user);
    }

    @Test
    public void delete(){
        save();
        jdbcUserRepository.delete(userId);
    }

    @Test
    public void get(){
        User user100000 = jdbcUserRepository.get(100000);
        System.out.println(user100000);
        User user100012 = jdbcUserRepository.get(100012);
        System.out.println(user100012);
    }

    @Test
    public void getByEmail(){
        User userEmail = jdbcUserRepository.getByEmail("Test@email.com");
        System.out.println(userEmail);
    }

    @Test
    public void getAll(){
        List<User> userList = jdbcUserRepository.getAll();
        userList.forEach(System.out::println);
    }

    @Test
    public void getByPhone(){
        User userPhone = jdbcUserRepository.getByPhone("+380-00-11-00-126");
        System.out.println(userPhone);
    }
}
