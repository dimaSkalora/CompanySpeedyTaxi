package com.taxi.speedy.company.repository;

import com.taxi.speedy.company.model.UserBankCard;

import java.util.List;

public interface UserBankCardRepository {
    UserBankCard save(UserBankCard userBankCard);
    boolean delete(int id);
    UserBankCard getIdUser(int idUser);
    UserBankCard getIdBankCard(int idBankCard);
    List<UserBankCard> getAllIdUser(int idUser);
    List<UserBankCard> getAll();
}
