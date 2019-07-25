package com.taxi.speedy.company.repository.spring_jdbc.row_mapper;

import com.taxi.speedy.company.model.TaxiRoute;
import com.taxi.speedy.company.model.UserBankCard;
import com.taxi.speedy.company.model.UserVehicle;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

//RowMapper <T> - используется JdbcTemplate для отображения строк ResultSet для каждой строки.
//Реализации этого интерфейса выполняют фактическую работу по отображению каждой строки в объект результата
public class TaxiRouteRowMapper implements RowMapper<TaxiRoute> {
    //Класс ResultSet представляет результирующий набор данных и обеспечивает приложению построчный доступ
    // к результатам запросов. При обработке запроса ResultSet поддерживает указатель на текущую обрабатываемую строку.
    @Override
    public TaxiRoute mapRow(ResultSet resultSet, int i) throws SQLException {
        //Методы ResultSet.getXXX предоставляют доступ к значениям в колонках в текущей строке.
        // В пределах одной строки значения могут быть считаны в любом порядке
        TaxiRoute taxiRoute = new TaxiRoute();
        taxiRoute.setId(resultSet.getInt(1));                       //taxiRoute.setId(resultSet.getInt("id));
        taxiRoute.setIdUserVehicle((UserVehicle) resultSet.getObject(2));
        taxiRoute.setIdUserBankCard((UserBankCard) resultSet.getObject(3));
        taxiRoute.setDeparturePoint(resultSet.getString(4));
        taxiRoute.setArrivalPoint(resultSet.getString(5));
        taxiRoute.setStartDate(resultSet.getObject(6, LocalDateTime.class));
        //taxiRoute.setStartDate(resultSet.getTimestamp(6).toLocalDateTime());
        taxiRoute.setEndDate(resultSet.getTimestamp(7).toLocalDateTime());
        taxiRoute.setLanding(resultSet.getInt(8));
        taxiRoute.setTariffPerKilometer(resultSet.getDouble(9));
        taxiRoute.setDistance(resultSet.getDouble(10));
        taxiRoute.setFarePayment(resultSet.getDouble(11));
        taxiRoute.setFuelConsuption(resultSet.getDouble(12));

        return taxiRoute;
    }
}
