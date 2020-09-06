package com.taxi.speedy.company.repository.spring_jdbc;

import com.taxi.speedy.company.model.Role;
import com.taxi.speedy.company.model.User;
import com.taxi.speedy.company.repository.UserRepository;
import com.taxi.speedy.company.repository.spring_jdbc.row_mapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.util.*;

@Repository//("jdbcUserRepositoryImpl")
public class JdbcUserRepositoryImpl implements UserRepository {
    //Статический метод фабрики для создания нового BeanPropertyRowMapper (с отображенным классом,
    // указанным только один раз).
    //private static final RowMapper<User> ROW_MAPPER_USER = BeanPropertyRowMapper.newInstance(User.class);
    private static final BeanPropertyRowMapper<User> ROW_MAPPER_USER = BeanPropertyRowMapper.newInstance(User.class);

    /*
     *  JdbcTemplate - это мощный механизм для подключения к базе данных и выполнения SQL-запросов.
     *  Мы можем выполнять все операции с базой данных с помощью класса JdbcTemplate, такие как вставка,
     *  обновление, удаление и извлечение данных из базы данных.
     */
    private JdbcTemplate jdbcTemplate;
    //способ вставки данных по именованному параметру. Таким образом мы используем имена вместо? (Знак вопроса)
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /*   SimpleJdbcInsert - многопоточный, многоразовый объект, обеспечивающий удобные возможности вставки для таблицы.
       Он обеспечивает обработку метаданных, чтобы упростить код, необходимый для построения основного оператора insert.
               Все, что вам нужно указать, - это имя таблицы и Карта, содержащая имена столбцов и значения столбца.*/
    private SimpleJdbcInsert insertUser;

    @Autowired
    public JdbcUserRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertUser = new SimpleJdbcInsert(dataSource)
                //Укажите имя таблицы, которое будет использоваться для вставки.
                .withTableName("users")
                //Укажите имена любых столбцов, в которых есть автоматически сгенерированные ключи.
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public User save(User user) {
        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id",user.getId()) //Добавьте параметр к этому источнику параметра.
                .addValue("name",user.getName())
                .addValue("email",user.getEmail())
                .addValue("password",user.getPassword())
                .addValue("phone",user.getPhone())
                .addValue("address",user.getAddress())
                .addValue("registered",user.getRegistered())
                .addValue("enabled",user.isEnabled());

/*        BeanPropertySqlParameterSource - анализирует переданный ему объект и для каждого свойства объекта создаёт параметр
        с именем свойства и его значением.*/
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(user);
        if (user.isNew()) {
            //Выполните вставку, используя значения, переданные и возвращающие сгенерированный ключ.
            //Number newKey = insertUser.executeAndReturnKey(parameterSource);
            Number newKey = insertUser.executeAndReturnKey(map);
            user.setId(newKey.intValue());
            insertRoles(user);
        } else {
            if (namedParameterJdbcTemplate.update(
                    "UPDATE users SET name=:name, email=:email, password=:password, phone=:phone, address=:address, " +
                            "registered=:registered, enabled=:enabled WHERE id=:id", parameterSource) == 0) {
                return null;
            }
            // Simplest implementation.
            // More complicated : get user roles from DB and compare them with user.roles (assume that roles are changed rarely).
            // If roles are changed, calculate difference in java and delete/insert them.
            deleteRoles(user);
            insertRoles(user);
        }
        return user;
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM users WHERE id=?",id) != 0;
    }

    @Override
    public User get(int id) {
      /*  String sql = "select * from users where id=:id";
      //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        return namedParameterJdbcTemplate.queryForObject(sql, params, new UserRowMapper());*/

        //Получить список юзеров со всеми ролями которые у них есть
        Map<Integer, Set<Role>> mapUserRoles = getUserRoles();

        //for (Map.Entry<Integer, Set<Role>> entry : mapUserRoles.entrySet())
        //    System.out.println(entry.getKey()+"  " +entry.getValue());
        //mapUserRoles.forEach((k,v) -> System.out.println(k.toString()+" "+v));

        List<User> users = jdbcTemplate.query("SELECT * FROM users WHERE id=?", new UserRowMapper(), id);
        users.forEach(u -> u.setRoles(mapUserRoles.get(u.getId()))); // Добавляем для юзера роли которые получили из таблицы USER_ROLES
        return (User) DataAccessUtils.singleResult(users);  //Верните один объект результата из данной коллекции.
    }

