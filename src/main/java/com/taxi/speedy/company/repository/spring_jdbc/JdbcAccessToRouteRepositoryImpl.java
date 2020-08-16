package com.taxi.speedy.company.repository.spring_jdbc;

import com.taxi.speedy.company.model.AccessToRoute;
import com.taxi.speedy.company.repository.AccessToRouteRepository;
import com.taxi.speedy.company.repository.spring_jdbc.row_mapper.AccessToRouteRowMapper;
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
import java.time.LocalDate;
import java.util.List;

@Repository//("jdbcAccessToRouteRepositoryImpl")
public class JdbcAccessToRouteRepositoryImpl implements AccessToRouteRepository {

    //Статический метод фабрики для создания нового BeanPropertyRowMapper (с отображенным классом,
    // указанным только один раз).
    //private static final RowMapper<AccessToRoute> ROW_MAPPER_ACCESS_TO_ROUTE = BeanPropertyRowMapper.newInstance(AccessToRoute.class);
    private static BeanPropertyRowMapper<AccessToRoute> ROW_MAPPER_ACCESS_TO_ROUTE = BeanPropertyRowMapper.newInstance(AccessToRoute.class);
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
    private SimpleJdbcInsert jdbcInsert;

    private final String sqlQuery ="select  atr.id as atr_id, atr.checks_date_time as atr_checksdatetime, atr.id_user_vehicle as atr_id_user_vehicles \n" +
            " ,atr.id_user_state as atr_id_user_state, atr.id_vehicle_state as atr_id_vehicle_state, atr.is_access as atr_is_access \n" +
            " ,uv.id as uv_id, uv.start_date as uv_start_date, uv.end_date as uv_end_date, uv.id_user as uv_id_user \n" +
            " ,uv.id_vehicle as uv_id_vehicle, uv.is_current_user_machine as uv_is_current_user_machine \n" +
            " ,us.id as us_id, us.name_us as us_name_us, vs.id as vs_id, vs.name_vs as vs_name_vs \n" +
            " ,u.id as u_id, u.name as u_name, u.email as u_email \n" +
            " ,u.password as u_password, u.phone as u_phone, u.address as u_address \n" +
            " ,u.registered as u_registered, u.enabled as u_enabled \n" +
            " ,v.id as v_id, v.name_car as v_name_car \n" +
            " ,v.vehicle_number as v_vehicle_number, v.year_issue as v_year_issue, v.category as v_category \n" +
            " ,v.color as v_color, v.fuel_consumption as v_fuel_consumption \n" +
            " from access_to_route atr " +
            " left join user_vehicles uv on atr.id_user_vehicle = uv.id \n" +
            " left join user_state us on atr.id_user_state = us.id \n" +
            " left join vehicle_state vs on atr.id_vehicle_state = vs.id \n" +
            " left join users u on uv.id_user = u.id \n" +
            " left join vehicles v on uv.id_vehicle = v.id \n";

    @Autowired
    public JdbcAccessToRouteRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        jdbcInsert = new SimpleJdbcInsert(dataSource)
                //Укажите имя таблицы, которое будет использоваться для вставки.
                .withTableName("access_to_route")
                //Укажите имена любых столбцов, в которых есть автоматически сгенерированные ключи.
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public AccessToRoute save(AccessToRoute accessToRoute) {
        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                        .addValue("id",accessToRoute.getId())
                        .addValue("checksDateTime",accessToRoute.getChecksDateTime())
                        .addValue("idUserVehicle",accessToRoute.getIdUserVehicle().getId())
                        .addValue("idUserState",accessToRoute.getIdUserState().getId())
                        .addValue("idVehicleState",accessToRoute.getIdVehicleState().getId())
                        .addValue("isAccess",accessToRoute.getIsAccess());

          /*  BeanPropertySqlParameterSource - анализирует переданный ему объект и для каждого свойства
            объекта создаёт параметр с именем свойства и его значением.
         */
        BeanPropertySqlParameterSource beanPropertySqlParameterSource = new BeanPropertySqlParameterSource(accessToRoute);

        if (accessToRoute.isNew()){
            //Выполните вставку, используя значения, переданные и возвращающие сгенерированный ключ.
            //Number newKey = jdbcInsert.executeAndReturnKey(beanPropertySqlParameterSource);
            Number newKey = jdbcInsert.executeAndReturnKey(mapSqlParameterSource);
            accessToRoute.setId(newKey.intValue());
        }else {
            if(namedParameterJdbcTemplate.update("UPDATE access_to_route SET checks_date_time=:checksDateTime," +
                    " id_user_vehicle=:idUserVehicle, id_user_state=:idUserState, " +
                    " id_vehicle_state=:idVehicleState, is_access=:isAccess WHERE id=:id",mapSqlParameterSource) == 0){
                return null;
            }
        }

        return accessToRoute;
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM access_to_route WHERE id=?",id) != 0;
    }

