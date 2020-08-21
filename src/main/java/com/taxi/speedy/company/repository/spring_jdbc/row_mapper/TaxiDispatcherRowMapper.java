package com.taxi.speedy.company.repository.spring_jdbc.row_mapper;

import com.taxi.speedy.company.model.TaxiDispatcher;
import com.taxi.speedy.company.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

//RowMapper <T> - используется JdbcTemplate для отображения строк ResultSet для каждой строки.
//Реализации этого интерфейса выполняют фактическую работу по отображению каждой строки в объект результата
public class TaxiDispatcherRowMapper implements RowMapper<TaxiDispatcher> {
    //Класс ResultSet представляет результирующий набор данных и обеспечивает приложению построчный доступ
    // к результатам запросов. При обработке запроса ResultSet поддерживает указатель на текущую обрабатываемую строку.
    @Override
    public TaxiDispatcher mapRow(ResultSet resultSet, int i) throws SQLException {
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

        return taxiDispatcher;
    }
}
