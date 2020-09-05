package com.taxi.speedy.company.repository.spring_jdbc;

import com.taxi.speedy.company.model.TaxiUserOrder;
import com.taxi.speedy.company.repository.TaxiUserOrderRepository;
import com.taxi.speedy.company.repository.spring_jdbc.row_mapper.TaxiUserOrdersRowMapper;
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
import java.util.List;

@Repository("jdbcTaxiUserOrderRepositoryImpl")
public class JdbcTaxiUserOrderRepositoryImpl implements TaxiUserOrderRepository {

    //Статический метод фабрики для создания нового BeanPropertyRowMapper (с отображенным классом,
    // указанным только один раз).
    //private static final RowMapper<TaxiUserOrder> ROW_MAPPER_TAXI_USER_ORDER = BeanPropertyRowMapper.newInstance(TaxiUserOrder.class);
    private BeanPropertyRowMapper<TaxiUserOrder> ROW_MAPPER_TAXI_USER_ORDER = BeanPropertyRowMapper.newInstance(TaxiUserOrder.class);
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
    private SimpleJdbcInsert jdbcInsert;

    private final String sqlQuery = """ 
                select tuo.id as tuo_id, tuo.date_time_order as tuo_date_time_order, tuo.id_user as tuo_id_user,
                    tuo.address_departure as tuo_address_departure, 
                    tuo.address_arrival as tuo_address_arrival, tuo.start_date as tuo_start_date,
                    tuo.end_date as tuo_end_date, tuo.fulfilled as tuo_fulfilled, 
                    u.id as u_id, u.name as u_name, u.email as u_email,
                    u.password as u_password, u.phone as u_phone, u.address as u_address,
                    u.registered as u_registered, u.enabled as u_enabled 
                from taxi_user_orders tuo 
                left join users u on tuo.id_user = u.id
                """;

    @Autowired
    public JdbcTaxiUserOrderRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        jdbcInsert = new SimpleJdbcInsert(dataSource)
                //Укажите имя таблицы, которое будет использоваться для вставки.
                .withTableName("taxi_user_orders")
                //Укажите имена любых столбцов, в которых есть автоматически сгенерированные ключи.
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public TaxiUserOrder save(TaxiUserOrder taxiUserOrder) {
        final String sqlUpdate = """
                UPDATE taxi_user_orders set date_time_order=:dateTimeOrder, id_user=:idUser,
                        address_departure=:addressDeparture, address_arrival=:addressArrival,
                        start_date=:startDate, end_date=:endDate, fulfilled=:fulfilled
                where id=:id       
                """;
        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id",taxiUserOrder.getId())
                .addValue("dateTimeOrder",taxiUserOrder.getDateTimeOrder())
                .addValue("idUser",taxiUserOrder.getIdUser().getId())
                .addValue("addressDeparture",taxiUserOrder.getAddressDeparture())
                .addValue("addressArrival",taxiUserOrder.getAddressArrival())
                .addValue("startDate",taxiUserOrder.getStartDate())
                .addValue("endDate",taxiUserOrder.getEndDate())
                .addValue("fulfilled",taxiUserOrder.getFulfilled());

        /*  BeanPropertySqlParameterSource - анализирует переданный ему объект и для каждого свойства
            объекта создаёт параметр с именем свойства и его значением.
         */
        BeanPropertySqlParameterSource beanPropertySqlParameterSource = new BeanPropertySqlParameterSource(TaxiUserOrder.class);

        if (taxiUserOrder.isNew()){
            Number newKey = jdbcInsert.executeAndReturnKey(parameterSource);
            //Number newKey = jdbcInsert.executeAndReturnKey(beanPropertySqlParameterSource);

            taxiUserOrder.setId(newKey.intValue());
        }else if ((namedParameterJdbcTemplate.update(sqlUpdate,parameterSource)) != 0){
            return taxiUserOrder;
        }

        return taxiUserOrder;
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("delete from taxi_user_orders tuo where tuo.id=?",id) != 0;
    }

    @Override
    public TaxiUserOrder get(int id) {
       /* String sqlGet = sqlQuery + " where tuo.id=? ";
        List<TaxiUserOrder> list = jdbcTemplate.query(sqlGet, new TaxiUserOrdersRowMapper(),id);*/

        String sqlGet = sqlQuery + " where tuo.id=:id ";
        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        MapSqlParameterSource propertySource = new MapSqlParameterSource()
                .addValue("id",id);
        List<TaxiUserOrder> list = namedParameterJdbcTemplate.query(sqlGet,propertySource, new TaxiUserOrdersRowMapper());

        return DataAccessUtils.singleResult(list);//Возвращает один объект результата из данной коллекции.
    }

    @Override
    public List<TaxiUserOrder> getAllIdUser(int idUser) {
        List<TaxiUserOrder> list;
        String sqlGetAllIdUser;

     /*   //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("idUser",idUser);
        sqlGetAllIdUser = sqlQuery + " where tuo.id_user=:idUser";
        list = namedParameterJdbcTemplate.query(sqlGetAllIdUser,parameterSource,new TaxiUserOrdersRowMapper());*/

        sqlGetAllIdUser = sqlQuery + " where tuo.id_user=? ";
        list=jdbcTemplate.query(sqlGetAllIdUser,new TaxiUserOrdersRowMapper(),idUser);

        return list;
    }

