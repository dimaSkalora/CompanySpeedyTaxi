package com.taxi.speedy.company.repository.spring_jdbc.row_mapper;

import com.taxi.speedy.company.model.ComingConsuptionFuel;
import com.taxi.speedy.company.model.UserVehicle;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

//RowMapper <T> - используется JdbcTemplate для отображения строк ResultSet для каждой строки.
//Реализации этого интерфейса выполняют фактическую работу по отображению каждой строки в объект результата
public class ComingConsuptionFuelRowMapper implements RowMapper<ComingConsuptionFuel> {
    //Класс ResultSet представляет результирующий набор данных и обеспечивает приложению построчный доступ
    // к результатам запросов. При обработке запроса ResultSet поддерживает указатель на текущую обрабатываемую строку.
    @Override
    public ComingConsuptionFuel mapRow(ResultSet resultSet, int i) throws SQLException {
        //Методы ResultSet.getXXX предоставляют доступ к значениям в колонках в текущей строке.
        // В пределах одной строки значения могут быть считаны в любом порядке
        ComingConsuptionFuel comingConsuptionFuel = new ComingConsuptionFuel();
        comingConsuptionFuel.setId(resultSet.getInt(1));                            //comingConsuptionFuel.setId(resultSet.getInt("id"));
        comingConsuptionFuel.setDateCCF(resultSet.getString(2));
        comingConsuptionFuel.setIdUserVehicle((UserVehicle) resultSet.getObject(3));
        comingConsuptionFuel.setConsuption(resultSet.getDouble(4));
        comingConsuptionFuel.setComing(resultSet.getDouble(5));
        comingConsuptionFuel.setRemainder(resultSet.getDouble(6));
        comingConsuptionFuel.setKilometer(resultSet.getDouble(7));
        comingConsuptionFuel.setTotaKilometer(resultSet.getDouble(8));

        return comingConsuptionFuel;
    }
}
