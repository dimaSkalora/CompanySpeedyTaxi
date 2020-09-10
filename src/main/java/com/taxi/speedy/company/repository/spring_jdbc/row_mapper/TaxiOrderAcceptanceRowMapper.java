package com.taxi.speedy.company.repository.spring_jdbc.row_mapper;

import com.taxi.speedy.company.model.TaxiDispatcherOrder;
import com.taxi.speedy.company.model.TaxiOrderAcceptance;
import com.taxi.speedy.company.model.TaxiUserOrder;
import com.taxi.speedy.company.model.UserVehicle;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

//RowMapper <T> - используется JdbcTemplate для отображения строк ResultSet для каждой строки.
//Реализации этого интерфейса выполняют фактическую работу по отображению каждой строки в объект результата
public class TaxiOrderAcceptanceRowMapper implements RowMapper<TaxiOrderAcceptance> {
    //Класс ResultSet представляет результирующий набор данных и обеспечивает приложению построчный доступ
    // к результатам запросов. При обработке запроса ResultSet поддерживает указатель на текущую обрабатываемую строку.
    @Override
    public TaxiOrderAcceptance mapRow(ResultSet resultSet, int i) throws SQLException {
        //Методы ResultSet.getXXX предоставляют доступ к значениям в колонках в текущей строке.
        // В пределах одной строки значения могут быть считаны в любом порядке

        /*-----------------------UserVehicle-----------------------------*/
        UserVehicle userVehicle = new UserVehicle();
        userVehicle.setId(resultSet.getInt("uv_id"));                                     //userVehicle.setId(resultSet.getInt(1));
        userVehicle.setStartDate(resultSet.getTimestamp("uv_start_date").toLocalDateTime());      //userVehicle.setStartDate(resultSet.getTimestamp("start_date").toLocalDateTime());
        //userVehicle.setStartDate(resultSet.getObject(2,LocalDateTime.class));                 //userVehicle.setStartDate(resultSet.getObject(2,LocalDateTime.class));
        if (resultSet.getTimestamp("uv_end_date") != null)
            userVehicle.setEndDate(resultSet.getTimestamp("uv_end_date").toLocalDateTime());
        userVehicle.setIsCurrentUserMachine(resultSet.getInt("uv_is_current_user_machine"));

        /*-----------------------TaxiDispatcherOrder-----------------------------*/
        TaxiDispatcherOrder taxiDispatcherOrder = new TaxiDispatcherOrder();
        taxiDispatcherOrder.setId(resultSet.getInt(1));                         //taxiDispatcherOrder.setId(resultSet.getInt("tdo_id"));
        if (resultSet.getTimestamp("dto_date_time_order") != null)
            taxiDispatcherOrder.setDateTimeOrder(resultSet.getTimestamp("dto_date_time_order").toLocalDateTime());
        taxiDispatcherOrder.setUserName(resultSet.getString("tdo_user_name"));
        taxiDispatcherOrder.setUserPhone(resultSet.getString("tdo_user_phone"));
        taxiDispatcherOrder.setAddressDeparture(resultSet.getString("tdo_address_departure"));
        taxiDispatcherOrder.setAddressArrival(resultSet.getString("tdo_address_arrival"));
        if (resultSet.getTimestamp("tdo_start_date") != null)
            taxiDispatcherOrder.setStartDate(resultSet.getTimestamp("tdo_start_date").toLocalDateTime());
        if (resultSet.getTimestamp("tdo_end_date") != null)
            taxiDispatcherOrder.setEndDate(resultSet.getTimestamp("tdo_end_date").toLocalDateTime());
        taxiDispatcherOrder.setFulfilled(resultSet.getInt("tdo_fulfilled"));

        /*-----------------------TaxiUserOrder-----------------------------*/
        TaxiUserOrder taxiUserOrder = new TaxiUserOrder();
        taxiUserOrder.setId(resultSet.getInt("tuo_id"));                       //taxiUserOrder.setId(resultSet.getInt(1));
        if(resultSet.getObject("tuo_date_time_order") != null)
            taxiUserOrder.setDateTimeOrder(resultSet.getObject(2, LocalDateTime.class));
        taxiUserOrder.setAddressDeparture(resultSet.getString("tuo_address_departure"));
        taxiUserOrder.setAddressArrival(resultSet.getString("tuo_address_arrival"));
        taxiUserOrder.setStartDate(resultSet.getObject("tuo_start_date", LocalDateTime.class));
        if(resultSet.getTimestamp("tuo_end_date") != null)
            taxiUserOrder.setEndDate(resultSet.getTimestamp("tuo_end_date").toLocalDateTime());
        taxiUserOrder.setFulfilled(resultSet.getInt("tuo_fulfilled"));


        TaxiOrderAcceptance taxiOrderAcceptance = new TaxiOrderAcceptance();
        taxiOrderAcceptance.setId(resultSet.getInt("toa_id"));                     //taxiOrderAcceptance.setId(resultSet.getInt(1));
        taxiOrderAcceptance.setIdUserVehicle(userVehicle);
        taxiOrderAcceptance.setIdTaxiDispatcherOrder(taxiDispatcherOrder);
        taxiOrderAcceptance.setIdTaxiUserOrder(taxiUserOrder);
        taxiOrderAcceptance.setExecutionStatus(resultSet.getInt("toa_execution_status"));
        taxiOrderAcceptance.setAdoptionStatus(resultSet.getInt("toa_adoption_status"));

        return taxiOrderAcceptance;
    }
}
