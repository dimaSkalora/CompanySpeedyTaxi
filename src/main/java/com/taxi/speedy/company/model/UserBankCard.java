package com.taxi.speedy.company.model;

//Банковськая карта usera
public class UserBankCard extends AbstractBaseEntity {
    private User idUser;
    private BankCard idBankCard;

    public UserBankCard() {
    }

    public UserBankCard(Integer id, User idUser, BankCard idBankCard) {
        super(id);
        this.idUser = idUser;
        this.idBankCard = idBankCard;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public BankCard getIdBankCard() {
        return idBankCard;
    }

    public void setIdBankCard(BankCard idBankCard) {
        this.idBankCard = idBankCard;
    }

    @Override
    public String toString() {
        return "UserBankCard{" +
                "idUser=" + idUser +
                ", idBankCard=" + idBankCard +
                ", id=" + id +
                '}';
    }
}
