package com.taxi.speedy.company.repository.spring_jdbc.row_mapper;

import com.taxi.speedy.company.model.BankCard;
import com.taxi.speedy.company.model.DepartmentCompany;
import com.taxi.speedy.company.model.PaySheet;
import com.taxi.speedy.company.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

//RowMapper <T> - используется JdbcTemplate для отображения строк ResultSet для каждой строки.
//Реализации этого интерфейса выполняют фактическую работу по отображению каждой строки в объект результата
public class PaySheetRowMapper implements RowMapper<PaySheet> {
    //Класс ResultSet представляет результирующий набор данных и обеспечивает приложению построчный доступ
    // к результатам запросов. При обработке запроса ResultSet поддерживает указатель на текущую обрабатываемую строку.
    @Override
    public PaySheet mapRow(ResultSet resultSet, int i) throws SQLException {
        //Методы ResultSet.getXXX предоставляют доступ к значениям в колонках в текущей строке.
        // В пределах одной строки значения могут быть считаны в любом порядке
        PaySheet paySheet = new PaySheet();
        paySheet.setId(resultSet.getInt(1));                        //paySheet.setId(resultSet.getInt("id"));
        paySheet.setIdUser((User) resultSet.getObject(2));
        paySheet.setIdBankCard((BankCard) resultSet.getObject(3));
        paySheet.setIdDepartmentCompany((DepartmentCompany) resultSet.getObject(4));
        paySheet.setMonthYear(resultSet.getString(5));
        paySheet.setPrepayment(resultSet.getDouble(6));
        paySheet.setSalary(resultSet.getDouble(7));
        paySheet.setPremium(resultSet.getDouble(8));
        paySheet.setHospitals(resultSet.getDouble(9));
        paySheet.setHoliday(resultSet.getDouble(10));
        paySheet.setAmountWithoutTaxes(resultSet.getDouble(11));
        paySheet.setNdfl(resultSet.getDouble(12));
        paySheet.setEsv(resultSet.getDouble(13));
        paySheet.setVs(resultSet.getDouble(14));
        paySheet.setAmountWithTexas(resultSet.getDouble(15));
        paySheet.setNormHoursWorked(resultSet.getInt(16));
        paySheet.setHoursWorked(resultSet.getInt(17));
        paySheet.setHours_holiday(resultSet.getInt(18));

        return paySheet;
    }
}
