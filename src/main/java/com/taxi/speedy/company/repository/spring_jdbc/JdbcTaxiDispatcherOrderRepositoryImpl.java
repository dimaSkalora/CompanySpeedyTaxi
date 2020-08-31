package com.taxi.speedy.company.repository.spring_jdbc;

import com.taxi.speedy.company.model.TaxiDispatcherOrder;
import com.taxi.speedy.company.repository.TaxiDispatcherOrderRepository;
import com.taxi.speedy.company.repository.spring_jdbc.row_mapper.TaxiDispatcherOrderRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository("jdbcTaxiDispatcherOrderRepositoryImpl")
public class JdbcTaxiDispatcherOrderRepositoryImpl implements TaxiDispatcherOrderRepository {

    /*
     *  JdbcTemplate - это мощный механизм для подключения к базе данных и выполнения SQL-запросов.
     *  Мы можем выполнять все операции с базой данных с помощью класса JdbcTemplate, такие как вставка,
     *  обновление, удаление и извлечение данных из базы данных.
     */
    private JdbcTemplate jdbcTemplate;
    //способ вставки данных по именованному параметру. Таким образом мы используем имена вместо? (Знак вопроса)
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    /* SimpleJdbcInsert - многопоточный, многоразовый объект, обеспечивающий удобные возможности вставки для таблицы.
        Он обеспечивает обработку метаданных, чтобы упростить код, необходимый для построения основного оператора insert.
        Все, что вам нужно указать, - это имя таблицы и Карта, содержащая имена столбцов и значения столбца.
     */
    private SimpleJdbcInsert simpleJdbcInsert;

    //Статический метод фабрики для создания нового BeanPropertyRowMapper (с отображенным классом,
    // указанным только один раз).
    //private static final RowMapper<TaxiDispatcherOrder> ROW_MAPPER_TD_ORDER = BeanPropertyRowMapper.newInstance(TaxiDispatcherOrder.class);
    private static BeanPropertyRowMapper<TaxiDispatcherOrder> ROW_MAPPER_TD_ORDER = BeanPropertyRowMapper.newInstance(TaxiDispatcherOrder.class);

    private final String sqlQuery ="select tdo.id as tdo_id, tdo.date_time_order as dto_date_time_order, tdo.id_taxi_dispatcher as tdo_id_td,\n" +
            " tdo.user_name as tdo_user_name, tdo.user_phone as tdo_user_phone,\n" +
            " tdo.address_departure as tdo_address_departure, tdo.address_arrival as tdo_address_arrival,\n" +
            " tdo.start_date as tdo_start_date, tdo.end_date as tdo_end_date, tdo.fulfilled as tdo_fulfilled,\n" +
            " td.id as td_id, td.id_user as td_id_user,\n" +
            " u.id as u_id, u.name as u_name, u.email as u_email, \n" +
            " u.password as u_password, u.phone as u_phone, u.address as u_address,\n" +
            " u.registered as u_registered, u.enabled as u_enabled\n"+
            " from taxi_dispatcher_orders tdo \n" +
            " left join taxi_dispatchers td on tdo.id_taxi_dispatcher = td.id \n" +
            " left join users u on td.id_user = u.id \n";

