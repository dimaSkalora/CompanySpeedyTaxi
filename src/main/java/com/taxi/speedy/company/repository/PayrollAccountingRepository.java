package com.taxi.speedy.company.repository;

import com.taxi.speedy.company.model.PayrollAccounting;

import java.util.List;

public interface PayrollAccountingRepository {
    PayrollAccounting save(PayrollAccounting payrollAccounting);
    boolean delete(int id);
    PayrollAccounting get(int id);
    PayrollAccounting getIdDepartmentCompany(int idDepartmentCompany);
    PayrollAccounting getIdUserBankCard(int idUserBankCard);
    List<PayrollAccounting> getAllMonthYear(String monthYear);
    List<PayrollAccounting> getAllIdTypePayment(int idTypePayment);
    List<PayrollAccounting> getAll();
}