    @Override
    public List<User> getAll() {
        //Получить список юзеров со всеми ролями которые у них есть
        Map<Integer, Set<Role>> mapUserRoles = getUserRoles();
        //for (Map.Entry<Integer, Set<Role>> entry : mapUserRoles.entrySet())
        //    System.out.println(entry.getKey()+"  " +entry.getValue());
        //mapUserRoles.forEach((k,v) -> System.out.println(k.toString()+" "+v));

        List<User> users = jdbcTemplate.query("SELECT * FROM USERS ORDER BY NAME, EMAIL",ROW_MAPPER_USER);
        users.forEach(user -> user.setRoles(mapUserRoles.get(user.getId())));
        return users;
    }

    @Override
    public User getByEmail(String email) {
        //        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE email=?", ROW_MAPPER, email);
/*        List<User> users = jdbcTemplate.query("SELECT * FROM users WHERE email=?", new UserRowMapper(), email);
        return (User) DataAccessUtils.singleResult(users);*/

        //Получить список юзеров со всеми ролями которые у них есть
        Map<Integer, Set<Role>> mapUserRoles = getUserRoles();
        //mapUserRoles.forEach((k,v) -> System.out.println(k.toString()+" "+v));

        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("email", email);
        List<User> users = namedParameterJdbcTemplate.query("SELECT * FROM users WHERE email=:email", params, new UserRowMapper());
        //users.forEach(u -> u.setRoles(mapUserRoles.get(u.getId()))); // Добавляем для юзера роли которые получили из таблицы USER_ROLES
        setRoles(users.get(0));
        return (User) DataAccessUtils.singleResult(users); //Вернёт один объект результата из данной коллекции.
    }

    @Override
    public User getByPhone(String phone) {
        //        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE email=?", ROW_MAPPER, email);
/*        List<User> users = jdbcTemplate.query("SELECT * FROM users WHERE email=?", new UserRowMapper(), email);
        return (User) DataAccessUtils.singleResult(users);*/

        //Получить список юзеров со всеми ролями которые у них есть
        Map<Integer, Set<Role>> mapUserRoles = getUserRoles();
        //mapUserRoles.forEach((k,v)-> System.out.println(k.intValue()+" "+v));

        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("phone",phone);

        List<User> users = namedParameterJdbcTemplate.query("SELECT * FROM USERS WHERE phone=:phone",mapSqlParameterSource,ROW_MAPPER_USER);
        //users.forEach(u -> u.setRoles(mapUserRoles.get(u.getId()))); // Добавляем для юзера роли которые получили из таблицы USER_ROLES
        setRoles(users.get(0));
        return (User) DataAccessUtils.singleResult(users); //Вернёт один объект результата из данной коллекции.
    }

    private void insertRoles(User u){
        Set<Role> roles = u.getRoles();
        if (!CollectionUtils.isEmpty(roles)) {
            jdbcTemplate.batchUpdate("INSERT INTO user_roles (user_id, role) VALUES (?, ?)", roles, roles.size(),
                    (ps, role) -> {
                        ps.setInt(1, u.getId());
                        ps.setString(2, role.name());
                    });
        }
    }

    private boolean deleteRoles(User user){
        return jdbcTemplate.update("DELETE FROM user_roles WHERE user_id=?",user.getId()) != 0;
    }

    //Заполняем юзера ролями которые у него есть
    private User setRoles(User u) {
        if (u != null) {
            List<Role> roles = jdbcTemplate.query("SELECT role FROM user_roles  WHERE user_id=?",
                    (rs, rowNum) -> Role.valueOf(rs.getString("role")), u.getId());
            u.setRoles(roles);
        }
        return u;
    }

    //Получить список юзеров со всеми ролями которые у них есть
    private  Map<Integer, Set<Role>> getUserRoles(){
        Map<Integer, Set<Role>> mapUserRoles = new HashMap<>();
        jdbcTemplate.query("SELECT * FROM USER_ROLES ORDER BY user_id", rs ->{
            //EnumSet.noneOf(Role.class) - получаем пустой список Role
            mapUserRoles.computeIfAbsent(rs.getInt("user_id"),user_id -> EnumSet.noneOf(Role.class))
                    //Добавляем роли к юзеру
                    .add(Role.valueOf(rs.getString("role")));
        });
        //for (Map.Entry<Integer, Set<Role>> entry : mapUserRoles.entrySet())
        //    System.out.println(entry.getKey()+"  " +entry.getValue());
        //mapUserRoles.forEach((k,v) -> System.out.println(k.toString()+" "+v));
        return  mapUserRoles;
    }
}