    @Autowired
    public JdbcTaxiDispatcherOrderRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                //Укажите имя таблицы, которое будет использоваться для вставки.
                .withTableName("taxi_dispatcher_orders")
                //Укажите имена любых столбцов, в которых есть автоматически сгенерированные ключи.
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public TaxiDispatcherOrder save(TaxiDispatcherOrder taxiDispatcherOrder) {
        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id",taxiDispatcherOrder.getId())
                .addValue("dateTimeOrder",taxiDispatcherOrder.getDateTimeOrder())
                .addValue("idTaxiDispatcher",taxiDispatcherOrder.getIdTaxiDispatcher().getId())
                .addValue("userName",taxiDispatcherOrder.getUserName())
                .addValue("userPhone",taxiDispatcherOrder.getUserPhone())
                .addValue("addressDeparture",taxiDispatcherOrder.getAddressDeparture())
                .addValue("addressArrival",taxiDispatcherOrder.getAddressArrival())
                .addValue("startDate",taxiDispatcherOrder.getStartDate())
                .addValue("endDate",taxiDispatcherOrder.getEndDate())
                .addValue("fulfilled",taxiDispatcherOrder.getFulfilled());

         /*  BeanPropertySqlParameterSource - анализирует переданный ему объект и для каждого свойства
            объекта создаёт параметр с именем свойства и его значением.
         */
        BeanPropertySqlParameterSource beanPropertySqlParameterSource = new BeanPropertySqlParameterSource(taxiDispatcherOrder);

        if(taxiDispatcherOrder.isNew()){
            //Number newKey = simpleJdbcInsert.executeAndReturnKey(beanPropertySqlParameterSource);
            Number newKey = simpleJdbcInsert.executeAndReturnKey(parameterSource);

            taxiDispatcherOrder.setId(newKey.intValue());
        }else if (namedParameterJdbcTemplate.update("update taxi_dispatcher_orders set date_time_order=:dateTimeOrder, " +
                " id_taxi_dispatcher=:idTaxiDispatcher, user_name=:userName, user_phone=:userPhone, address_departure=:addressDeparture," +
                " address_arrival=:addressArrival, start_date=:startDate, end_date=:endDate, " +
                " fulfilled=:fulfilled",parameterSource) == 0){
            return taxiDispatcherOrder;
        }

        return taxiDispatcherOrder;
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("delete from taxi_dispatcher_orders as tdo where tdo.id=?",id) !=0 ;
    }

    @Override
    public TaxiDispatcherOrder get(int id) {
        // String sqlGet =  sqlQuery +" where tdo.id=:id";
        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
       /* MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("id",id);
        List<TaxiDispatcherOrder> taxiDispatcherOrders = namedParameterJdbcTemplate.query(sqlGet,
                mapSqlParameterSource, new TaxiDispatcherOrderRowMapper());*/

       String sqlGet =  sqlQuery +" where tdo.id=?";
       List<TaxiDispatcherOrder> taxiDispatcherOrders = jdbcTemplate.query(sqlGet,new TaxiDispatcherOrderRowMapper(),id);

        return DataAccessUtils.singleResult(taxiDispatcherOrders);//Возвращает один объект результата из данной коллекции.
    }

    @Override
    public List<TaxiDispatcherOrder> getAll() {
        return jdbcTemplate.query(sqlQuery, new TaxiDispatcherOrderRowMapper());
    }

    @Override
    public List<TaxiDispatcherOrder> getByIdTaxiDispatcher(int idTaxiDispatcher) {
/*        String sqlByIdTaxiDispatcher = sqlQuery + " where tdo.id_taxi_dispatcher=?";
        List<TaxiDispatcherOrder> taxiDispatcherOrders = jdbcTemplate.query(sqlByIdTaxiDispatcher,new TaxiDispatcherOrderRowMapper(),idTaxiDispatcher);*/

        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("idTaxiDispatcher",idTaxiDispatcher);

        String sqlByIdTaxiDispatcher = sqlQuery+" where tdo.id_taxi_dispatcher=:idTaxiDispatcher";
        List<TaxiDispatcherOrder> taxiDispatcherOrders = namedParameterJdbcTemplate.query(sqlByIdTaxiDispatcher,parameterSource,new TaxiDispatcherOrderRowMapper());

        return taxiDispatcherOrders;
    }

    @Override
    public List<TaxiDispatcherOrder> getByAddressDeparture(String addressDeparture) {
        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("addressDeparture",addressDeparture);

        String sqlByAddressDeparture = sqlQuery+" where address_departure=:addressDeparture";
        List<TaxiDispatcherOrder> taxiDispatcherOrders = namedParameterJdbcTemplate.query(sqlByAddressDeparture,parameterSource,new TaxiDispatcherOrderRowMapper());
        return taxiDispatcherOrders;
    }

    @Override
    public List<TaxiDispatcherOrder> getByAddressArrival(String addressArrival) {
        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("addressArrival",addressArrival);

        String sqlByAddressDeparture = sqlQuery+" where address_arrival=:addressArrival";
        List<TaxiDispatcherOrder> taxiDispatcherOrders = namedParameterJdbcTemplate.query(sqlByAddressDeparture,parameterSource,new TaxiDispatcherOrderRowMapper());
        return taxiDispatcherOrders;
    }

