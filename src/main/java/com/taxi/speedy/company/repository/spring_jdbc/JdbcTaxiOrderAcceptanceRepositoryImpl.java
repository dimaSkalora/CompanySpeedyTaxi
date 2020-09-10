package com.taxi.speedy.company.repository.spring_jdbc;

import com.taxi.speedy.company.model.TaxiDispatcherOrder;
import com.taxi.speedy.company.model.TaxiOrderAcceptance;
import com.taxi.speedy.company.repository.TaxiOrderAcceptanceRepository;
import com.taxi.speedy.company.repository.spring_jdbc.row_mapper.TaxiOrderAcceptanceRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
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
            " toa.id_taxi_dispatcher_order as toa_id_taxi_dispatcher_order, toa.id_taxi_user_order as toa_id_taxi_user_order,\n" +
            " toa.execution_status as toa_execution_status, toa.adoption_status as toa_adoption_status, \n " +
            " uv.id as uv_id, uv.start_date as uv_start_date, uv.end_date as uv_end_date, uv.id_user as uv_id_user, \n" +
            " uv.id_vehicle as uv_id_vehicle, uv.is_current_user_machine as uv_is_current_user_machine, \n" +
            " tdo.id as tdo_id, tdo.date_time_order as dto_date_time_order, tdo.id_taxi_dispatcher as tdo_id_td,\n" +
            " tdo.user_name as tdo_user_name, tdo.user_phone as tdo_user_phone,\n" +
            " tdo.address_departure as tdo_address_departure, tdo.address_arrival as tdo_address_arrival,\n" +
            " tdo.start_date as tdo_start_date, tdo.end_date as tdo_end_date, tdo.fulfilled as tdo_fulfilled,\n" +
            " tuo.id as tuo_id, tuo.date_time_order as tuo_date_time_order, tuo.id_user as tuo_id_user,\n"+
            " tuo.address_departure as tuo_address_departure, \n"+
            " tuo.address_arrival as tuo_address_arrival, tuo.start_date as tuo_start_date,\n"+
            " tuo.end_date as tuo_end_date, tuo.fulfilled as tuo_fulfilled\n"+
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
                .addValue("id",taxiOrderAcceptance.getId())
                .addValue("idUserVehicle",taxiOrderAcceptance.getIdUserVehicle().getId());
        if(taxiOrderAcceptance.getIdTaxiDispatcherOrder() != null)
            parameterSource.addValue("idTaxiDispatcherOrder",taxiOrderAcceptance.getIdTaxiDispatcherOrder().getId());
        if(taxiOrderAcceptance.getIdTaxiUserOrder() != null)
            parameterSource.addValue("idTaxiUserOrder",taxiOrderAcceptance.getIdTaxiUserOrder().getId());
        parameterSource.addValue("executionStatus",taxiOrderAcceptance.getExecutionStatus());
        parameterSource.addValue("adoptionStatus",taxiOrderAcceptance.getAdoptionStatus());

        /*  BeanPropertySqlParameterSource - анализирует переданный ему объект и для каждого свойства
            объекта создаёт параметр с именем свойства и его значением.
         */
        BeanPropertySqlParameterSource propertySqlParameterSource = new BeanPropertySqlParameterSource(taxiOrderAcceptance);

        if (taxiOrderAcceptance.isNew()){
            Number newKey = simpleJdbcInsert.executeAndReturnKey(parameterSource);
            //Number newKey = simpleJdbcInsert.executeAndReturnKey(propertySqlParameterSource);

            taxiOrderAcceptance.setId(newKey.intValue());
        }else if (namedParameterJdbcTemplate.update("update taxi_order_acceptance set id_user_vehicle=:idUserVehicle,\n" +
                        " id_taxi_dispatcher_order=:idTaxiDispatcherOrder, id_taxi_user_order=:idTaxiUserOrder,\n" +
                        " executionStatus=:executionStatus, adoptionStatus=:adoptionStatus\n" +
                        " where id=:id", parameterSource) == 0){
            return taxiOrderAcceptance;
        }

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

        String sqlQueryGet = sqlQuery+" where toa.id=?";
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
        String sqlFilterTaxiUserOrder = sqlQuery;
        List<TaxiOrderAcceptance> list;
        int count = 0;

        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        if(taxiOrderAcceptance.getId() != null)
            parameterSource.addValue("id",taxiOrderAcceptance.getId());
        if(taxiOrderAcceptance.getIdUserVehicle() != null)
            parameterSource.addValue("idUserVehicle",taxiOrderAcceptance.getIdUserVehicle());
        if(taxiOrderAcceptance.getIdTaxiDispatcherOrder() != null)
            parameterSource.addValue("idTaxiDispatcherOrder",taxiOrderAcceptance.getIdTaxiDispatcherOrder());
        if(taxiOrderAcceptance.getIdTaxiUserOrder() != null)
            parameterSource.addValue("idTaxiUserOrder",taxiOrderAcceptance.getIdTaxiUserOrder());
        if(taxiOrderAcceptance.getExecutionStatus() != null)
            parameterSource.addValue("executionStatus",taxiOrderAcceptance.getExecutionStatus());
        if(taxiOrderAcceptance.getAdoptionStatus() != null)
            parameterSource.addValue("adoptionStatus",taxiOrderAcceptance.getAdoptionStatus());

        for(var mapEntry: parameterSource.getValues().entrySet()){
            String paramName = mapEntry.getKey();

            if (count==0){
                switch (paramName){
                    case "id"                       ->   sqlFilterTaxiUserOrder = sqlFilterTaxiUserOrder+" where toa.id=:id\n";
                    case "idUserVehicle"            ->   sqlFilterTaxiUserOrder += " where toa.id_user_vehicle=:idUserVehicle\n";
                    case "idTaxiDispatcherOrder"    ->   sqlFilterTaxiUserOrder += " where toa.id_taxi_dispatcher_order=:idTaxiDispatcherOrder\n";
                    case "idTaxiUserOrder"          ->   sqlFilterTaxiUserOrder += " where toa.id_taxi_user_order=:idTaxiUserOrder\n";
                    case "executionStatus"          ->   sqlFilterTaxiUserOrder += " where toa.execution_status=:executionStatus\n";
                    case "adoptionStatus"           ->   sqlFilterTaxiUserOrder += " where toa.adoption_status=:adoptionStatus\n";
                }
            }else {
                switch (paramName){
                    case "id"                       ->   sqlFilterTaxiUserOrder = sqlFilterTaxiUserOrder+" and toa.id=:id\n";
                    case "idUserVehicle"            ->   sqlFilterTaxiUserOrder += " and toa.id_user_vehicle=:idUserVehicle\n";
                    case "idTaxiDispatcherOrder"    ->   sqlFilterTaxiUserOrder += " and toa.id_taxi_dispatcher_order=:idTaxiDispatcherOrder\n";
                    case "idTaxiUserOrder"          ->   sqlFilterTaxiUserOrder += " and toa.id_taxi_user_order=:idTaxiUserOrder\n";
                    case "executionStatus"          ->   sqlFilterTaxiUserOrder += " and toa.execution_status=:executionStatus\n";
                    case "adoptionStatus"           ->   sqlFilterTaxiUserOrder += " and toa.adoption_status=:adoptionStatus\n";
                }
            }
            count++;
        }

        if ((sqlCondition != null) && (!sqlCondition.equals(""))){
            if (parameterSource.getParameterNames().length > 0)
                sqlFilterTaxiUserOrder += "and ( "+sqlCondition+" )";
            else
                sqlFilterTaxiUserOrder += " where ("+sqlCondition+" )";
        }
        
        list = namedParameterJdbcTemplate.query(sqlFilterTaxiUserOrder,parameterSource,new TaxiOrderAcceptanceRowMapper());
        
        return list;
    }
}
