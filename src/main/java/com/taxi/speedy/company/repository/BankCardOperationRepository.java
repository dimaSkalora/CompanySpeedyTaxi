package com.taxi.speedy.company.repository;

import com.taxi.speedy.company.model.BankCardOperation;

import java.time.LocalDateTime;
import java.util.List;

public interface BankCardOperationRepository {
    BankCardOperation save(BankCardOperation bankCardOperation);
    boolean delete(int id);
    BankCardOperation getIdUserBankCard(int idUserBankCard);
    BankCardOperation getDateTimeArrivelMoney(LocalDateTime dateTimeArrivelMoney);          //Дата и время прихода денег
    BankCardOperation getIdBankCardArrivel(int idBankCardArrivel);                          //От какой банковськой карты перечислили приход денег
    BankCardOperation getDateTimeSpendingMoney(LocalDateTime dateTimeSpendingMoney);        //Дата и время расхода денег
    BankCardOperation getIdBankCardSpending(int idBankCardSpending);                        //На какую банковськую карту перечислили расход денег
    BankCardOperation getIdUserBankCardDateTimeArrivelMoney(int idUserBankCard, LocalDateTime dateTimeArrivelMoney);
    List<BankCardOperation> getAllIdUserBankCard(int idUserBankCard);
    List<BankCardOperation> getAllIdBankCardArrivel(int idBankCardArrivel);
    List<BankCardOperation> getAllDateTimeArrivelMoney(LocalDateTime dateTimeArrivelMoney);
    List<BankCardOperation> getAllDateTimeSpendingMoney(LocalDateTime dateTimeSpendingMoney);
    List<BankCardOperation> getAllIdUserBankCardDateTimeArrivelMoney(int idUserBankCard, LocalDateTime dateTimeArrivelMoney);
    List<BankCardOperation> getAll();
}
