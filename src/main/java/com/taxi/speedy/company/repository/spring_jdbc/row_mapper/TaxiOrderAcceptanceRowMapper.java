package com.taxi.speedy.company.repository.spring_jdbc.row_mapper;

import com.taxi.speedy.company.model.TaxiDispatcherOrder;
import com.taxi.speedy.company.model.TaxiOrderAcceptance;
import com.taxi.speedy.company.model.TaxiUserOrder;
import com.taxi.speedy.company.model.UserVehicle;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

//RowMapper <T> - используется JdbcTemplate для отображения строк ResultSet для каждой строки.
//Реализации этого интерфейса выполняют фактическую работу по отображению каждой строки в объект результата
public class TaxiOrderAcceptanceRowMapper implements RowMapper<TaxiOrderAcceptance> {
    //Класс ResultSet представляет результирующий набор данных и обеспечивает приложению построчный доступ
    // к результатам запросов. При обработке запроса ResultSet поддерживает указатель на текущую обрабатываемую строку.
    @Override
    public TaxiOrderAcceptance mapRow(ResultSet resultSet, int i) throws SQLException {
        //Методы ResultSet.getXXX предоставляют доступ к значениям в колонках в текущей строке.
        // В пределах одной строки значения могут быть считаны в любом порядке
        TaxiOrderAcceptance taxiOrderAcceptance = new TaxiOrderAcceptance();
        taxiOrderAcceptance.setId(resultSet.getInt(1));                     //taxiOrderAcceptance.setId(resultSet.getInt("id"));
        taxiOrderAcceptance.setIdUserVehicle((UserVehicle) resultSet.getObject(2));
        taxiOrderAcceptance.setIdTaxiDispatcherOrder((TaxiDispatcherOrder) resultSet.getObject(3));
        taxiOrderAcceptance.setIdTaxiUserOrder((TaxiUserOrder) resultSet.getObject(4));
        taxiOrderAcceptance.setExecutionStatus(resultSet.getInt(5));
        taxiOrderAcceptance.setAdoptionStatus(resultSet.getInt(6));

        return taxiOrderAcceptance;
    }
}
