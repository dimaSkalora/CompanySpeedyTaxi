package com.taxi.speedy.company.model;

import java.time.LocalDateTime;

//Банковськие операции
public class BankCardOperation extends AbstractBaseEntity {
    private UserBankCard idUserBankCard;                    //Юзер и его банковськая карта
    private LocalDateTime dateTimeArrivelMoney;             //Дата и время прихода денег
    private BankCard idBankCardArrivel;                     //От какой банковськой карты перечислили приход денег
    private Double arrivalMoney;                            //Приход денег
    private LocalDateTime dateTimeSpendingMoney;            //Дата и время расхода денег
    private BankCard idBankCardSpending;                    //На какую банковськую карту перечислили расход денег
    private Double spendingMoney;                           //Расход денег
    private Double moneyBalance;                            //Всего денег на банковськой карте

    public BankCardOperation() {
    }

    public BankCardOperation(UserBankCard idUserBankCard, LocalDateTime dateTimeArrivelMoney, BankCard idBankCardArrivel, Double arrivalMoney, LocalDateTime dateTimeSpendingMoney, BankCard idBankCardSpending, Double spendingMoney, Double moneyBalance) {
        this.idUserBankCard = idUserBankCard;
        this.dateTimeArrivelMoney = dateTimeArrivelMoney;
        this.idBankCardArrivel = idBankCardArrivel;
        this.arrivalMoney = arrivalMoney;
        this.dateTimeSpendingMoney = dateTimeSpendingMoney;
        this.idBankCardSpending = idBankCardSpending;
        this.spendingMoney = spendingMoney;
        this.moneyBalance = moneyBalance;
    }

    public BankCardOperation(Integer id, UserBankCard idUserBankCard, LocalDateTime dateTimeArrivelMoney, BankCard idBankCardArrivel, Double arrivalMoney, LocalDateTime dateTimeSpendingMoney, BankCard idBankCardSpending, Double spendingMoney, Double moneyBalance) {
        super(id);
        this.idUserBankCard = idUserBankCard;
        this.dateTimeArrivelMoney = dateTimeArrivelMoney;
        this.idBankCardArrivel = idBankCardArrivel;
        this.arrivalMoney = arrivalMoney;
        this.dateTimeSpendingMoney = dateTimeSpendingMoney;
        this.idBankCardSpending = idBankCardSpending;
        this.spendingMoney = spendingMoney;
        this.moneyBalance = moneyBalance;
    }

    public UserBankCard getIdUserBankCard() {
        return idUserBankCard;
    }

    public void setIdUserBankCard(UserBankCard idUserBankCard) {
        this.idUserBankCard = idUserBankCard;
    }

    public LocalDateTime getDateTimeArrivelMoney() {
        return dateTimeArrivelMoney;
    }

    public void setDateTimeArrivelMoney(LocalDateTime dateTimeArrivelMoney) {
        this.dateTimeArrivelMoney = dateTimeArrivelMoney;
    }

    public BankCard getIdBankCardArrivel() {
        return idBankCardArrivel;
    }

    public void setIdBankCardArrivel(BankCard idBankCardArrivel) {
        this.idBankCardArrivel = idBankCardArrivel;
    }

    public Double getArrivalMoney() {
        return arrivalMoney;
    }

    public void setArrivalMoney(Double arrivalMoney) {
        this.arrivalMoney = arrivalMoney;
    }

    public LocalDateTime getDateTimeSpendingMoney() {
        return dateTimeSpendingMoney;
    }

    public void setDateTimeSpendingMoney(LocalDateTime dateTimeSpendingMoney) {
        this.dateTimeSpendingMoney = dateTimeSpendingMoney;
    }

    public BankCard getIdBankCardSpending() {
        return idBankCardSpending;
    }

    public void setIdBankCardSpending(BankCard idBankCardSpending) {
        this.idBankCardSpending = idBankCardSpending;
    }

    public Double getSpendingMoney() {
        return spendingMoney;
    }

    public void setSpendingMoney(Double spendingMoney) {
        this.spendingMoney = spendingMoney;
    }

    public Double getMoneyBalance() {
        return moneyBalance;
    }

    public void setMoneyBalance(Double moneyBalance) {
        this.moneyBalance = moneyBalance;
    }

    @Override
    public String toString() {
        return "BankCardOperation{" +
                "idUserBankCard=" + idUserBankCard +
                ", dateTimeArrivelMoney=" + dateTimeArrivelMoney +
                ", idBankCardArrivel=" + idBankCardArrivel +
                ", arrivalMoney=" + arrivalMoney +
                ", dateTimeSpendingMoney=" + dateTimeSpendingMoney +
                ", idBankCardSpending=" + idBankCardSpending +
                ", spendingMoney=" + spendingMoney +
                ", moneyBalance=" + moneyBalance +
                ", id=" + id +
                '}';
    }
}
