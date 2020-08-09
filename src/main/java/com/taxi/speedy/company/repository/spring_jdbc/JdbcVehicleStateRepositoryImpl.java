package com.taxi.speedy.company.repository.spring_jdbc;

import com.taxi.speedy.company.model.VehicleState;
import com.taxi.speedy.company.repository.VehicleStateRepository;
import com.taxi.speedy.company.repository.spring_jdbc.row_mapper.VehicleStateRowMapper;
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
import java.util.concurrent.atomic.AtomicInteger;

@Repository//("jdbcVehicleStateRepositoryImpl")
public class JdbcVehicleStateRepositoryImpl implements VehicleStateRepository {

    //Статический метод фабрики для создания нового BeanPropertyRowMapper (с отображенным классом,
    // указанным только один раз).
    //private static final RowMapper<VehicleState> ROW_MAPPER_USER_STATE = BeanPropertyRowMapper.newInstance(VehicleState.class);
    private static BeanPropertyRowMapper<VehicleState> ROW_MAPPER_VEHICLE_STATE = BeanPropertyRowMapper.newInstance(VehicleState.class);

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
    public JdbcVehicleStateRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                //Укажите имя таблицы, которое будет использоваться для вставки.
                .withTableName("vehicle_state")
                //Укажите имена любых столбцов, в которых есть автоматически сгенерированные ключи.
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public VehicleState save(VehicleState vehicleState) {
        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("id",vehicleState.getId())    //Добавьте параметр к этому источнику параметра.
                .addValue("nameVS",vehicleState.getNameVS());

             /*BeanPropertySqlParameterSource - анализирует переданный ему объект и для каждого свойства
         объекта создаёт параметр с именем свойства и его значением.*/
        BeanPropertySqlParameterSource beanPropertySqlParameterSource = new BeanPropertySqlParameterSource(vehicleState);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        if(vehicleState.isNew()){
            //Выполните вставку, используя значения, переданные и возвращающие сгенерированный ключ.
            //Number newKey = jdbcInsert.executeAndReturnKey(mapSqlParameterSource);
            //Number newKey = jdbcInsert.executeAndReturnKey(beanPropertySqlParameterSource);
            //vehicleState.setId(newKey.intValue());

            namedParameterJdbcTemplate.update("INSERT INTO vehicle_state (name_vs) VALUES (:nameVS)",mapSqlParameterSource,keyHolder);
            //AtomicInteger предоставляет операции с базовым значением int, которые могут быть прочитаны и записаны атомарно,
            // а также содержит расширенные атомарные операции.
            AtomicInteger atomicInteger = new AtomicInteger();
            keyHolder.getKeys().forEach((k,v)-> {
                        if(k.equals("id"))
                            atomicInteger.addAndGet((Integer)v);
                    }
            );

            vehicleState.setId(atomicInteger.intValue());
        }else{
            if(namedParameterJdbcTemplate.update("UPDATE vehicle_state SET name_vs=:nameVS where id=:id"
                    ,beanPropertySqlParameterSource)==0)
                return null;
        }


        return vehicleState;
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM vehicle_state WHERE ID=?",id) != 0;
    }

    @Override
    public VehicleState get(int id) {
        /* String sql = "select * from vehicle_state where id=:id";
        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        return namedParameterJdbcTemplate.queryForObject(sql, params, ROW_MAPPER_VEHICLE_STATE());*/

        List<VehicleState> vehicleStates = jdbcTemplate.query("SELECT * FROM vehicle_state WHERE id=?",new VehicleStateRowMapper(),id);

        return DataAccessUtils.singleResult(vehicleStates);//Возвращает один объект результата из данной коллекции.
    }

    @Override
    public List<VehicleState> getAll() {
        return jdbcTemplate.query("SELECT * FROM vehicle_state",new VehicleStateRowMapper());
    }

    @Override
    public VehicleState getByNameVS(String nameVS) {
        //List<VehicleState> userStates = jdbcTemplate.query("SELECT * FROM vehicle_state WHERE name_vs=?",new VehicleStateRowMapper(),nameVS);
        //return DataAccessUtils.singleResult(vehicleStates);//Возвращает один объект результата из данной коллекции.

        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("nameVS", nameVS);
        VehicleState vehicleState = namedParameterJdbcTemplate.queryForObject("SELECT * FROM vehicle_state WHERE name_vs=:nameVS"
                ,mapSqlParameterSource,new VehicleStateRowMapper());

        return vehicleState;
    }
}
