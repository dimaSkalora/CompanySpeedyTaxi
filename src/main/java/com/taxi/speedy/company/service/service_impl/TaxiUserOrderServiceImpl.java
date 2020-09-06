package com.taxi.speedy.company.service.service_impl;

import com.taxi.speedy.company.model.TaxiUserOrder;
import com.taxi.speedy.company.repository.TaxiUserOrderRepository;
import com.taxi.speedy.company.service.TaxiUserOrderService;
import com.taxi.speedy.company.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static com.taxi.speedy.company.util.ValidationUtil.checkNotFound;
import static com.taxi.speedy.company.util.ValidationUtil.checkNotFoundWithId;

@Service//("taxiUserOrderServiceImpl")
public class TaxiUserOrderServiceImpl implements TaxiUserOrderService {
    //@Autowired
    //@Qualifier("jdbcTaxiUserOrderRepositoryImpl")
    private TaxiUserOrderRepository taxiUserOrderRepository;

    @Autowired
    public TaxiUserOrderServiceImpl(TaxiUserOrderRepository taxiUserOrderRepository) {
        this.taxiUserOrderRepository = taxiUserOrderRepository;
    }

    @Override
    public TaxiUserOrder create(TaxiUserOrder taxiUserOrder) {
        Assert.notNull(taxiUserOrder,"не должно быть null");
        return taxiUserOrderRepository.save(taxiUserOrder);
    }

    @Override
    public TaxiUserOrder update(TaxiUserOrder taxiUserOrder) {
        Assert.notNull(taxiUserOrder,"не должно быть null");
        //Проверка - не найден с идентификатором
        return checkNotFoundWithId(taxiUserOrderRepository.save(taxiUserOrder),taxiUserOrder.getId());
    }

    @Override
    public boolean delete(int id) throws NotFoundException {
        //Проверка - не найден с идентификатором
        return checkNotFoundWithId(Boolean.valueOf(taxiUserOrderRepository.delete(id)),id);
    }

    @Override
    public TaxiUserOrder get(int id) throws NotFoundException {
        //Проверка - не найден с идентификатором
        return checkNotFoundWithId(taxiUserOrderRepository.get(id),id);
    }

    @Override
    public List<TaxiUserOrder> getAllIdUser(int idUser) throws NotFoundException {
        return taxiUserOrderRepository.getAllIdUser(idUser);
    }

    @Override
    public List<TaxiUserOrder> getAll() {
        return taxiUserOrderRepository.getAll();
    }

    @Override
    public List<TaxiUserOrder> filterTaxiUserOrder(TaxiUserOrder taxiUserOrder) {
        Assert.notNull(taxiUserOrder,"не должно быть null");
        return taxiUserOrderRepository.filterTaxiUserOrder(taxiUserOrder);
    }

    @Override
    public List<TaxiUserOrder> filterTaxiUserOrder(TaxiUserOrder taxiUserOrder, String sqlCondition) {
        return taxiUserOrderRepository.filterTaxiUserOrder(taxiUserOrder,sqlCondition);
    }
}
