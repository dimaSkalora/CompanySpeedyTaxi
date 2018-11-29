package com.taxi.speedy.company.model;

//Банковськая карта для заправки
public class BankCard extends AbstractBaseEntity{
    private String bankCardNmber;               //Номер банковськой карты (Номер должен быть уникальным)
    private TypeBankCard idTypeBankCard;        //Тип банковськой карты (refueling, driver, salary, company)
    private Double moneyBalance;                //Всего денег на банковськой карте
    private Integer isActive;                   //1 - активная, 0 - не активная

    public BankCard() {
    }

    public BankCard(String bankCardNmber, TypeBankCard idTypeBankCard, Double moneyBalance, Integer isActive) {
        this.bankCardNmber = bankCardNmber;
        this.idTypeBankCard = idTypeBankCard;
        this.moneyBalance = moneyBalance;
        this.isActive = isActive;
    }

    public BankCard(Integer id, String bankCardNmber, TypeBankCard idTypeBankCard, Double moneyBalance, Integer isActive) {
        super(id);
        this.bankCardNmber = bankCardNmber;
        this.idTypeBankCard = idTypeBankCard;
        this.moneyBalance = moneyBalance;
        this.isActive = isActive;
    }

    public String getBankCardNmber() {
        return bankCardNmber;
    }

    public void setBankCardNmber(String bankCardNmber) {
        this.bankCardNmber = bankCardNmber;
    }

    public TypeBankCard getIdTypeBankCard() {
        return idTypeBankCard;
    }

    public void setIdTypeBankCard(TypeBankCard idTypeBankCard) {
        this.idTypeBankCard = idTypeBankCard;
    }

    public Double getMoneyBalance() {
        return moneyBalance;
    }

    public void setMoneyBalance(Double moneyBalance) {
        this.moneyBalance = moneyBalance;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "BankCard{" +
                "bankCardNmber='" + bankCardNmber + '\'' +
                ", idTypeBankCard=" + idTypeBankCard +
                ", moneyBalance=" + moneyBalance +
                ", isActive=" + isActive +
                ", id=" + id +
                '}';
    }
}
