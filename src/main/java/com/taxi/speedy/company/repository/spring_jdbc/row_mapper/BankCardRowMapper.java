package com.taxi.speedy.company.repository.spring_jdbc.row_mapper;

import com.taxi.speedy.company.model.BankCard;
import com.taxi.speedy.company.model.TypeBankCard;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

//RowMapper <T> - используется JdbcTemplate для отображения строк ResultSet для каждой строки.
//Реализации этого интерфейса выполняют фактическую работу по отображению каждой строки в объект результата
public class BankCardRowMapper implements RowMapper<BankCard> {
    //Класс ResultSet представляет результирующий набор данных и обеспечивает приложению построчный доступ
    // к результатам запросов. При обработке запроса ResultSet поддерживает указатель на текущую обрабатываемую строку.
    @Override
    public BankCard mapRow(ResultSet resultSet, int i) throws SQLException {
        //Методы ResultSet.getXXX предоставляют доступ к значениям в колонках в текущей строке.
        // В пределах одной строки значения могут быть считаны в любом порядке
        BankCard bankCard = new BankCard();
        bankCard.setId(resultSet.getInt(1));                //bankCard.setId(resultSet.getInt("id"));
        bankCard.setBankCardNmber(resultSet.getString(2));
        bankCard.setIdTypeBankCard((TypeBankCard) resultSet.getObject(3));
        bankCard.setMoneyBalance(resultSet.getDouble(4));
        bankCard.setIsActive(resultSet.getInt(5));

        return bankCard;
    }
}
