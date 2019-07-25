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
        TaxiDispatcher taxiDispatcher = new TaxiDispatcher();
        taxiDispatcher.setId(resultSet.getInt(1));                  //taxiDispatcher.setId(resultSet.getInt("id"));
        taxiDispatcher.setIdUser((User) resultSet.getObject(2));

        return taxiDispatcher;
    }
}
