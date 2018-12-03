package com.taxi.speedy.company.model;

//Маршруты по заказам
public class TaxiRouteOnOrder extends AbstractBaseEntity{
    private UserVehicle idUserVehicle;
    private UserBankCard idUserBankCard;
    private TaxiOrderAcceptance idTaxiOrderAcceptance;
    private Integer landing;                                //Цена за посадку
    private Double tariffPerKilometer;                      //Тариф за километр
    private Double distance;                                //Растояние маршрута в километрах
    private Double farePayment;                             //Оплата за проезд
    private Double fuelConsuption;                          //Расход топлива

    public TaxiRouteOnOrder() {
    }

    public TaxiRouteOnOrder(UserVehicle idUserVehicle, UserBankCard idUserBankCard, TaxiOrderAcceptance idTaxiOrderAcceptance, Integer landing, Double tariffPerKilometer, Double distance, Double farePayment, Double fuelConsuption) {
        this.idUserVehicle = idUserVehicle;
        this.idUserBankCard = idUserBankCard;
        this.idTaxiOrderAcceptance = idTaxiOrderAcceptance;
        this.landing = landing;
        this.tariffPerKilometer = tariffPerKilometer;
        this.distance = distance;
        this.farePayment = farePayment;
        this.fuelConsuption = fuelConsuption;
    }

    public TaxiRouteOnOrder(Integer id, UserVehicle idUserVehicle, UserBankCard idUserBankCard, TaxiOrderAcceptance idTaxiOrderAcceptance, Integer landing, Double tariffPerKilometer, Double distance, Double farePayment, Double fuelConsuption) {
        super(id);
        this.idUserVehicle = idUserVehicle;
        this.idUserBankCard = idUserBankCard;
        this.idTaxiOrderAcceptance = idTaxiOrderAcceptance;
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

    public TaxiOrderAcceptance getIdTaxiOrderAcceptance() {
        return idTaxiOrderAcceptance;
    }

    public void setIdTaxiOrderAcceptance(TaxiOrderAcceptance idTaxiOrderAcceptance) {
        this.idTaxiOrderAcceptance = idTaxiOrderAcceptance;
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
        return "TaxiRouteOnOrder{" +
                "idUserVehicle=" + idUserVehicle +
                ", idUserBankCard=" + idUserBankCard +
                ", idTaxiOrderAcceptance=" + idTaxiOrderAcceptance +
                ", landing=" + landing +
                ", tariffPerKilometer=" + tariffPerKilometer +
                ", distance=" + distance +
                ", farePayment=" + farePayment +
                ", fuelConsuption=" + fuelConsuption +
                ", id=" + id +
                '}';
    }
}