    @Override
    public List<TaxiUserOrder> getAll() {
        return jdbcTemplate.query(sqlQuery, new TaxiUserOrdersRowMapper());
    }

    @Override
    public List<TaxiUserOrder> filterTaxiUserOrder(TaxiUserOrder taxiUserOrder) {
        return filterTaxiUserOrder(taxiUserOrder,null);
    }

    @Override
    public List<TaxiUserOrder> filterTaxiUserOrder(TaxiUserOrder taxiUserOrder, String sqlCondition) {
        String sqlFilterTaxiUserOrder = sqlQuery;
        List<TaxiUserOrder> list = null;
        int count = 0;

        //Этот класс предназначен для передачи в простой Map значений параметров методам NamedParameterJdbcTemplate класса.
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        if (!taxiUserOrder.isNew())
            parameterSource.addValue("id",taxiUserOrder.getId());
        if (taxiUserOrder.getDateTimeOrder() != null)
            parameterSource.addValue("dateTimeOrder",taxiUserOrder.getDateTimeOrder());
        if (taxiUserOrder.getIdUser() != null)
            parameterSource.addValue("idUser",taxiUserOrder.getIdUser().getId());
        if (taxiUserOrder.getAddressDeparture() != null)
            parameterSource.addValue("addressDeparture",taxiUserOrder.getAddressDeparture());
        if (taxiUserOrder.getAddressArrival() != null)
            parameterSource.addValue("addressArrival",taxiUserOrder.getAddressArrival());
        if (taxiUserOrder.getStartDate() != null)
            parameterSource.addValue("startDate",taxiUserOrder.getStartDate());
        if (taxiUserOrder.getEndDate() != null)
            parameterSource.addValue("endDate",taxiUserOrder.getEndDate());
        if (taxiUserOrder.getFulfilled() != null)
            parameterSource.addValue("fulfilled",taxiUserOrder.getFulfilled());

        for(var mapEntry: parameterSource.getValues().entrySet()){
            String paramName = mapEntry.getKey();

            if(count==0){
                switch (paramName){
                    case "id"                   -> sqlFilterTaxiUserOrder = sqlFilterTaxiUserOrder + " where tuo.id=:id \n";
                    case "dateTimeOrder"        -> sqlFilterTaxiUserOrder += " where tuo.date_time_order=:dateTimeOrder \n";
                    case "idUser"               -> sqlFilterTaxiUserOrder += " where tuo.id_user=:idUser \n";
                    case "addressDeparture"     -> sqlFilterTaxiUserOrder += " where tuo.address_departure=:addressDeparture \n";
                    case "addressArrival"       -> sqlFilterTaxiUserOrder += " where tuo.address_arrival=:addressArrival \n";
                    case "startDate"            -> sqlFilterTaxiUserOrder += " where tuo.start_date=:startDate \n";
                    case "endDate"              -> sqlFilterTaxiUserOrder += " where tuo.end_date=:endDate \n";
                    case "fulfilled"            ->  sqlFilterTaxiUserOrder += " where tuo.fulfilled=:fulfilled \n";
                }
            }else {
                if (paramName.equals("id"))
                    sqlFilterTaxiUserOrder += " and tuo.id=:id \n";
                if (paramName.equals("dateTimeOrder"))
                    sqlFilterTaxiUserOrder += " and tuo.date_time_order=:dateTimeOrder \n";
                if (paramName.equals("idUser"))
                    sqlFilterTaxiUserOrder += " and tuo.id_user=:idUser \n";
                if (paramName.equals("addressDeparture"))
                    sqlFilterTaxiUserOrder += " and tuo.address_departure=:addressDeparture \n";
                if (paramName.equals("addressArrival"))
                    sqlFilterTaxiUserOrder += " and tuo.address_arrival=:addressArrival \n";
                if (paramName.equals("startDate"))
                    sqlFilterTaxiUserOrder += " and tuo.start_date=:startDate \n";
                if (paramName.equals("endDate"))
                    sqlFilterTaxiUserOrder += " and tuo.end_date=:endDate \n";
                if (paramName.equals("fulfilled"))
                    sqlFilterTaxiUserOrder += " and tuo.fulfilled=:fulfilled \n";
            }
            count++;
        }

        if ((sqlCondition != null) && (!sqlCondition.equals(""))){
            if (parameterSource.getParameterNames().length > 0)
                sqlFilterTaxiUserOrder += " and ( "+ sqlCondition+" )";
            else
                sqlFilterTaxiUserOrder = sqlFilterTaxiUserOrder + " where ( "+sqlCondition+" )";
        }

        list = namedParameterJdbcTemplate.query(sqlFilterTaxiUserOrder,parameterSource,new TaxiUserOrdersRowMapper());

        return list;
    }
}