    @Override
    public List<TaxiDispatcherOrder> getByUserName(String userName) {
        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("userName",userName);

        String sqlByAddressDeparture = sqlQuery+" where user_name=:userName";
        List<TaxiDispatcherOrder> taxiDispatcherOrders = namedParameterJdbcTemplate.query(sqlByAddressDeparture,parameterSource,new TaxiDispatcherOrderRowMapper());
        return taxiDispatcherOrders;
    }

    @Override
    public List<TaxiDispatcherOrder> getByUserPhone(String userPhone) {
        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("userPhone",userPhone);

        String sqlByAddressDeparture = sqlQuery+" where user_phone=:userPhone";
        List<TaxiDispatcherOrder> taxiDispatcherOrders = namedParameterJdbcTemplate.query(sqlByAddressDeparture,parameterSource,new TaxiDispatcherOrderRowMapper());
        return taxiDispatcherOrders;
    }

    @Override
    public List<TaxiDispatcherOrder> getByFulfilled(int fulfilled) {
        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("fulfilled",fulfilled);

        String sqlByAddressDeparture = sqlQuery+" where fulfilled=:fulfilled";
        List<TaxiDispatcherOrder> taxiDispatcherOrders = namedParameterJdbcTemplate.query(sqlByAddressDeparture,parameterSource,new TaxiDispatcherOrderRowMapper());
        return taxiDispatcherOrders;
    }

    @Override
    public List<TaxiDispatcherOrder> getByBetweenStartDate(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        String sqlByAddressDeparture = sqlQuery+" where tdo.start_date between ? and ? \n" +
                "ORDER BY tdo.start_date DESC"; /*DESC - сортируем по убыванию*/
        List<TaxiDispatcherOrder> taxiDispatcherOrders = jdbcTemplate.query(sqlByAddressDeparture,
                new TaxiDispatcherOrderRowMapper(),startDateTime,endDateTime);

        return taxiDispatcherOrders;
    }

    @Override
    public List<TaxiDispatcherOrder> getByBetweenEndDate(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("startDate",startDateTime)
                .addValue("endDate",endDateTime);

        String sqlByAddressDeparture = sqlQuery+" where tdo.end_date between :startDate and :endDate \n" +
                "ORDER BY tdo.end_date DESC"; /*DESC - сортируем по убыванию*/
        List<TaxiDispatcherOrder> taxiDispatcherOrders = namedParameterJdbcTemplate.query(sqlByAddressDeparture,parameterSource,new TaxiDispatcherOrderRowMapper());
        return taxiDispatcherOrders;
    }

    @Override
    public List<TaxiDispatcherOrder> getFilterTaxiDispatcherOrder(TaxiDispatcherOrder taxiDispatcherOrder) {
        return getFilterTaxiDispatcherOrder(taxiDispatcherOrder,null);
    }

