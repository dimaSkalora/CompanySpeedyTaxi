package com.taxi.speedy.company.repository.spring_jdbc;

import com.taxi.speedy.company.model.Vehicle;
import com.taxi.speedy.company.repository.VehicleRepository;
import com.taxi.speedy.company.repository.spring_jdbc.row_mapper.VehicleRowMapper;
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

@Repository/*("jdbcVehicleRepositoryImpl")*/
public class JDBCVehicleRepositoryImpl implements VehicleRepository {
    //Статический метод фабрики для создания нового BeanPropertyRowMapper (с отображенным классом,
    // указанным только один раз).
    //private static final RowMapper<Vehicle> ROW_MAPPER_VEHICLE = BeanPropertyRowMapper.newInstance(Vehicle.class);
    private static BeanPropertyRowMapper<Vehicle> ROW_MAPPER_VEHICLE = BeanPropertyRowMapper.newInstance(Vehicle.class);

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
    private SimpleJdbcInsert jdbcInsert;

    @Autowired
    public JDBCVehicleRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
            //Укажите имя таблицы, которое будет использоваться для вставки.
            .withTableName("vehicle")
            //Укажите имена любых столбцов, в которых есть автоматически сгенерированные ключи.
            .usingGeneratedKeyColumns("id");
        this.jdbcTemplate=jdbcTemplate;
        this.namedParameterJdbcTemplate=namedParameterJdbcTemplate;
    }

    @Override
    public Vehicle save(Vehicle vehicle) {
        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("id",vehicle.getId())    //Добавьте параметр к этому источнику параметра.
                .addValue("nameCar",vehicle.getNameCar())
                .addValue("vehicleNumber",vehicle.getVehicleNumber())
                .addValue("yearIssue",vehicle.getYearIssue())
                .addValue("category", vehicle.getCategory())
                .addValue("color", vehicle.getColor())
                .addValue("fuelConsumption", vehicle.getFuelConsumption());

        /*        BeanPropertySqlParameterSource - анализирует переданный ему объект и для каждого свойства
         объекта создаёт параметр с именем свойства и его значением.*/
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(vehicle);

        if (vehicle.isNew()){
            //Выполните вставку, используя значения, переданные и возвращающие сгенерированный ключ.
            //Number newKey = jdbcInsert.executeAndReturnKey(mapSqlParameterSource);
            Number newKey = jdbcInsert.executeAndReturnKey(parameterSource);
            vehicle.setId(newKey.intValue());
        }else {
            if (namedParameterJdbcTemplate.update("UPDATE vehicles SET name_car=:name_car, vehicle_number=:vehicle_number," +
                    "yearIssue=:yearIssue, category=:category, color=:color, fuel_consumption=:fuel_consumption",parameterSource)==0)
                return null;
        }
        return vehicle;
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("UPDATE vehicles SET id=?",id) != 0;
    }

    @Override
    public Vehicle get(int id) {
             /*  String sql = "select * from users vehicles id=:id";
      //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        return namedParameterJdbcTemplate.queryForObject(sql, params, new VehicleRowMapper());*/

        List<Vehicle> vehicles = jdbcTemplate.query("SELECT * FROM vehicles WHERE id=?", new VehicleRowMapper(), id);

        return (Vehicle) DataAccessUtils.singleResult(vehicles); //Возвращает один объект результата из данной коллекции.
    }

    @Override
    public List<Vehicle> getAll() {
        List<Vehicle> vehicles = jdbcTemplate.query("SELECT * FROM vehicles",ROW_MAPPER_VEHICLE);
        return vehicles;
    }

    @Override
    public List<Vehicle> getByNameCar(String nameCar) {
        //List<Vehicle> vehicles = jdbcTemplate.query("SELECT * FROM vehicles WHERE name_car=?",new VehicleRowMapper(),nameCar);
        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("name_car",nameCar);

        return namedParameterJdbcTemplate.query("SELECT * FROM vehicles WHERE name_car=:name_car",parameterSource,new VehicleRowMapper());
    }

    @Override
    public List<Vehicle> getByVehicleNumber(String vehicleNumber) {
        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
     /*   MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("vehicle_number",vehicleNumber);

        return namedParameterJdbcTemplate.query("SELECT * FROM vehicles WHERE name_car=:name_car",parameterSource,new VehicleRowMapper());
     */
        return jdbcTemplate.query("SELECT * FROM vehicles WHERE vehicle_number=?", ROW_MAPPER_VEHICLE, vehicleNumber);
    }
}
