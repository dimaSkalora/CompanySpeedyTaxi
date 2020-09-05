package com.taxi.speedy.company.repository;

import com.taxi.speedy.company.model.TaxiUserOrder;
import java.util.List;

public interface TaxiUserOrderRepository {
    TaxiUserOrder save(TaxiUserOrder taxiUserOrder);
    boolean delete(int id);
    TaxiUserOrder get(int id);
    List<TaxiUserOrder> getAllIdUser(int idUser);
    List<TaxiUserOrder> getAll();
    List<TaxiUserOrder> filterTaxiUserOrder(TaxiUserOrder taxiUserOrder);
    List<TaxiUserOrder> filterTaxiUserOrder(TaxiUserOrder taxiUserOrder,String sqlCondition);
}