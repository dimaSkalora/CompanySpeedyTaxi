package com.taxi.speedy.company.repository.spring_jdbc.row_mapper;

import com.taxi.speedy.company.model.TaxiUserOrder;
import com.taxi.speedy.company.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

//RowMapper <T> - используется JdbcTemplate для отображения строк ResultSet для каждой строки.
//Реализации этого интерфейса выполняют фактическую работу по отображению каждой строки в объект результата
public class TaxiUserOrdersRowMapper implements RowMapper<TaxiUserOrder> {
    //Класс ResultSet представляет результирующий набор данных и обеспечивает приложению построчный доступ
    // к результатам запросов. При обработке запроса ResultSet поддерживает указатель на текущую обрабатываемую строку.
    @Override
    public TaxiUserOrder mapRow(ResultSet resultSet, int i) throws SQLException {
        //Методы ResultSet.getXXX предоставляют доступ к значениям в колонках в текущей строке.
        // В пределах одной строки значения могут быть считаны в любом порядке
        TaxiUserOrder taxiUserOrder = new TaxiUserOrder();
        taxiUserOrder.setId(resultSet.getInt(1));                       //taxiUserOrder.setId(resultSet.getInt("id"));
        taxiUserOrder.setDateTimeOrder(resultSet.getObject(2,LocalDateTime.class));
        taxiUserOrder.setId((Integer) resultSet.getObject(3));
        taxiUserOrder.setAddressDeparture(resultSet.getString(4));
        taxiUserOrder.setAddressArrival(resultSet.getString(5));
        taxiUserOrder.setStartDate(resultSet.getObject(6, LocalDateTime.class));
        //taxiUserOrder.setStartDate(resultSet.getTimestamp(6).toLocalDateTime());
        taxiUserOrder.setEndDate(resultSet.getTimestamp(7).toLocalDateTime());
        taxiUserOrder.setFulfilled(resultSet.getInt(8));

        return taxiUserOrder;
    }
}
