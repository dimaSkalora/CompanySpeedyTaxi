package com.taxi.speedy.company.service.service_impl;

import com.taxi.speedy.company.model.TaxiOrderAcceptance;
import com.taxi.speedy.company.repository.TaxiOrderAcceptanceRepository;
import com.taxi.speedy.company.service.TaxiOrderAcceptanceService;
import com.taxi.speedy.company.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaxiOrderAcceptanceServiceImpl implements TaxiOrderAcceptanceService {

    //@Autowired
    //@Qualifier("jdbcTaxiOrderAcceptanceRepository")
    private TaxiOrderAcceptanceRepository taxiOrderAcceptanceRepository;

    @Autowired
    public TaxiOrderAcceptanceServiceImpl(TaxiOrderAcceptanceRepository taxiOrderAcceptanceRepository) {
        this.taxiOrderAcceptanceRepository = taxiOrderAcceptanceRepository;
    }

    @Override
    public TaxiOrderAcceptance save(TaxiOrderAcceptance taxiOrderAcceptance) {
        return null;
    }

    @Override
    public void update(TaxiOrderAcceptance taxiOrderAcceptance) {

    }

    @Override
    public boolean delete(int id) throws NotFoundException {
        return false;
    }

    @Override
    public TaxiOrderAcceptance get(int id) throws NotFoundException {
        return null;
    }

    @Override
    public List<TaxiOrderAcceptance> getAll() {
        return null;
    }

    @Override
    public List<TaxiOrderAcceptance> filterTaxiUserOrder(TaxiOrderAcceptance taxiOrderAcceptance) {
        return null;
    }

    @Override
    public List<TaxiOrderAcceptance> filterTaxiUserOrder(TaxiOrderAcceptance taxiOrderAcceptance, String sqlCondition) {
        return null;
    }
}
