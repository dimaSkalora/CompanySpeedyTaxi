package com.taxi.speedy.company.repository.spring_jdbc.row_mapper;

import com.taxi.speedy.company.model.BankCard;
import com.taxi.speedy.company.model.User;
import com.taxi.speedy.company.model.UserBankCard;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

//RowMapper <T> - используется JdbcTemplate для отображения строк ResultSet для каждой строки.
//Реализации этого интерфейса выполняют фактическую работу по отображению каждой строки в объект результата
public class UserBankCardRowMapper implements RowMapper<UserBankCard> {
    //Класс ResultSet представляет результирующий набор данных и обеспечивает приложению построчный доступ
    // к результатам запросов. При обработке запроса ResultSet поддерживает указатель на текущую обрабатываемую строку.
    @Override
    public UserBankCard mapRow(ResultSet resultSet, int i) throws SQLException {
        //Методы ResultSet.getXXX предоставляют доступ к значениям в колонках в текущей строке.
        // В пределах одной строки значения могут быть считаны в любом порядке
        UserBankCard userBankCard = new UserBankCard();
        userBankCard.setId(resultSet.getInt(1));                    //userBankCard.setId(resultSet.getInt("id"));
        userBankCard.setIdUser((User) resultSet.getObject(2));
        userBankCard.setIdBankCard((BankCard) resultSet.getObject(3));

        return userBankCard;
    }
}