    @Override
    public List<TaxiDispatcherOrder> getFilterTaxiDispatcherOrder(TaxiDispatcherOrder taxiDispatcherOrder, String sqlCondition) {
        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        if (!taxiDispatcherOrder.isNew())
            parameterSource.addValue("id",taxiDispatcherOrder.getId());
        if (taxiDispatcherOrder.getDateTimeOrder() != null)
            parameterSource.addValue("dateTimeOrder",taxiDispatcherOrder.getDateTimeOrder());
        if (taxiDispatcherOrder.getIdTaxiDispatcher() != null)
            parameterSource.addValue("idTaxiDispatcher",taxiDispatcherOrder.getIdTaxiDispatcher().getId());
        if (taxiDispatcherOrder.getUserName() != null)
            parameterSource.addValue("userName",taxiDispatcherOrder.getUserName());
        if (taxiDispatcherOrder.getUserPhone() != null)
            parameterSource.addValue("userPhone",taxiDispatcherOrder.getUserPhone());
        if (taxiDispatcherOrder.getAddressDeparture() != null)
            parameterSource.addValue("addressDeparture",taxiDispatcherOrder.getAddressDeparture());
        if (taxiDispatcherOrder.getAddressArrival() != null)
            parameterSource.addValue("addressArrival",taxiDispatcherOrder.getAddressArrival());
        if (taxiDispatcherOrder.getStartDate() != null)
            parameterSource.addValue("startDate",taxiDispatcherOrder.getStartDate());
        if (taxiDispatcherOrder.getEndDate() != null)
            parameterSource.addValue("endDate",taxiDispatcherOrder.getEndDate());
        if (taxiDispatcherOrder.getFulfilled() != null)
            parameterSource.addValue("fulfilled",taxiDispatcherOrder.getFulfilled());

        String sqlFilterTaxiDispatcherOrder = sqlQuery;

        int count = 0;
        for(Map.Entry<String,Object> mapEntry : parameterSource.getValues().entrySet()){
            String paramName = mapEntry.getKey();
            if(count==0){
                if (paramName.equals("id"))
                    sqlFilterTaxiDispatcherOrder += " where tdo.id=:id \n";
                if (paramName.equals("dateTimeOrder"))
                    sqlFilterTaxiDispatcherOrder += " where tdo.date_time_order=:dateTimeOrder \n";
                if (paramName.equals("idTaxiDispatcher"))
                    sqlFilterTaxiDispatcherOrder += " where tdo.id_taxi_dispatcher=:idTaxiDispatcher \n";
                if (paramName.equals("userName"))
                    sqlFilterTaxiDispatcherOrder += " where tdo.user_name=:userName \n";
                if (paramName.equals("userPhone"))
                    sqlFilterTaxiDispatcherOrder += " where tdo.user_phone=:userPhone \n";
                if (paramName.equals("addressDeparture"))
                    sqlFilterTaxiDispatcherOrder += " where tdo.address_departure=:addressDeparture \n";
                if (paramName.equals("addressArrival"))
                    sqlFilterTaxiDispatcherOrder += " where tdo.address_arrival=:addressArrival \n";
                if (paramName.equals("startDate"))
                    sqlFilterTaxiDispatcherOrder += " where tdo.start_date=:startDate \n";
                if (paramName.equals("endDate"))
                    sqlFilterTaxiDispatcherOrder += " where tdo.end_date=:endDate \n";
                if (paramName.equals("fulfilled"))
                    sqlFilterTaxiDispatcherOrder += " where tdo.fulfilled=:fulfilled \n";
            }else {
                if (paramName.equals("id"))
                    sqlFilterTaxiDispatcherOrder += " and tdo.id=:id \n";
                if (paramName.equals("dateTimeOrder"))
                    sqlFilterTaxiDispatcherOrder += " and tdo.date_time_order=:dateTimeOrder \n";
                if (paramName.equals("idTaxiDispatcher"))
                    sqlFilterTaxiDispatcherOrder += " and tdo.id_taxi_dispatcher=:idTaxiDispatcher \n";
                if (paramName.equals("userName"))
                    sqlFilterTaxiDispatcherOrder += " and tdo.user_name=:userName \n";
                if (paramName.equals("userPhone"))
                    sqlFilterTaxiDispatcherOrder += " and tdo.user_phone=:userPhone \n";
                if (paramName.equals("addressDeparture"))
                    sqlFilterTaxiDispatcherOrder += " and tdo.address_departure=:addressDeparture \n";
                if (paramName.equals("addressArrival"))
                    sqlFilterTaxiDispatcherOrder += " and tdo.address_arrival=:addressArrival \n";
                if (paramName.equals("startDate"))
                    sqlFilterTaxiDispatcherOrder += " and tdo.start_date=:startDate \n";
                if (paramName.equals("endDate"))
                    sqlFilterTaxiDispatcherOrder += " and tdo.end_date=:endDate \n";
                if (paramName.equals("fulfilled"))
                    sqlFilterTaxiDispatcherOrder += " and tdo.fulfilled=:fulfilled \n";
            }
            count++;
        }

        if(sqlCondition != null)
            if(parameterSource.getParameterNames().length > 0)
                sqlFilterTaxiDispatcherOrder += " and ( "+ sqlCondition+" ) ";
            else
                sqlFilterTaxiDispatcherOrder += " where ( "+ sqlCondition+" ) ";

        List<TaxiDispatcherOrder> taxiDispatcherOrders = namedParameterJdbcTemplate
                .query(sqlFilterTaxiDispatcherOrder, parameterSource, new TaxiDispatcherOrderRowMapper());

        return taxiDispatcherOrders;
    }
}
