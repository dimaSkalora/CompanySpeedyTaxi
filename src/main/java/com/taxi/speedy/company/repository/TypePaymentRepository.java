package com.taxi.speedy.company.repository;

import com.taxi.speedy.company.model.TypePayment;

import java.util.List;

public interface TypePaymentRepository {
   TypePayment save(TypePayment typePayment);
   boolean delete(int id);
   TypePayment get(int id);
   TypePayment getNameTp(String nameTp);
   List<TypePayment> getAll();
}