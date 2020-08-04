package com.taxi.speedy.company.repository.spring_jdbc;

import com.taxi.speedy.company.model.UserState;
import com.taxi.speedy.company.repository.UserStateRepository;
import com.taxi.speedy.company.repository.spring_jdbc.row_mapper.UserStateRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository//("jdbcUserStateRepositoryImpl")
public class JdbcUserStateRepositoryImpl implements UserStateRepository {

    //Статический метод фабрики для создания нового BeanPropertyRowMapper (с отображенным классом,
    // указанным только один раз).
    //private static final RowMapper<Vehicle> ROW_MAPPER_USER_STATE = BeanPropertyRowMapper.newInstance(Vehicle.class);
    private static BeanPropertyRowMapper<UserState> ROW_MAPPER_USER_STATE = BeanPropertyRowMapper.newInstance(UserState.class);

    /*
     *  JdbcTemplate - это мощный механизм для подключения к базе данных и выполнения SQL-запросов.
     *  Мы можем выполнять все операции с базой данных с помощью класса JdbcTemplate, такие как вставка,
     *  обновление, удаление и извлечение данных из базы данных.
     */
    private JdbcTemplate jdbcTemplate;
    //способ вставки данных по именованному параметру. Таким образом мы используем имена вместо? (Знак вопроса)
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    /* SimpleJdbcInsert - многопоточный, многоразовый объект, обеспечивающий удобные возможности вставки для таблицы.
       Он обеспечивает обработку метаданных, чтобы упростить код, необходимый для построения основного оператора insert.
       Все, что вам нужно указать, - это имя таблицы и Карта, содержащая имена столбцов и значения столбца.*/
    private SimpleJdbcInsert jdbcInsert;

    @Autowired
    public JdbcUserStateRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        jdbcInsert = new SimpleJdbcInsert(dataSource)
                    //Укажите имя таблицы, которое будет использоваться для вставки.
                    .withTableName("user_state")
                    //Укажите имена любых столбцов, в которых есть автоматически сгенерированные ключи.
                    .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public UserState save(UserState userState) {
        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
              //  .addValue("id",userState.getId())    //Добавьте параметр к этому источнику параметра.
                .addValue("nameUS",userState.getNameUS());

          /*BeanPropertySqlParameterSource - анализирует переданный ему объект и для каждого свойства
         объекта создаёт параметр с именем свойства и его значением.*/
        BeanPropertySqlParameterSource beanPropertySqlParameterSource = new BeanPropertySqlParameterSource(userState);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        if(userState.isNew()){
            //Выполните вставку, используя значения, переданные и возвращающие сгенерированный ключ.
            //Number newKey = jdbcInsert.executeAndReturnKey(mapSqlParameterSource);
            //Number newKey = jdbcInsert.executeAndReturnKey(beanPropertySqlParameterSource);
            namedParameterJdbcTemplate.update("INSERT INTO user_state (name_us) VALUES (:nameUS)",mapSqlParameterSource,keyHolder);
            //AtomicInteger предоставляет операции с базовым значением int, которые могут быть прочитаны и записаны атомарно,
            // а также содержит расширенные атомарные операции.
            AtomicInteger atomicInteger = new AtomicInteger();
            keyHolder.getKeys().forEach((k,y)-> {
                            if(k.equals("id"))
                                atomicInteger.addAndGet((Integer)y);
                    }
            );

       /*   int count=0;
            for (Map.Entry<String, Object> entry : keyHolder.getKeys().entrySet()){
                if(count==0)
                    System.out.println(entry.getValue());
                count++;
            }*/

            userState.setId(atomicInteger.intValue());
        }else {
            if(namedParameterJdbcTemplate.update("UPDATE user_state SET name_us=:nameUS",
                    mapSqlParameterSource)==0)
                return null;
        }

        return userState;
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM user_state WHERE id=?",id) != 0;
    }

    @Override
    public UserState get(int id) {
   /*            String sql = "select * from user_state where id=:id";
      //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        return namedParameterJdbcTemplate.queryForObject(sql, params, new UserStateRowMapper());*/

        List<UserState> userStateList = jdbcTemplate.query("SELECT * FROM user_state WHERE id=?",new UserStateRowMapper(),id);

        return DataAccessUtils.singleResult(userStateList); //Возвращает один объект результата из данной коллекции.
    }

    @Override
    public List<UserState> getAll() {
        return jdbcTemplate.query("SELECT * from user_state",ROW_MAPPER_USER_STATE);
    }

    @Override
    public UserState getByNameUS(String nameUS) {
        //List<UserState> userStates = jdbcTemplate.query("SELECT * FROM user_state WHERE name_us=?",new UserStateRowMapper(),nameUS);

        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("nameUS",nameUS);
        UserState userState = namedParameterJdbcTemplate.queryForObject("SELECT * from user_state where name_us=:nameUS",mapSqlParameterSource, new UserStateRowMapper());

        return userState;
    }
}
