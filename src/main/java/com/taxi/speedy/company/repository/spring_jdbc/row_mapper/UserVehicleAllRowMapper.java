package com.taxi.speedy.company.repository.spring_jdbc.row_mapper;

import com.taxi.speedy.company.model.User;
import com.taxi.speedy.company.model.UserVehicle;
import com.taxi.speedy.company.model.Vehicle;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

//RowMapper <T> - используется JdbcTemplate для отображения строк ResultSet для каждой строки.
//Реализации этого интерфейса выполняют фактическую работу по отображению каждой строки в объект результата
public class UserVehicleAllRowMapper implements RowMapper<UserVehicle> {
    //Класс ResultSet представляет результирующий набор данных и обеспечивает приложению построчный доступ
    // к результатам запросов. При обработке запроса ResultSet поддерживает указатель на текущую обрабатываемую строку.
    @Override
    public UserVehicle mapRow(ResultSet resultSet, int i) throws SQLException {
        //Методы ResultSet.getXXX предоставляют доступ к значениям в колонках в текущей строке.
        // В пределах одной строки значения могут быть считаны в любом порядке
        User user = new User();

        Vehicle vehicle = new Vehicle();

        UserVehicle userVehicle = new UserVehicle();

        userVehicle.setId(resultSet.getInt(1));                                     //userVehicle.setId(resultSet.getInt("id"));
        userVehicle.setStartDate(resultSet.getTimestamp(2).toLocalDateTime());      //userVehicle.setStartDate(resultSet.getTimestamp("start_date").toLocalDateTime());
        //userVehicle.setStartDate(resultSet.getObject(2,LocalDateTime.class));                 //userVehicle.setStartDate(resultSet.getObject(2,LocalDateTime.class));
        userVehicle.setEndDate(resultSet.getTimestamp(3).toLocalDateTime());
        userVehicle.setIdUser((User) resultSet.getObject(4));                       //userVehicle.setIdUser((User) resultSet.getObject("id_user"));
        userVehicle.setIdVehicle((Vehicle) resultSet.getObject(5));
        userVehicle.setIsCurrentUserMachine(resultSet.getInt(6));

        return userVehicle;
    }

}
