package com.taxi.speedy.company.repository.spring_jdbc;

import com.taxi.speedy.company.model.TaxiOrderAcceptance;
import com.taxi.speedy.company.repository.TaxiOrderAcceptanceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository//("taxiOrderAcceptanceRepositoryImpl")
public class JdbcTaxiOrderAcceptanceRepositoryImpl implements TaxiOrderAcceptanceRepository {
    @Override
    public TaxiOrderAcceptance save(TaxiOrderAcceptance taxiOrderAcceptance) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public TaxiOrderAcceptance get(int id) {
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
