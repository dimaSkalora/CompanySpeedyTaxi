package com.taxi.speedy.company.repository.spring_jdbc.row_mapper;

import com.taxi.speedy.company.model.TaxiDispatcher;
import com.taxi.speedy.company.model.TaxiDispatcherOrder;
import com.taxi.speedy.company.model.User;
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

        User user = new User();
        user.setId(resultSet.getInt("u_id"));
        user.setName(resultSet.getString("u_name"));
        user.setEmail(resultSet.getString("u_email"));
        user.setPassword(resultSet.getString("u_password"));
        user.setPhone(resultSet.getString("u_phone"));
        user.setAddress(resultSet.getString("u_address"));
        user.setRegistered(resultSet.getTimestamp("u_registered"));
        user.setEnabled(resultSet.getBoolean("u_enabled"));

        TaxiDispatcher taxiDispatcher = new TaxiDispatcher();
        taxiDispatcher.setId(resultSet.getInt("td_id"));        //taxiDispatcher.setId(resultSet.getInt(1));
        taxiDispatcher.setIdUser(user);

        TaxiDispatcherOrder taxiDispatcherOrder = new TaxiDispatcherOrder();
        taxiDispatcherOrder.setId(resultSet.getInt("tdo_id"));                         //taxiDispatcherOrder.setId(resultSet.getInt(1));
        if (resultSet.getTimestamp("dto_date_time_order") != null)
            taxiDispatcherOrder.setDateTimeOrder(resultSet.getTimestamp("dto_date_time_order").toLocalDateTime());
        //taxiDispatcherOrder.setIdTaxiDispatcher((TaxiDispatcher) resultSet.getObject(3));
        taxiDispatcherOrder.setIdTaxiDispatcher(taxiDispatcher);
        taxiDispatcherOrder.setUserName(resultSet.getString("tdo_user_name"));
        taxiDispatcherOrder.setUserPhone(resultSet.getString("tdo_user_phone"));
        taxiDispatcherOrder.setAddressDeparture(resultSet.getString("tdo_address_departure"));
        taxiDispatcherOrder.setAddressArrival(resultSet.getString("tdo_address_arrival"));
        if (resultSet.getTimestamp("tdo_start_date") != null)
            taxiDispatcherOrder.setStartDate(resultSet.getTimestamp("tdo_start_date").toLocalDateTime());
        if (resultSet.getTimestamp("tdo_end_date") != null)
            taxiDispatcherOrder.setEndDate(resultSet.getTimestamp("tdo_end_date").toLocalDateTime());
        taxiDispatcherOrder.setFulfilled(resultSet.getInt("tdo_fulfilled"));

        return taxiDispatcherOrder;
    }
}
