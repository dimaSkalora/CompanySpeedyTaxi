package com.taxi.speedy.company.repository;

import com.taxi.speedy.company.model.PaySheet;

import java.util.List;

public interface PaySheetRepository {
    PaySheet save(PaySheet paySheet);
    boolean delete(int id);
    PaySheet get(int id);
    PaySheet getIdUser(int idUser);
    PaySheet getIdBankCard(int idBankCard);
    PaySheet getIdDepartmentCompany(int idDepartmentCompany);
    PaySheet getMonthYear(String monthYear);
    List<PaySheet> getAllIdUser(int idUser);
    List<PaySheet> getAllIdBankCard(int idBankCard);
    List<PaySheet> getAllIdDepartmentCompany(int idDepartmentCompany);
    List<PaySheet> getAllMonthYear(String monthYear);
    List<PaySheet> getAll();
}
