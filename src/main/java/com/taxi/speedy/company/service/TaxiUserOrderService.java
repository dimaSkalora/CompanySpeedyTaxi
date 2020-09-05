package com.taxi.speedy.company.service;

import com.taxi.speedy.company.model.TaxiUserOrder;
import com.taxi.speedy.company.util.exception.NotFoundException;

import java.util.List;

public interface TaxiUserOrderService {
    TaxiUserOrder create(TaxiUserOrder taxiUserOrder);
    TaxiUserOrder update(TaxiUserOrder taxiUserOrder);
    boolean delete(int id) throws NotFoundException;//NotFoundException - Об'экт не обнаружен
    TaxiUserOrder get(int id) throws NotFoundException;//NotFoundException - Об'экт не обнаружен
    List<TaxiUserOrder> getAllIdUser(int idUser) throws NotFoundException;//NotFoundException - Об'экт не обнаружен
    List<TaxiUserOrder> getAll();
    List<TaxiUserOrder> filterTaxiUserOrder(TaxiUserOrder taxiUserOrder);
    List<TaxiUserOrder> filterTaxiUserOrder(TaxiUserOrder taxiUserOrder,String sqlCondition);
}
