package com.taxi.speedy.company.service.service_impl;

import com.taxi.speedy.company.model.TaxiOrderAcceptance;
import com.taxi.speedy.company.repository.TaxiOrderAcceptanceRepository;
import com.taxi.speedy.company.service.TaxiOrderAcceptanceService;
import com.taxi.speedy.company.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static com.taxi.speedy.company.util.ValidationUtil.checkNotFoundWithId;

@Service//("taxiOrderAcceptanceServiceImpl")
public class TaxiOrderAcceptanceServiceImpl implements TaxiOrderAcceptanceService {

    //@Autowired
    //@Qualifier("jdbcTaxiOrderAcceptanceRepository")
    private TaxiOrderAcceptanceRepository taxiOrderAcceptanceRepository;

    @Autowired
    public TaxiOrderAcceptanceServiceImpl(TaxiOrderAcceptanceRepository taxiOrderAcceptanceRepository) {
        this.taxiOrderAcceptanceRepository = taxiOrderAcceptanceRepository;
    }

    @Override
    public TaxiOrderAcceptance create(TaxiOrderAcceptance taxiOrderAcceptance) {
        Assert.notNull(taxiOrderAcceptance,"не должно быть null");
        return taxiOrderAcceptanceRepository.save(taxiOrderAcceptance);
    }

    @Override
    public void update(TaxiOrderAcceptance taxiOrderAcceptance) {
        Assert.notNull(taxiOrderAcceptance,"не должно быть null");
        //Проверка - не найден с идентификатором
        checkNotFoundWithId(taxiOrderAcceptanceRepository.save(taxiOrderAcceptance),
                taxiOrderAcceptance.getId());
    }

    @Override
    public boolean delete(int id) throws NotFoundException {
        //Проверка - не найден с идентификатором
        return checkNotFoundWithId(Boolean.valueOf(taxiOrderAcceptanceRepository.delete(id)),id);
    }

    @Override
    public TaxiOrderAcceptance get(int id) throws NotFoundException {
        //Проверка - не найден с идентификатором
        return checkNotFoundWithId(taxiOrderAcceptanceRepository.get(id),id);
    }

    @Override
    public List<TaxiOrderAcceptance> getAll() {
        return taxiOrderAcceptanceRepository.getAll();
    }

    @Override
    public List<TaxiOrderAcceptance> filterTaxiUserOrder(TaxiOrderAcceptance taxiOrderAcceptance) {
        Assert.notNull(taxiOrderAcceptance,"не должно быть null");
        return taxiOrderAcceptanceRepository.filterTaxiUserOrder(taxiOrderAcceptance);
    }

    @Override
    public List<TaxiOrderAcceptance> filterTaxiUserOrder(TaxiOrderAcceptance taxiOrderAcceptance, String sqlCondition) {
        Assert.notNull(taxiOrderAcceptance,"не должно быть null");
        Assert.notNull(sqlCondition,"не должно быть null");

        return taxiOrderAcceptanceRepository.filterTaxiUserOrder(taxiOrderAcceptance,sqlCondition);
    }
}
