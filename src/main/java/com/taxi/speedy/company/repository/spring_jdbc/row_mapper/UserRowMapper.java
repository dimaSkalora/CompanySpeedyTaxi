package com.taxi.speedy.company.repository.spring_jdbc.row_mapper;

import com.taxi.speedy.company.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

//RowMapper <T> - используется JdbcTemplate для отображения строк ResultSet для каждой строки.
//Реализации этого интерфейса выполняют фактическую работу по отображению каждой строки в объект результата
public class UserRowMapper implements RowMapper<User> {
    //Класс ResultSet представляет результирующий набор данных и обеспечивает приложению построчный доступ
    // к результатам запросов. При обработке запроса ResultSet поддерживает указатель на текущую обрабатываемую строку.
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        //Методы ResultSet.getXXX предоставляют доступ к значениям в колонках в текущей строке.
        // В пределах одной строки значения могут быть считаны в любом порядке
        User user = new User();
        user.setId(resultSet.getInt("id"));                  //user.setId(resultSet.getInt(1));
        user.setName(resultSet.getString("name"));           //user.setName(resultSet.getString(2));
        user.setEmail(resultSet.getString("email"));         // user.setEmail(resultSet.getString(3));
        user.setPassword(resultSet.getString("password"));
        user.setPhone(resultSet.getString("phone"));
        user.setAddress(resultSet.getString("address"));
        //user.setRoles(resultSet.getArray("roles"));
        user.setRegistered(resultSet.getDate("registered"));
        user.setEnabled(resultSet.getBoolean("enabled"));

        return user;
    }
}
