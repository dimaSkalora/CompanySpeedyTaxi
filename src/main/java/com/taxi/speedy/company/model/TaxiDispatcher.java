package com.taxi.speedy.company.model;

public class TaxiDispatcher extends AbstractBaseEntity{
    private User idUser;

    public TaxiDispatcher() {
    }

    public TaxiDispatcher(User idUser) {
        this.idUser = idUser;
    }

    public TaxiDispatcher(Integer id, User idUser) {
        super(id);
        this.idUser = idUser;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "TaxiDispatcher{" +
                "idUser=" + idUser +
                ", id=" + id +
                '}';
    }
}
