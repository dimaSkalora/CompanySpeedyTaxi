package com.taxi.speedy.company.repository.spring_jdbc.row_mapper;

import com.taxi.speedy.company.model.TaxiDispatcher;
import com.taxi.speedy.company.model.TaxiDispatcherOrder;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

//RowMapper <T> - используется JdbcTemplate для отображения строк ResultSet для каждой строки.
//Реализации этого интерфейса выполняют фактическую работу по отображению каждой строки в объект результата
public class TaxiDispatcherOrderRowMapper implements RowMapper<TaxiDispatcherOrder> {
    //Класс ResultSet представляет результирующий набор данных и обеспечивает приложению построчный доступ
    // к результатам запросов. При обработке запроса ResultSet поддерживает указатель на текущую обрабатываемую строку.
    @Override
    public TaxiDispatcherOrder mapRow(ResultSet resultSet, int i) throws SQLException {
        //Методы ResultSet.getXXX предоставляют доступ к значениям в колонках в текущей строке.
        // В пределах одной строки значения могут быть считаны в любом порядке
        TaxiDispatcherOrder taxiDispatcherOrder = new TaxiDispatcherOrder();
        taxiDispatcherOrder.setId(resultSet.getInt(1));                         //taxiDispatcherOrder.setId(resultSet.getInt("id"));
       //taxiDispatcherOrder.setDateTimeOrder(resultSet.getTimestamp(2).toLocalDateTime());
        taxiDispatcherOrder.setDateTimeOrder(resultSet.getObject(2,LocalDateTime.class));
        //taxiDispatcherOrder.setIdTaxiDispatcher((TaxiDispatcher) resultSet.getObject(3));
        taxiDispatcherOrder.setIdTaxiDispatcher(resultSet.getObject(3,TaxiDispatcher.class));
        taxiDispatcherOrder.setUserName(resultSet.getString(4));
        taxiDispatcherOrder.setUserPhone(resultSet.getString(5));
        taxiDispatcherOrder.setAddressDeparture(resultSet.getString(6));
        taxiDispatcherOrder.setAddressArrival(resultSet.getString(7));
        taxiDispatcherOrder.setStartDate(resultSet.getTimestamp(8).toLocalDateTime());
        taxiDispatcherOrder.setEndDate(resultSet.getTimestamp(9).toLocalDateTime());
        taxiDispatcherOrder.setFulfilled(resultSet.getInt(10));

        return taxiDispatcherOrder;
    }
}
