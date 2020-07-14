package com.taxi.speedy.company.repository.spring_jdbc.row_mapper;

import com.taxi.speedy.company.model.User;
import com.taxi.speedy.company.model.UserVehicle;
import com.taxi.speedy.company.model.Vehicle;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

//RowMapper <T> - используется JdbcTemplate для отображения строк ResultSet для каждой строки.
//Реализации этого интерфейса выполняют фактическую работу по отображению каждой строки в объект результата
public class UserVehicleRowMapper implements RowMapper<UserVehicle> {
    //Класс ResultSet представляет результирующий набор данных и обеспечивает приложению построчный доступ
    // к результатам запросов. При обработке запроса ResultSet поддерживает указатель на текущую обрабатываемую строку.
    @Override
    public UserVehicle mapRow(ResultSet resultSet, int i) throws SQLException {
        //Методы ResultSet.getXXX предоставляют доступ к значениям в колонках в текущей строке.
        // В пределах одной строки значения могут быть считаны в любом порядке
        User user = new User();
        user.setId(resultSet.getInt("u_id"));
        user.setName(resultSet.getString("u_name"));
        user.setEmail(resultSet.getString("u_email"));
        user.setPassword(resultSet.getString("u_password"));
        user.setPhone(resultSet.getString("u_phone"));
        user.setAddress(resultSet.getString("u_address"));
        user.setRegistered(resultSet.getTimestamp("u_registered"));
        user.setEnabled(resultSet.getBoolean("u_enabled"));

        Vehicle vehicle = new Vehicle();
        vehicle.setId(resultSet.getInt("v_id"));
        vehicle.setNameCar(resultSet.getString("v_name_car"));
        vehicle.setVehicleNumber(resultSet.getString("v_vehicle_number"));
        vehicle.setYearIssue(resultSet.getInt("v_year_issue"));
        vehicle.setCategory(resultSet.getString("v_category"));
        vehicle.setColor(resultSet.getString("v_color"));
        vehicle.setFuelConsumption(resultSet.getInt("v_fuel_consumption"));

        UserVehicle userVehicle = new UserVehicle();
        userVehicle.setId(resultSet.getInt("uv_id"));                                     //userVehicle.setId(resultSet.getInt(1));
        userVehicle.setStartDate(resultSet.getTimestamp("uv_start_date").toLocalDateTime());      //userVehicle.setStartDate(resultSet.getTimestamp("start_date").toLocalDateTime());
        //userVehicle.setStartDate(resultSet.getObject(2,LocalDateTime.class));                 //userVehicle.setStartDate(resultSet.getObject(2,LocalDateTime.class));
        if (resultSet.getTimestamp("uv_end_date") != null)
            userVehicle.setEndDate(resultSet.getTimestamp("uv_end_date").toLocalDateTime());
        userVehicle.setIdUser(user);                       //userVehicle.setIdUser((User) resultSet.getObject("id_user"));
        userVehicle.setIdVehicle(vehicle);
        userVehicle.setIsCurrentUserMachine(resultSet.getInt("uv_is_current_user_machine"));

        return userVehicle;
    }
}
