package com.taxi.speedy.company.repository.spring_jdbc.row_mapper;

import com.taxi.speedy.company.model.DepartmentCompany;
import com.taxi.speedy.company.model.PayrollAccounting;
import com.taxi.speedy.company.model.TypePayment;
import com.taxi.speedy.company.model.UserBankCard;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

//RowMapper <T> - используется JdbcTemplate для отображения строк ResultSet для каждой строки.
//Реализации этого интерфейса выполняют фактическую работу по отображению каждой строки в объект результата
public class PayrollAccountingRowMapper implements RowMapper<PayrollAccounting> {
    //Класс ResultSet представляет результирующий набор данных и обеспечивает приложению построчный доступ
    // к результатам запросов. При обработке запроса ResultSet поддерживает указатель на текущую обрабатываемую строку.
    @Override
    public PayrollAccounting mapRow(ResultSet resultSet, int i) throws SQLException {
        //Методы ResultSet.getXXX предоставляют доступ к значениям в колонках в текущей строке.
        // В пределах одной строки значения могут быть считаны в любом порядке
        PayrollAccounting payrollAccounting = new PayrollAccounting();
        payrollAccounting.setId(resultSet.getInt(1));                           //payrollAccounting.setId(resultSet.getInt("id"));
        payrollAccounting.setIdDepartmentCompany((DepartmentCompany) resultSet.getObject(2));
        payrollAccounting.setIdUserBankCard((UserBankCard) resultSet.getObject(3));
        payrollAccounting.setMonthYear(resultSet.getString(4));
        payrollAccounting.setNormNumberDaysWorkedMonth(resultSet.getInt(5));
        payrollAccounting.setNumberDaysWorkedMonth(resultSet.getInt(6));
        payrollAccounting.setStakeDays(resultSet.getDouble(7));
        payrollAccounting.setNormHoursWorkedMonth(resultSet.getInt(8));
        payrollAccounting.setHoursWorkedMonthl(resultSet.getInt(9));
        payrollAccounting.setStakeHour(resultSet.getDouble(10));
        //payrollAccounting.setPaymentDateTime(resultSet.getTimestamp(11).toLocalDateTime());
        payrollAccounting.setPaymentDateTime(resultSet.getObject(11, LocalDateTime.class));
        payrollAccounting.setIdTypePayment((TypePayment) resultSet.getObject(12));
        payrollAccounting.setPayout(resultSet.getDouble(13));
        payrollAccounting.setSumPayoutMonth(resultSet.getDouble(14));

        return payrollAccounting;
    }
}
