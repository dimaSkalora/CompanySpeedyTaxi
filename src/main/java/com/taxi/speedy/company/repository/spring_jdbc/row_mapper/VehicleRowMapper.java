package com.taxi.speedy.company.repository.spring_jdbc.row_mapper;

import com.taxi.speedy.company.model.Vehicle;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

//RowMapper <T> - используется JdbcTemplate для отображения строк ResultSet для каждой строки.
//Реализации этого интерфейса выполняют фактическую работу по отображению каждой строки в объект результата
public class VehicleRowMapper implements RowMapper<Vehicle> {
    //Класс ResultSet представляет результирующий набор данных и обеспечивает приложению построчный доступ
    // к результатам запросов. При обработке запроса ResultSet поддерживает указатель на текущую обрабатываемую строку.
    @Override
    public Vehicle mapRow(ResultSet resultSet, int i) throws SQLException {
        //Методы ResultSet.getXXX предоставляют доступ к значениям в колонках в текущей строке.
        // В пределах одной строки значения могут быть считаны в любом порядке
        Vehicle vehicle =  new Vehicle();
        vehicle.setId(resultSet.getInt("id"));                      //vehicle.setId(resultSet.getInt(1));
        vehicle.setNameCar(resultSet.getString("name_car"));       //vehicle.setNameCar(resultSet.getString(2));
        vehicle.setVehicleNumber(resultSet.getString("vehicle_number"));  //vehicle.setVehicleNumber(resultSet.getString(3));
        vehicle.setYearIssue(resultSet.getInt("year_issue"));
        vehicle.setCategory(resultSet.getString("category"));
        vehicle.setColor(resultSet.getString("color"));
        vehicle.setFuelConsumption(resultSet.getInt("fuel_consumption"));

        return vehicle;
    }
}
