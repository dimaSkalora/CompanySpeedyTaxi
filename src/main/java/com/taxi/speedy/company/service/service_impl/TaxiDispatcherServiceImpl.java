package com.taxi.speedy.company.service.service_impl;

import com.taxi.speedy.company.model.TaxiDispatcher;
import com.taxi.speedy.company.repository.TaxiDispatcherRepository;
import com.taxi.speedy.company.service.TaxiDispatcherService;
import com.taxi.speedy.company.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static com.taxi.speedy.company.util.ValidationUtil.checkNotFoundWithId;

@Service
public class TaxiDispatcherServiceImpl implements TaxiDispatcherService {

    private TaxiDispatcherRepository taxiDispatcherRepository;

    @Autowired
    public TaxiDispatcherServiceImpl(TaxiDispatcherRepository taxiDispatcherRepository) {
        this.taxiDispatcherRepository = taxiDispatcherRepository;
    }

    @Override
    public TaxiDispatcher create(TaxiDispatcher taxiDispatcher) {
        Assert.notNull(taxiDispatcher,"не должно быть null");
        return taxiDispatcherRepository.save(taxiDispatcher);
    }

    @Override
    public TaxiDispatcher update(TaxiDispatcher taxiDispatcher) throws NotFoundException {
        Assert.notNull(taxiDispatcher,"не должно быть null");
        //Проверка - не найден с идентификатором
        return checkNotFoundWithId(taxiDispatcherRepository.save(taxiDispatcher),taxiDispatcher.getId());
    }

    @Override
    public TaxiDispatcher get(int id) throws NotFoundException {
        //Проверка - не найден с идентификатором
        return checkNotFoundWithId(taxiDispatcherRepository.get(id),id);
    }

    @Override
    public List<TaxiDispatcher> getAll() {
        return taxiDispatcherRepository.getAll();
    }
}
