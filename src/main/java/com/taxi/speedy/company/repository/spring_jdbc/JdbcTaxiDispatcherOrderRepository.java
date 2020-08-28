package com.taxi.speedy.company.repository.spring_jdbc;

import com.taxi.speedy.company.model.TaxiDispatcherOrder;
import com.taxi.speedy.company.repository.TaxiDispatcherOrderRepository;
import com.taxi.speedy.company.repository.spring_jdbc.row_mapper.TaxiDispatcherOrderRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.List;

@Repository
public class JdbcTaxiDispatcherOrderRepository implements TaxiDispatcherOrderRepository {

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
        Все, что вам нужно указать, - это имя таблицы и Карта, содержащая имена столбцов и значения столбца.
     */
    private SimpleJdbcInsert simpleJdbcInsert;

    //Статический метод фабрики для создания нового BeanPropertyRowMapper (с отображенным классом,
    // указанным только один раз).
    //private static final RowMapper<TaxiDispatcherOrder> ROW_MAPPER_TD_ORDER = BeanPropertyRowMapper.newInstance(TaxiDispatcherOrder.class);
    private static BeanPropertyRowMapper<TaxiDispatcherOrder> ROW_MAPPER_TD_ORDER = BeanPropertyRowMapper.newInstance(TaxiDispatcherOrder.class);

    private final String sqlQuery ="";

    @Autowired
    public JdbcTaxiDispatcherOrderRepository(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                //Укажите имя таблицы, которое будет использоваться для вставки.
                .withTableName("taxi_dispatcher_orders")
                //Укажите имена любых столбцов, в которых есть автоматически сгенерированные ключи.
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public TaxiDispatcherOrder save(TaxiDispatcherOrder taxiDispatcherOrder) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public TaxiDispatcherOrder get(int id) {
        // String sqlGet =  sqlQuery +" where .id=:id";
        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
       /* MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("id",id);
        List<TaxiDispatcherOrder> taxiDispatcherOrders = namedParameterJdbcTemplate.query(sqlGet,
                mapSqlParameterSource, new TaxiDispatcherOrderRowMapper());*/

       String sqlGet =  sqlQuery +" where .id=?";
       List<TaxiDispatcherOrder> taxiDispatcherOrders = jdbcTemplate.query(sqlGet,new TaxiDispatcherOrderRowMapper(),id);

        return DataAccessUtils.singleResult(taxiDispatcherOrders);//Возвращает один объект результата из данной коллекции.
    }

    @Override
    public List<TaxiDispatcherOrder> getAll() {
        return null;
    }

    @Override
    public List<TaxiDispatcherOrder> getByIdTaxiDispatcher(int idTaxiDispatcher) {
        return null;
    }

    @Override
    public List<TaxiDispatcherOrder> getByAddressDeparture(String addressDeparture) {
        return null;
    }

    @Override
    public List<TaxiDispatcherOrder> getByAddressArrival(String addressArrival) {
        return null;
    }

    @Override
    public List<TaxiDispatcherOrder> getByUserName(String userName) {
        return null;
    }

    @Override
    public List<TaxiDispatcherOrder> getByPhoneUser(String phoneUser) {
        return null;
    }

    @Override
    public List<TaxiDispatcherOrder> getByFulfilled(int fulfilled) {
        return null;
    }

    @Override
    public List<TaxiDispatcherOrder> getByAllFulfilled(int fulfilled) {
        return null;
    }

    @Override
    public List<TaxiDispatcherOrder> getByBetween(LocalDate startDate, LocalDate endDate) {
        return null;
    }

    @Override
    public List<TaxiDispatcherOrder> getFilterTaxiDispatcherOrder(TaxiDispatcherOrder taxiDispatcherOrder) {
        return null;
    }
}
