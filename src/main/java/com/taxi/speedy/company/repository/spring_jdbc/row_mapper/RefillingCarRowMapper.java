package com.taxi.speedy.company.repository.spring_jdbc.row_mapper;

import com.taxi.speedy.company.model.RefillingCar;
import com.taxi.speedy.company.model.UserBankCard;
import com.taxi.speedy.company.model.UserVehicle;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

//RowMapper <T> - используется JdbcTemplate для отображения строк ResultSet для каждой строки.
//Реализации этого интерфейса выполняют фактическую работу по отображению каждой строки в объект результата
public class RefillingCarRowMapper implements RowMapper<RefillingCar> {
    //Класс ResultSet представляет результирующий набор данных и обеспечивает приложению построчный доступ
    // к результатам запросов. При обработке запроса ResultSet поддерживает указатель на текущую обрабатываемую строку.
    @Override
    public RefillingCar mapRow(ResultSet resultSet, int i) throws SQLException {
        //Методы ResultSet.getXXX предоставляют доступ к значениям в колонках в текущей строке.
        // В пределах одной строки значения могут быть считаны в любом порядке
        RefillingCar refillingCar = new RefillingCar();
        refillingCar.setId(resultSet.getInt(1));                            //refillingCar.setId(resultSet.getInt("id"));
        refillingCar.setIdUserVehicle((UserVehicle) resultSet.getObject(2));
        refillingCar.setIdUserBankCard((UserBankCard) resultSet.getObject(3));
        //refillingCar.setDateTime(resultSet.getTimestamp(4).toLocalDateTime());
        refillingCar.setDateTime(resultSet.getObject(4, LocalDateTime.class));
        refillingCar.setLiter(resultSet.getDouble(5));
        refillingCar.setPricePerLiter(resultSet.getDouble(6));
        refillingCar.setPaymentOfRefueling(resultSet.getDouble(7));

        return refillingCar;
    }
}
