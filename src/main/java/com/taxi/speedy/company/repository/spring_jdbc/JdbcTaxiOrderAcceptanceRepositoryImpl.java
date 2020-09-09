package com.taxi.speedy.company.repository.spring_jdbc;

import com.taxi.speedy.company.model.TaxiDispatcherOrder;
import com.taxi.speedy.company.model.TaxiOrderAcceptance;
import com.taxi.speedy.company.repository.TaxiOrderAcceptanceRepository;
import com.taxi.speedy.company.repository.spring_jdbc.row_mapper.TaxiOrderAcceptanceRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository//("taxiOrderAcceptanceRepositoryImpl")
public class JdbcTaxiOrderAcceptanceRepositoryImpl implements TaxiOrderAcceptanceRepository {
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
    //private static final RowMapper<TaxiOrderAcceptance> ROW_MAPPER_TO_ACCEPTANCE = BeanPropertyRowMapper.newInstance(TaxiOrderAcceptance.class);
    private static BeanPropertyRowMapper<TaxiOrderAcceptance> ROW_MAPPER_TO_ACCEPTANCE = BeanPropertyRowMapper.newInstance(TaxiOrderAcceptance.class);

    private final String sqlQuery =" select  toa.id as toa_id, toa.id_user_vehicle as toa_id_user_vehicle, \n" +
            " toa.id_taxi_dispatcher_order as toa_id_taxi_dispatcher_order, toa.id_taxi_user_order as toa.id_taxi_user_order,\n" +
            " toa.execution_status as toa_execution_status, toa.adoption_status as toa_adoption_status, \n " +
            " uv.id as uv_id, uv.start_date as uv_start_date, uv.end_date as uv_end_date, uv.id_user as uv_id_user, \n" +
            " uv.id_vehicle as uv_id_vehicle, uv.is_current_user_machine as uv_is_current_user_machine, \n" +
            " tdo.id as tdo_id, tdo.date_time_order as dto_date_time_order, tdo.id_taxi_dispatcher as tdo_id_td,\n" +
            " tdo.user_name as tdo_user_name, tdo.user_phone as tdo_user_phone,\n" +
            " tuo.id as tuo_id, tuo.date_time_order as tuo_date_time_order, tuo.id_user as tuo_id_user,\n"+
            " tuo.address_departure as tuo_address_departure, \n"+
            " tuo.address_arrival as tuo_address_arrival, tuo.start_date as tuo_start_date,\n"+
            " tuo.end_date as tuo_end_date, tuo.fulfilled as tuo_fulfilled,\n"+
            " from taxi_order_acceptance toa \n" +
            " left join user_vehicles uv on toa.id_user_vehicle = uv.id\n" +
            " left join taxi_dispatcher_orders tdo on toa.id_taxi_dispatcher_order = tdo.id\n" +
            " left join taxi_user_orders tuo on toa.id_taxi_user_order = tuo.id\n ";

    @Autowired
    public JdbcTaxiOrderAcceptanceRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                //Укажите имя таблицы, которое будет использоваться для вставки.
                .withTableName("taxi_order_acceptance")
                //Укажите имена любых столбцов, в которых есть автоматически сгенерированные ключи.
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public TaxiOrderAcceptance save(TaxiOrderAcceptance taxiOrderAcceptance) {
        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id",taxiOrderAcceptance.getId());

        return taxiOrderAcceptance;
    }

    @Override
    public boolean delete(int id) {
       /* String sqlQueryDelete = "delete from taxi_dispatcher_orders where id=?";
        return jdbcTemplate.update(sqlQueryDelete,id) != 0;*/
        String sqlQueryDelete = "delete from taxi_order_acceptance where id=:id";
        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id",id);
        return namedParameterJdbcTemplate.update(sqlQueryDelete,parameterSource) != 0;
    }

    @Override
    public TaxiOrderAcceptance get(int id) {
      /*  String sqlQueryGet = sqlQuery+" where id=:id";
        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id",id);
        List<TaxiOrderAcceptance> list = namedParameterJdbcTemplate.query(sqlQueryGet,parameterSource,new TaxiOrderAcceptanceRowMapper()); */

        String sqlQueryGet = sqlQuery+" where id=?";
        List<TaxiOrderAcceptance> list = jdbcTemplate.query(sqlQueryGet,new TaxiOrderAcceptanceRowMapper(),id);

        return DataAccessUtils.singleResult(list);//Возвращает один объект результата из данной коллекции.
    }

    @Override
    public List<TaxiOrderAcceptance> getAll() {
        return jdbcTemplate.query(sqlQuery,new TaxiOrderAcceptanceRowMapper());
    }

    @Override
    public List<TaxiOrderAcceptance> filterTaxiUserOrder(TaxiOrderAcceptance taxiOrderAcceptance) {
        return filterTaxiUserOrder(taxiOrderAcceptance,null);
    }

    @Override
    public List<TaxiOrderAcceptance> filterTaxiUserOrder(TaxiOrderAcceptance taxiOrderAcceptance, String sqlCondition) {
        return null;
    }
}
