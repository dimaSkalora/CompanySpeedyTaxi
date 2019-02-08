package com.taxi.speedy.company.repository;

import com.taxi.speedy.company.model.BankCard;

import java.util.List;

public interface BankCardRepository {
    BankCard save(BankCard bankCard);
    boolean delete(int id);
    BankCard get(int id);
    BankCard getBankCardNmber(String bankCardNmber);
    BankCard getIdTypeBankCard(int idTypeBankCard);
    List<BankCard> getIsActive(int isActive);
    List<BankCard> getAll();
}
