package com.taxi.speedy.company.repository.spring_jdbc.row_mapper;

import com.taxi.speedy.company.model.BankCard;
import com.taxi.speedy.company.model.BankCardOperation;
import com.taxi.speedy.company.model.UserBankCard;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

//RowMapper <T> - используется JdbcTemplate для отображения строк ResultSet для каждой строки.
//Реализации этого интерфейса выполняют фактическую работу по отображению каждой строки в объект результата
public class BankCardOperationsRowMapper implements RowMapper<BankCardOperation> {
    //Класс ResultSet представляет результирующий набор данных и обеспечивает приложению построчный доступ
    // к результатам запросов. При обработке запроса ResultSet поддерживает указатель на текущую обрабатываемую строку.
    @Override
    public BankCardOperation mapRow(ResultSet resultSet, int i) throws SQLException {
        //Методы ResultSet.getXXX предоставляют доступ к значениям в колонках в текущей строке.
        // В пределах одной строки значения могут быть считаны в любом порядке
        BankCardOperation bankCardOperation = new BankCardOperation();
        bankCardOperation.setId(resultSet.getInt(1));                   //bankCardOperation.setId(resultSet.getInt("id));
        bankCardOperation.setIdUserBankCard((UserBankCard) resultSet.getObject(2));
        bankCardOperation.setDateTimeArrivelMoney(resultSet.getObject(3, LocalDateTime.class));
        //bankCardOperation.setDateTimeArrivelMoney(resultSet.getTimestamp(3).toLocalDateTime());
        bankCardOperation.setIdBankCardArrivel((BankCard) resultSet.getObject(4));
        bankCardOperation.setArrivalMoney(resultSet.getDouble(5));
        bankCardOperation.setDateTimeSpendingMoney(resultSet.getObject(6, LocalDateTime.class));
        bankCardOperation.setIdBankCardSpending((BankCard) resultSet.getObject(7));
        bankCardOperation.setSpendingMoney(resultSet.getDouble(8));
        bankCardOperation.setMoneyBalance(resultSet.getDouble(9));

        return bankCardOperation;
    }
}
