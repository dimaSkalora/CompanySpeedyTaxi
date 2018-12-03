package com.taxi.speedy.company.model;

import java.time.LocalDateTime;

//Заправка ТС
public class RefillingCar extends AbstractBaseEntity {
    private UserVehicle idUserVehicle;
    private UserBankCard idUserBankCard;
    private LocalDateTime dateTime;                 //Дата и время заправки
    private Double liter;                           //Литров заправлено
    private Double pricePerLiter;                   //Цена за литр
    private Double paymentOfRefueling;              //Оплата за дозапраку

    public RefillingCar() {
    }

    public RefillingCar(Integer id, UserVehicle idUserVehicle, UserBankCard idUserBankCard, LocalDateTime dateTime, Double liter, Double pricePerLiter, Double paymentOfRefueling) {
        super(id);
        this.idUserVehicle = idUserVehicle;
        this.idUserBankCard = idUserBankCard;
        this.dateTime = dateTime;
        this.liter = liter;
        this.pricePerLiter = pricePerLiter;
        this.paymentOfRefueling = paymentOfRefueling;
    }

    public RefillingCar(Integer id, UserVehicle idUserVehicle, UserBankCard idUserBankCard, Double liter, Double pricePerLiter, Double paymentOfRefueling) {
        this(id, idUserVehicle, idUserBankCard, LocalDateTime.now() , liter, pricePerLiter, paymentOfRefueling);
    }

    public UserVehicle getIdUserVehicle() {
        return idUserVehicle;
    }

    public void setIdUserVehicle(UserVehicle idUserVehicle) {
        this.idUserVehicle = idUserVehicle;
    }

    public UserBankCard getIdUserBankCard() {
        return idUserBankCard;
    }

    public void setIdUserBankCard(UserBankCard idUserBankCard) {
        this.idUserBankCard = idUserBankCard;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Double getLiter() {
        return liter;
    }

    public void setLiter(Double liter) {
        this.liter = liter;
    }

    public Double getPricePerLiter() {
        return pricePerLiter;
    }

    public void setPricePerLiter(Double pricePerLiter) {
        this.pricePerLiter = pricePerLiter;
    }

    public Double getPaymentOfRefueling() {
        return paymentOfRefueling;
    }

    public void setPaymentOfRefueling(Double paymentOfRefueling) {
        this.paymentOfRefueling = paymentOfRefueling;
    }

    @Override
    public String toString() {
        return "RefillingCar{" +
                "idUserVehicle=" + idUserVehicle +
                ", idUserBankCard=" + idUserBankCard +
                ", dateTime=" + dateTime +
                ", liter=" + liter +
                ", pricePerLiter=" + pricePerLiter +
                ", paymentOfRefueling=" + paymentOfRefueling +
                ", id=" + id +
                '}';
    }
}
