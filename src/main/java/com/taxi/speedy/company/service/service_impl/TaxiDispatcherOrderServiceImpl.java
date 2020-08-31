package com.taxi.speedy.company.service.service_impl;

import com.taxi.speedy.company.model.TaxiDispatcherOrder;
import com.taxi.speedy.company.repository.TaxiDispatcherOrderRepository;
import com.taxi.speedy.company.service.TaxiDispatcherOrderService;
import com.taxi.speedy.company.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;

import static com.taxi.speedy.company.util.ValidationUtil.checkNotFound;
import static com.taxi.speedy.company.util.ValidationUtil.checkNotFoundWithId;

@Service("taxiDispatcherOrderServiceImpl")
public class TaxiDispatcherOrderServiceImpl implements TaxiDispatcherOrderService {
    @Autowired
    private TaxiDispatcherOrderRepository taxiDispatcherOrderRepository;

    @Override
    public TaxiDispatcherOrder create(TaxiDispatcherOrder taxiDispatcherOrder) {
        Assert.notNull(taxiDispatcherOrder,"не должно быть null");
        return taxiDispatcherOrderRepository.save(taxiDispatcherOrder);
    }

    @Override
    public TaxiDispatcherOrder update(TaxiDispatcherOrder taxiDispatcherOrder) throws NotFoundException {
        Assert.notNull(taxiDispatcherOrder,"не должно быть null");
        //Проверка - не найден с идентификатором
        return checkNotFoundWithId(taxiDispatcherOrderRepository.save(taxiDispatcherOrder),taxiDispatcherOrder.getId());
    }

    @Override
    public boolean delete(int id) throws NotFoundException {
        //Проверка - не найден с идентификатором
        return checkNotFoundWithId(Boolean.valueOf(taxiDispatcherOrderRepository.delete(id)),id);
    }

    @Override
    public TaxiDispatcherOrder get(int id) throws NotFoundException {
        //Проверка - не найден с идентификатором
        return checkNotFoundWithId(taxiDispatcherOrderRepository.get(id),id);
    }

    @Override
    public List<TaxiDispatcherOrder> getAll() {
        return taxiDispatcherOrderRepository.getAll();
    }

    @Override
    public List<TaxiDispatcherOrder> getByIdTaxiDispatcher(int idTaxiDispatcher) throws NotFoundException {
        return checkNotFound(taxiDispatcherOrderRepository.getByIdTaxiDispatcher(idTaxiDispatcher),"idTaxiDispatcher = "+idTaxiDispatcher);
    }

    @Override
    public List<TaxiDispatcherOrder> getByAddressDeparture(String addressDeparture) throws NotFoundException {
        //Проверка - не найден
        return checkNotFound(taxiDispatcherOrderRepository.getByAddressDeparture(addressDeparture),"addressDeparture = "+addressDeparture);
    }

    @Override
    public List<TaxiDispatcherOrder> getByAddressArrival(String addressArrival) throws NotFoundException {
        Assert.notNull(addressArrival,"не должно быть null");
        return taxiDispatcherOrderRepository.getByAddressArrival(addressArrival);
    }

    @Override
    public List<TaxiDispatcherOrder> getByUserName(String userName) throws NotFoundException {
        Assert.notNull(userName,"не должно быть null");
        return taxiDispatcherOrderRepository.getByUserName(userName);
    }

    @Override
    public List<TaxiDispatcherOrder> getByUserPhone(String userPhone) throws NotFoundException {
        Assert.notNull(userPhone,"не должно быть null");
        return taxiDispatcherOrderRepository.getByUserPhone(userPhone);
    }

    @Override
    public List<TaxiDispatcherOrder> getByFulfilled(int fulfilled) throws NotFoundException {
        return checkNotFound(taxiDispatcherOrderRepository.getByFulfilled(fulfilled),"fulfilled = "+fulfilled);
    }

    @Override
    public List<TaxiDispatcherOrder> getByBetweenStartDate(LocalDateTime startDateTime, LocalDateTime endDateTime) throws NotFoundException {
        Assert.notNull(startDateTime,"не должно быть null");
        Assert.notNull(endDateTime,"не должно быть null");

        return taxiDispatcherOrderRepository.getByBetweenStartDate(startDateTime,endDateTime);
    }

    @Override
    public List<TaxiDispatcherOrder> getByBetweenEndDate(LocalDateTime startDateTime, LocalDateTime endDateTime) throws NotFoundException {
        Assert.notNull(startDateTime,"не должно быть null");
        Assert.notNull(endDateTime,"не должно быть null");

        return taxiDispatcherOrderRepository.getByBetweenEndDate(startDateTime,endDateTime);
    }

    @Override
    public List<TaxiDispatcherOrder> getFilterTaxiDispatcherOrder(TaxiDispatcherOrder taxiDispatcherOrder) throws NotFoundException {
        Assert.notNull(taxiDispatcherOrder,"не должно быть null");

        return taxiDispatcherOrderRepository.getFilterTaxiDispatcherOrder(taxiDispatcherOrder);
    }

    @Override
    public List<TaxiDispatcherOrder> getFilterTaxiDispatcherOrder(TaxiDispatcherOrder taxiDispatcherOrder, String sqlCondition) throws NotFoundException {
        Assert.notNull(taxiDispatcherOrder,"не должно быть null");
        Assert.notNull(sqlCondition,"не должно быть null");

        return taxiDispatcherOrderRepository.getFilterTaxiDispatcherOrder(taxiDispatcherOrder,sqlCondition);
    }
}
