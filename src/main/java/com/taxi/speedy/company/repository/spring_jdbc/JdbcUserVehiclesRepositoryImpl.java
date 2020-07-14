package com.taxi.speedy.company.repository.spring_jdbc;

import com.taxi.speedy.company.model.UserVehicle;
import com.taxi.speedy.company.repository.UserVehicleRepository;
import com.taxi.speedy.company.repository.spring_jdbc.row_mapper.UserVehicleRowMapper;
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

@Repository//("jdbcUserVehiclesRepositoryImpl")
public class JdbcUserVehiclesRepositoryImpl implements UserVehicleRepository {
    //Статический метод фабрики для создания нового BeanPropertyRowMapper (с отображенным классом,
    // указанным только один раз).
    //private static final RowMapper<UserVehicle> ROW_MAPPER_USER_VEHICLE = BeanPropertyRowMapper.newInstance(UserVehicle.class);
    private static BeanPropertyRowMapper<UserVehicle> ROW_MAPPER_USER_VEHICLE = BeanPropertyRowMapper.newInstance(UserVehicle.class);
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

    private String sqlQuery = "select uv.id as uv_id, uv.start_date as uv_start_date, uv.end_date as uv_end_date, \n" +
            "       uv.id_user as uv_id_user, uv.id_vehicle as uv_id_vehicle, uv.is_current_user_machine as uv_is_current_user_machine, \n" +
            "       u.id as u_id, u.name as u_name, u.email as u_email, \n" +
            "       u.password as u_password, u.phone as u_phone, u.address as u_address, \n" +
            "       u.registered as u_registered, u.enabled as u_enabled, v.id as v_id, v.name_car as v_name_car, \n" +
            "       v.vehicle_number as v_vehicle_number, v.year_issue as v_year_issue, v.category as v_category, " +
            "       v.color as v_color, v.fuel_consumption as v_fuel_consumption \n" +
            "from user_vehicles uv, users u, vehicles v \n" +
            "where \n" +
            "uv.id_user = u.id \n" +
            "and uv.id_vehicle = v.id \n";

    @Autowired
    public JdbcUserVehiclesRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                //Укажите имя таблицы, которое будет использоваться для вставки.
                .withTableName("user_vehicles")
                //Укажите имена любых столбцов, в которых есть автоматически сгенерированные ключи.
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public UserVehicle save(UserVehicle userVehicle) {
        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("id", userVehicle.getId()) //Добавьте параметр к этому источнику параметра.
                .addValue("startDate", userVehicle.getStartDate())
                .addValue("endDate", userVehicle.getEndDate())
                .addValue("idUser", userVehicle.getIdUser().getId())
                .addValue("idVehicle", userVehicle.getIdVehicle().getId())
                .addValue("isCurrentUserMachine", userVehicle.getIsCurrentUserMachine());

        /*  BeanPropertySqlParameterSource - анализирует переданный ему объект и для каждого свойства
            объекта создаёт параметр с именем свойства и его значением.
         */
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(userVehicle);

        if (userVehicle.isNew()){
            //Выполните вставку, используя значения, переданные и возвращающие сгенерированный ключ.
            Number newKey = simpleJdbcInsert.executeAndReturnKey(mapSqlParameterSource);
            //Number newKey = simpleJdbcInsert.executeAndReturnKey(parameterSource);
            userVehicle.setId(newKey.intValue());
        }else {
            if (namedParameterJdbcTemplate.update("UPDATE user_vehicles SET start_date=:startDate,end_date=:endDate, id_user=:idUser," +
                    "id_vehicle=:idVehicle, is_current_user_machine=:isCurrentUserMachine WHERE id=:id", mapSqlParameterSource)==0){
                return null;
            }
        }

        return userVehicle;
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM user_vehicles WHERE id=?",id) != 0;
    }

    @Override
    public UserVehicle get(int id) {
      //  String sql = "select * from user_vehicles WHERE id=:id";
      //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
       /* MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        return namedParameterJdbcTemplate.queryForObject(sql, params, new UserVehicleRowMapper());*/

        sqlQuery += "and uv.id=? \n" +
                "order by uv_start_date";

       List<UserVehicle> userVehicles = jdbcTemplate.query(sqlQuery, new UserVehicleRowMapper(),id);

        return DataAccessUtils.singleResult(userVehicles);//Возвращает один объект результата из данной коллекции.
    }

    @Override
    public List<UserVehicle> getAll() {
        List<UserVehicle> userVehicles = jdbcTemplate.query(sqlQuery, new UserVehicleRowMapper());
        return userVehicles;
    }

    @Override
    public List<UserVehicle> getAllByUser(int idUser) {
        //List<UserVehicle> userVehicles = jdbcTemplate.query("SELECT * FROM user_vehicles WHERE id_user=?",new UserVehicleRowMapper(),idUser);
        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        sqlQuery += "and id_user=:idUser \n" +
                    "order by uv_start_date";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                            .addValue("idUser",idUser);
        List<UserVehicle> userVehicles = namedParameterJdbcTemplate.query(sqlQuery,parameterSource,new UserVehicleRowMapper());

        return userVehicles;
    }

    @Override
    public List<UserVehicle> getAllByVehicle(int idVehicle) {
        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
   /*     MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("idVehicle",idVehicle);
        List<UserVehicle> userVehicles = namedParameterJdbcTemplate.query("select * from user_vehicles where id_vehicle=:idVehicle",parameterSource,ROW_MAPPER_USER_VEHICLE);
        */
        sqlQuery += "and id_vehicle=? \n" +
                "order by uv_start_date";

        List<UserVehicle> userVehicles = jdbcTemplate.query(sqlQuery,new UserVehicleRowMapper(), idVehicle);

        return userVehicles;
    }

    @Override
    public List<UserVehicle> getByIsCurrentUserMachine(int isCurrentUserMachine) {
        //List<UserVehicle> userVehicles = jdbcTemplate.query("select * from user_vehicles where is_current_user_machine=?;",ROW_MAPPER_USER_VEHICLE, isCurrentUserMachine);
        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        sqlQuery += "and uv.is_current_user_machine=:isCurrentUserMachine \n" +
                "order by uv_start_date";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("isCurrentUserMachine",isCurrentUserMachine);

        List<UserVehicle> userVehicles = namedParameterJdbcTemplate.query(sqlQuery,mapSqlParameterSource,new UserVehicleRowMapper());

        return userVehicles;
    }

    @Override
    public List<UserVehicle> getStartDateBetween(LocalDate startDate, LocalDate endDate) {
        sqlQuery += " and uv.start_date " +
                    " BETWEEN ? AND ? " +
                    "ORDER BY uv.start_date DESC"; /*DESC - сортируем по убыванию*/

        List<UserVehicle> userVehicles = jdbcTemplate.query(sqlQuery,
                new UserVehicleRowMapper(),startDate,endDate);

        return userVehicles;
    }

    @Override
    public List<UserVehicle> getEndDateBetween(LocalDate startDate, LocalDate endDate) {
        sqlQuery += " and uv.end_date " +
                    " BETWEEN ? AND ? " +
                    "ORDER BY uv.end_date DESC"; /*DESC - сортируем по убыванию*/

        List<UserVehicle> userVehicles = jdbcTemplate.query(sqlQuery, /*DESC - сортируем по убыванию*/
                new UserVehicleRowMapper(),startDate,endDate);

        return userVehicles;
    }
}
