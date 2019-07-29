package com.taxi.speedy.company.repository.spring_jdbc.row_mapper;

import com.taxi.speedy.company.model.TaxiJobStatus;
import com.taxi.speedy.company.model.TaxiUserVehicleInfo;
import com.taxi.speedy.company.model.UserVehicle;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

//RowMapper <T> - используется JdbcTemplate для отображения строк ResultSet для каждой строки.
//Реализации этого интерфейса выполняют фактическую работу по отображению каждой строки в объект результата
public class TaxiUserVehicleInfoRowMapper implements RowMapper<TaxiUserVehicleInfo> {
    //Класс ResultSet представляет результирующий набор данных и обеспечивает приложению построчный доступ
    // к результатам запросов. При обработке запроса ResultSet поддерживает указатель на текущую обрабатываемую строку.
    @Override
    public TaxiUserVehicleInfo mapRow(ResultSet resultSet, int i) throws SQLException {
        //Методы ResultSet.getXXX предоставляют доступ к значениям в колонках в текущей строке.
        // В пределах одной строки значения могут быть считаны в любом порядке
        TaxiUserVehicleInfo taxiUserVehicleInfo = new TaxiUserVehicleInfo();
        taxiUserVehicleInfo.setId(resultSet.getInt(1));                             //taxiUserVehicleInfo.setId(resultSet.getInt("id"));
        taxiUserVehicleInfo.setIdUserVehicle((UserVehicle) resultSet.getObject(2));
        taxiUserVehicleInfo.setIdTaxiJobStatus((TaxiJobStatus) resultSet.getObject(3));

        return taxiUserVehicleInfo;
    }
}
