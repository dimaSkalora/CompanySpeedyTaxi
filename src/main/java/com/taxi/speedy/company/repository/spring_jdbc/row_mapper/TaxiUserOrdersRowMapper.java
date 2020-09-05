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

        User user = new User();
        user.setId(resultSet.getInt("u_id"));
        user.setName(resultSet.getString("u_name"));
        user.setEmail(resultSet.getString("u_email"));
        user.setPassword(resultSet.getString("u_password"));
        user.setPhone(resultSet.getString("u_phone"));
        user.setAddress(resultSet.getString("u_address"));
        user.setRegistered(resultSet.getTimestamp("u_registered"));
        user.setEnabled(resultSet.getBoolean("u_enabled"));

        TaxiUserOrder taxiUserOrder = new TaxiUserOrder();
        taxiUserOrder.setId(resultSet.getInt("tuo_id"));                       //taxiUserOrder.setId(resultSet.getInt(1));
        if(resultSet.getObject("tuo_date_time_order") != null)
            taxiUserOrder.setDateTimeOrder(resultSet.getObject(2,LocalDateTime.class));
        taxiUserOrder.setIdUser(user);
        taxiUserOrder.setAddressDeparture(resultSet.getString("tuo_address_departure"));
        taxiUserOrder.setAddressArrival(resultSet.getString("tuo_address_arrival"));
        taxiUserOrder.setStartDate(resultSet.getObject("tuo_start_date", LocalDateTime.class));
        taxiUserOrder.setEndDate(resultSet.getTimestamp("tuo_end_date").toLocalDateTime());
        taxiUserOrder.setFulfilled(resultSet.getInt("tuo_fulfilled"));

        return taxiUserOrder;
    }
}
