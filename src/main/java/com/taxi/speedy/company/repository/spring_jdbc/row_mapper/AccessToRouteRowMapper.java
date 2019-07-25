package com.taxi.speedy.company.repository.spring_jdbc.row_mapper;

import com.taxi.speedy.company.model.AccessToRoute;
import com.taxi.speedy.company.model.UserState;
import com.taxi.speedy.company.model.UserVehicle;
import com.taxi.speedy.company.model.VehicleState;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

//RowMapper <T> - используется JdbcTemplate для отображения строк ResultSet для каждой строки.
//Реализации этого интерфейса выполняют фактическую работу по отображению каждой строки в объект результата
public class AccessToRouteRowMapper implements RowMapper<AccessToRoute> {
    //Класс ResultSet представляет результирующий набор данных и обеспечивает приложению построчный доступ
    // к результатам запросов. При обработке запроса ResultSet поддерживает указатель на текущую обрабатываемую строку.
    @Override
    public AccessToRoute mapRow(ResultSet resultSet, int i) throws SQLException {
        //Методы ResultSet.getXXX предоставляют доступ к значениям в колонках в текущей строке.
        // В пределах одной строки значения могут быть считаны в любом порядке
        AccessToRoute accessToRoute = new AccessToRoute();
        accessToRoute.setId(resultSet.getInt(1));                       //accessToRoute.setId(resultSet.getInt("id"));
        accessToRoute.setChecksDateTime(resultSet.getObject(2, LocalDateTime.class));
        //accessToRoute.setChecksDateTime(resultSet.getTimestamp(2).toLocalDateTime());
        accessToRoute.setIdUserVehicle((UserVehicle) resultSet.getObject(3));
        accessToRoute.setIdUserState((UserState) resultSet.getObject(4));
        accessToRoute.setIdVehicleState((VehicleState) resultSet.getObject(5));
        accessToRoute.setIsAccess(resultSet.getInt(6));

        return accessToRoute;
    }
}
