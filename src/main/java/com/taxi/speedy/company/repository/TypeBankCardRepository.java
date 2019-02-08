package com.taxi.speedy.company.repository;

import com.taxi.speedy.company.model.TypeBankCard;

import java.util.List;

public interface TypeBankCardRepository {
    TypeBankCard save(TypeBankCard typeBankCard);
    boolean delete(int id);
    TypeBankCard get(int id);
    TypeBankCard getTypeName(String typeName);
    List<TypeBankCard> getAll();
}
