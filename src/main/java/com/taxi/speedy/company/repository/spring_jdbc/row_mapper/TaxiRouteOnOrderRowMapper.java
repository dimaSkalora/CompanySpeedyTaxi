package com.taxi.speedy.company.repository.spring_jdbc.row_mapper;

import com.taxi.speedy.company.model.TaxiOrderAcceptance;
import com.taxi.speedy.company.model.TaxiRouteOnOrder;
import com.taxi.speedy.company.model.UserBankCard;
import com.taxi.speedy.company.model.UserVehicle;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

//RowMapper <T> - используется JdbcTemplate для отображения строк ResultSet для каждой строки.
//Реализации этого интерфейса выполняют фактическую работу по отображению каждой строки в объект результата
public class TaxiRouteOnOrderRowMapper implements RowMapper<TaxiRouteOnOrder> {
    //Класс ResultSet представляет результирующий набор данных и обеспечивает приложению построчный доступ
    // к результатам запросов. При обработке запроса ResultSet поддерживает указатель на текущую обрабатываемую строку.
    @Override
    public TaxiRouteOnOrder mapRow(ResultSet resultSet, int i) throws SQLException {
        //Методы ResultSet.getXXX предоставляют доступ к значениям в колонках в текущей строке.
        // В пределах одной строки значения могут быть считаны в любом порядке
        TaxiRouteOnOrder taxiRouteOnOrder = new TaxiRouteOnOrder();
        taxiRouteOnOrder.setId(resultSet.getInt(1));                                //taxiRouteOnOrder.setId(resultSet.getInt("id"));
        taxiRouteOnOrder.setIdUserVehicle((UserVehicle) resultSet.getObject(2));
        taxiRouteOnOrder.setIdUserBankCard((UserBankCard) resultSet.getObject(3));
        taxiRouteOnOrder.setIdTaxiOrderAcceptance((TaxiOrderAcceptance) resultSet.getRowId(4));
        taxiRouteOnOrder.setLanding(resultSet.getInt(5));
        taxiRouteOnOrder.setTariffPerKilometer(resultSet.getDouble(6));
        taxiRouteOnOrder.setDistance(resultSet.getDouble(7));
        taxiRouteOnOrder.setFarePayment(resultSet.getDouble(8));
        taxiRouteOnOrder.setFuelConsuption(resultSet.getDouble(9));

        return taxiRouteOnOrder;
    }
}
