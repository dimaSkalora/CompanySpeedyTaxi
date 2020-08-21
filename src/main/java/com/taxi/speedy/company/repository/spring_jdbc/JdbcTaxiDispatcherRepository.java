package com.taxi.speedy.company.repository.spring_jdbc;

import com.taxi.speedy.company.model.TaxiDispatcher;
import com.taxi.speedy.company.repository.TaxiDispatcherRepository;
import com.taxi.speedy.company.repository.spring_jdbc.row_mapper.TaxiDispatcherRowMapper;
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

@Repository//("jdbcTaxiDispatcherRepository")
public class JdbcTaxiDispatcherRepository implements TaxiDispatcherRepository {

    /*
     *  JdbcTemplate - это мощный механизм для подключения к базе данных и выполнения SQL-запросов.
     *  Мы можем выполнять все операции с базой данных с помощью класса JdbcTemplate, такие как вставка,
     *  обновление, удаление и извлечение данных из базы данных.
     */
    private JdbcTemplate jdbcTemplate = null;
    //способ вставки данных по именованному параметру. Таким образом мы используем имена вместо? (Знак вопроса)
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate = null;
    /* SimpleJdbcInsert - многопоточный, многоразовый объект, обеспечивающий удобные возможности вставки для таблицы.
          Он обеспечивает обработку метаданных, чтобы упростить код, необходимый для построения основного оператора insert.
          Все, что вам нужно указать, - это имя таблицы и Карта, содержащая имена столбцов и значения столбца.
       */
    private SimpleJdbcInsert jdbcInsert = null;

    //Статический метод фабрики для создания нового BeanPropertyRowMapper (с отображенным классом,
    // указанным только один раз).
    //private static final RowMapper<TaxiDispatcher> ROW_MAPPER_TAXI_DISPATCHER = BeanPropertyRowMapper.newInstance(TaxiDispatcher.class);
    private static BeanPropertyRowMapper<TaxiDispatcher> ROW_MAPPER_TAXI_DISPATCHER = BeanPropertyRowMapper.newInstance(TaxiDispatcher.class);


    private final String sqlQuery ="SELECT td.id as td_id, td.id_user as td_id_user \n" +
            "       u.id as u_id, u.name as u_name, u.email as u_email, \n" +
            "       u.password as u_password, u.phone as u_phone, u.address as u_address, \n" +
            "       u.registered as u_registered, u.enabled as u_enabled \n" +
            "       FROM taxi_dispatchers td \n" +
            "       left join users u on td.id_user = u.id";

    @Autowired
    public JdbcTaxiDispatcherRepository(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        jdbcInsert = new SimpleJdbcInsert(dataSource)
                //Укажите имя таблицы, которое будет использоваться для вставки.
                .withTableName("taxi_dispatchers")
                //Укажите имена любых столбцов, в которых есть автоматически сгенерированные ключи.
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public TaxiDispatcher save(TaxiDispatcher taxiDispatcher) {
        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("id",taxiDispatcher.getId())
                .addValue("id_user",taxiDispatcher.getIdUser().getId());

          /*  BeanPropertySqlParameterSource - анализирует переданный ему объект и для каждого свойства
            объекта создаёт параметр с именем свойства и его значением.
         */
        BeanPropertySqlParameterSource beanPropertySqlParameterSource = new BeanPropertySqlParameterSource(taxiDispatcher);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        if (taxiDispatcher.isNew()){
            //Выполните вставку, используя значения, переданные и возвращающие сгенерированный ключ.
            //Number newKey = jdbcInsert.executeAndReturnKey(beanPropertySqlParameterSource);
            Number newKey = jdbcInsert.executeAndReturnKey(sqlParameterSource);

            taxiDispatcher.setId(newKey.intValue());

     /*       namedParameterJdbcTemplate.update("INSERT INTO taxi_dispatchers (id_user) VALUES (:id_user)",sqlParameterSource,keyHolder);
            //AtomicInteger предоставляет операции с базовым значением int, которые могут быть прочитаны и записаны атомарно,
            // а также содержит расширенные атомарные операции.
            AtomicInteger atomicInteger = new AtomicInteger();
            keyHolder.getKeys().forEach((k,y)-> {
                        if(k.equals("id"))
                            atomicInteger.addAndGet((Integer)y);
                    }
            );
            taxiDispatcher.setId(atomicInteger.intValue());*/
        }else {
            if (namedParameterJdbcTemplate.update("update taxi_dispatchers set id_user " +
                    "   where id=:id",sqlParameterSource) == 0){
                return taxiDispatcher;
            }
        }

        return taxiDispatcher;
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("delete from taxi_dispatchers where id=?",id) != 0;
    }

    @Override
    public TaxiDispatcher get(int id) {
        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
  /*      MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        String sqlQueryGet =  sqlQuery +" where td.id=:id";
        return namedParameterJdbcTemplate.queryForObject(sql, params, new TaxiDispatcherRowMapper());*/

        String sqlQueryGet =  sqlQuery +" where td.id=:id";
        List<TaxiDispatcher> taxiDispatcherList = jdbcTemplate.query(sqlQueryGet,new TaxiDispatcherRowMapper(),id);

        return DataAccessUtils.singleResult(taxiDispatcherList); //Возвращает один объект результата из данной коллекции.
    }

    @Override
    public List<TaxiDispatcher> getAll() {
        return jdbcTemplate.query(sqlQuery, new TaxiDispatcherRowMapper());
    }
}
