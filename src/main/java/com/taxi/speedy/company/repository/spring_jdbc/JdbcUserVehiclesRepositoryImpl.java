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
                .addValue("idUser", userVehicle.getIdUser())
                .addValue("idVehicle", userVehicle.getIdVehicle())
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
                    "id_vehicle=:idVehicle, is_current_user_machine=:isCurrentUserMachine WHERE id=:id", parameterSource)==0){
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

       List<UserVehicle> userVehicles = jdbcTemplate.query("SELECT * FROM user_vehicles WHERE id=?", ROW_MAPPER_USER_VEHICLE,id);

        return DataAccessUtils.singleResult(userVehicles);//Возвращает один объект результата из данной коллекции.
    }

    @Override
    public List<UserVehicle> getAll() {
        List<UserVehicle> userVehicles = jdbcTemplate.query("SELECT * FROM user_vehicles", new UserVehicleRowMapper());
        return null;
    }

    @Override
    public List<UserVehicle> getAllByUser(int idUser) {
        //List<UserVehicle> userVehicles = jdbcTemplate.query("SELECT * FROM user_vehicles WHERE id_user=?",new UserVehicleRowMapper(),idUser);
        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                            .addValue("idUser",idUser);
        List<UserVehicle> userVehicles = namedParameterJdbcTemplate.query("select * from user_vehicles where id_user=:idUser",parameterSource,ROW_MAPPER_USER_VEHICLE);

        return userVehicles;
    }

    @Override
    public List<UserVehicle> getAllByVehicle(int idVehicle) {
        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
   /*     MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("idVehicle",idVehicle);
        List<UserVehicle> userVehicles = namedParameterJdbcTemplate.query("select * from user_vehicles where id_vehicle=:idVehicle",parameterSource,ROW_MAPPER_USER_VEHICLE);
        */
        List<UserVehicle> userVehicles = jdbcTemplate.query("select * from user_vehicles where id_vehicle=?;",new UserVehicleRowMapper(), idVehicle);

        return userVehicles;
    }

    @Override
    public List<UserVehicle> getByIsCurrentUserMachine(int isCurrentUserMachine) {
        //List<UserVehicle> userVehicles = jdbcTemplate.query("select * from user_vehicles where is_current_user_machine=?;",new UserVehicleRowMapper(), isCurrentUserMachine);
        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("is_current_user_machine",isCurrentUserMachine);

        List<UserVehicle> userVehicles = namedParameterJdbcTemplate.query("select * from user_vehicles where is_current_user_machine=:isCurrentUserMachine",mapSqlParameterSource,ROW_MAPPER_USER_VEHICLE);

        return userVehicles;
    }

    @Override
    public List<UserVehicle> getStartDateBetween(LocalDate startDate, LocalDate endDate) {
        List<UserVehicle> userVehicles = jdbcTemplate.query("select * from user_vehicles WHERE start_date " +
                        "BETWEEN ? AND ? ORDER BY start_date DESC",/*DESC - сортируем по убыванию*/
                new UserVehicleRowMapper(),startDate,endDate);

        return userVehicles;
    }

    @Override
    public List<UserVehicle> getEndDateBetween(LocalDate startDate, LocalDate endDate) {
        List<UserVehicle> userVehicles = jdbcTemplate.query("select * from user_vehicles where end_date " +
                        "BETWEEN ? and ? order by end_date DESC ", /*DESC - сортируем по убыванию*/
                new UserVehicleRowMapper(),startDate,endDate);

        return userVehicles;
    }
}
