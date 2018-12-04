package com.taxi.speedy.company.model;

import java.time.LocalDateTime;

//Маршруты
public class TaxiRoute extends AbstractBaseEntity {
    private UserVehicle idUserVehicle;
    private UserBankCard idUserBankCard;
    private String departurePoint;                  //Пунк отправление
    private String arrivalPoint;                    //Пунк прибытие
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer landing;                        //Цена за посадку
    private Double tariffPerKilometer;              //Тариф за километр
    private Double distance;                        //Растояние маршрута в километрах
    private Double farePayment;                     //Оплата за проезд
    private Double fuelConsuption;                  //Расход топлива

    public TaxiRoute() {
    }

    public TaxiRoute(UserVehicle idUserVehicle, UserBankCard idUserBankCard, String departurePoint, String arrivalPoint, LocalDateTime startDate, LocalDateTime endDate, Integer landing, Double tariffPerKilometer, Double distance, Double farePayment, Double fuelConsuption) {
        this.idUserVehicle = idUserVehicle;
        this.idUserBankCard = idUserBankCard;
        this.departurePoint = departurePoint;
        this.arrivalPoint = arrivalPoint;
        this.startDate = startDate;
        this.endDate = endDate;
        this.landing = landing;
        this.tariffPerKilometer = tariffPerKilometer;
        this.distance = distance;
        this.farePayment = farePayment;
        this.fuelConsuption = fuelConsuption;
    }

    public TaxiRoute(Integer id, UserVehicle idUserVehicle, UserBankCard idUserBankCard, String departurePoint, String arrivalPoint, LocalDateTime startDate, LocalDateTime endDate, Integer landing, Double tariffPerKilometer, Double distance, Double farePayment, Double fuelConsuption) {
        super(id);
        this.idUserVehicle = idUserVehicle;
        this.idUserBankCard = idUserBankCard;
        this.departurePoint = departurePoint;
        this.arrivalPoint = arrivalPoint;
        this.startDate = startDate;
        this.endDate = endDate;
        this.landing = landing;
        this.tariffPerKilometer = tariffPerKilometer;
        this.distance = distance;
        this.farePayment = farePayment;
        this.fuelConsuption = fuelConsuption;
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

    public String getDeparturePoint() {
        return departurePoint;
    }

    public void setDeparturePoint(String departurePoint) {
        this.departurePoint = departurePoint;
    }

    public String getArrivalPoint() {
        return arrivalPoint;
    }

    public void setArrivalPoint(String arrivalPoint) {
        this.arrivalPoint = arrivalPoint;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Integer getLanding() {
        return landing;
    }

    public void setLanding(Integer landing) {
        this.landing = landing;
    }

    public Double getTariffPerKilometer() {
        return tariffPerKilometer;
    }

    public void setTariffPerKilometer(Double tariffPerKilometer) {
        this.tariffPerKilometer = tariffPerKilometer;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getFarePayment() {
        return farePayment;
    }

    public void setFarePayment(Double farePayment) {
        this.farePayment = farePayment;
    }

    public Double getFuelConsuption() {
        return fuelConsuption;
    }

    public void setFuelConsuption(Double fuelConsuption) {
        this.fuelConsuption = fuelConsuption;
    }

    @Override
    public String toString() {
        return "TaxiRoute{" +
                "idUserVehicle=" + idUserVehicle +
                ", idUserBankCard=" + idUserBankCard +
                ", departurePoint='" + departurePoint + '\'' +
                ", arrivalPoint='" + arrivalPoint + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", landing=" + landing +
                ", tariffPerKilometer=" + tariffPerKilometer +
                ", distance=" + distance +
                ", farePayment=" + farePayment +
                ", fuelConsuption=" + fuelConsuption +
                ", id=" + id +
                '}';
    }
}