    @Override
    public AccessToRoute get(int id) {
         // String sql =  sqlQuery +" where atr.id=:id";
        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        /*MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        return namedParameterJdbcTemplate.queryForObject(sql, params, new AccessToRouteRowMapper());*/

        String sqlGet = sqlQuery + " where atr.id=? ";
        List<AccessToRoute> accessToRouteList = jdbcTemplate.query(sqlGet,new AccessToRouteRowMapper(), id);

        return DataAccessUtils.singleResult(accessToRouteList); //Возвращает один объект результата из данной коллекции.
    }

    @Override
    public List<AccessToRoute> getAll() {
        //List<AccessToRoute> accessToRouteList = jdbcTemplate.query("SELECT * FROM access_to_route",ROW_MAPPER_ACCESS_TO_ROUTE);


        List<AccessToRoute> accessToRouteList = jdbcTemplate.query(sqlQuery,new AccessToRouteRowMapper());
        return accessToRouteList;
    }

    @Override
    public List<AccessToRoute> getByChecksDateTime(LocalDate startDate, LocalDate endDate){
        String sqlChecksDateTimeBetween = sqlQuery +  " and atr.checks_date_time " +
                " BETWEEN ? AND ? \n" +
                "ORDER BY atr.checks_date_time DESC"; /*DESC - сортируем по убыванию*/

        List<AccessToRoute> accessToRouteList = jdbcTemplate.query(sqlChecksDateTimeBetween,new AccessToRouteRowMapper(),startDate,endDate);
        return accessToRouteList;
    }

    @Override
    public List<AccessToRoute> getByUserVehicle(int idUserVehicle) {
        //List<AccessToRoute> userVehicles = jdbcTemplate.query("SELECT * FROM user_vehicles WHERE idUserVehicle=?",ROW_MAPPER_ACCESS_TO_ROUTE,idUser);

        String sqlByUserVehicle = sqlQuery + " where id_user_vehicle=:idUserVehicle";
        //MapSqlParameterSource - Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("idUserVehicle", idUserVehicle);
        List<AccessToRoute> accessToRouteList = namedParameterJdbcTemplate.query(sqlByUserVehicle
                ,mapSqlParameterSource,new AccessToRouteRowMapper());

        return accessToRouteList;
    }

    @Override
    public List<AccessToRoute> getByUserState(int idUserState) {
        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
     /*   MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("idUserState", idUserState);
        List<AccessToRoute> accessToRouteList = namedParameterJdbcTemplate.query("SELECT * FROM access_to_route where id_user_state=:idUserState"
                ,mapSqlParameterSource,new AccessToRouteRowMapper());*/
        String sqlByUserState = sqlQuery + " where id_user_state=?";
        List<AccessToRoute> accessToRouteList = jdbcTemplate.query(sqlByUserState,new AccessToRouteRowMapper(),idUserState);

        return accessToRouteList;
    }

    @Override
    public List<AccessToRoute> getByVehicleState(int idVehicleState) {
        //String sqlQuery = "SELECT * from access_to_route where id_vehicle_state=?";
        //List<AccessToRoute> accessToRouteList = jdbcTemplate.query(sqlQuery,new AccessToRouteRowMapper(),idVehicleState);

        String sqlByVehicleState = sqlQuery + " where id_vehicle_state=:idVehicleState";
        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("idVehicleState", idVehicleState);
        List<AccessToRoute> accessToRouteList = namedParameterJdbcTemplate.query(sqlByVehicleState
                ,mapSqlParameterSource,new AccessToRouteRowMapper());

        return accessToRouteList;
    }

    @Override
    public List<AccessToRoute> getByIsAccess(int isAccess) {
     /*   String sqlQuery = "SELECT * from access_to_route where is_access=:isAccess";
        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("isAccess", isAccess);
        List<AccessToRoute> accessToRouteList = namedParameterJdbcTemplate.query(sqlQuery
                ,mapSqlParameterSource,new AccessToRouteRowMapper());*/

        String sqlByVehicleState = sqlQuery +" where is_access=?";
        List<AccessToRoute> accessToRouteList = jdbcTemplate.query(sqlByVehicleState,new AccessToRouteRowMapper(),isAccess);

        return accessToRouteList;
    }
}
