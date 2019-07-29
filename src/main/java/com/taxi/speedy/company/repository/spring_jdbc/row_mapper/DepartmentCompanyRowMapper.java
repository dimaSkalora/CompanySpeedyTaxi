package com.taxi.speedy.company.repository.spring_jdbc.row_mapper;

import com.taxi.speedy.company.model.DepartmentCompany;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

//RowMapper <T> - используется JdbcTemplate для отображения строк ResultSet для каждой строки.
//Реализации этого интерфейса выполняют фактическую работу по отображению каждой строки в объект результата
public class DepartmentCompanyRowMapper implements RowMapper<DepartmentCompany> {
    //Класс ResultSet представляет результирующий набор данных и обеспечивает приложению построчный доступ
    // к результатам запросов. При обработке запроса ResultSet поддерживает указатель на текущую обрабатываемую строку.
    @Override
    public DepartmentCompany mapRow(ResultSet resultSet, int i) throws SQLException {
        //Методы ResultSet.getXXX предоставляют доступ к значениям в колонках в текущей строке.
        // В пределах одной строки значения могут быть считаны в любом порядке
        DepartmentCompany departmentCompany = new DepartmentCompany();
        departmentCompany.setId(resultSet.getInt(1));                                   //departmentCompany.setId(resultSet.getInt("id"));
        departmentCompany.setNameDC(resultSet.getString(2));

        return departmentCompany;
    }
}
