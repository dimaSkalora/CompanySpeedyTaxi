package com.taxi.speedy.company.model;


//Транспортное средство
public class Vehicle  extends AbstractBaseEntity{
    private String nameCar;                    //Название, модель ТС
    private String vehicleNumber;              //Номер ТС
    private Integer yearIssue;                 //Год выпуска ТС
    private String category;                    //Легковая, грузовая, автобус и т.д.
    private String color;                       //Цвет ТС
    private Integer fuelConsumption;           //Расход топлива л/100км

    public Vehicle() {
    }

    public Vehicle(Integer id, String nameCar, String vehicleNumber, Integer yearIssue, String category, String color, Integer fuelConsumption) {
        super(id);
        this.nameCar = nameCar;
        this.vehicleNumber = vehicleNumber;
        this.yearIssue = yearIssue;
        this.category = category;
        this.color = color;
        this.fuelConsumption = fuelConsumption;
    }

    public Vehicle(String nameCar, String vehicleNumber, Integer yearIssue, String category, String color, Integer fuelConsumption) {
        this(null, nameCar, vehicleNumber, yearIssue, category, color, fuelConsumption);
    }

    public String getNameCar() {
        return nameCar;
    }

    public void setNameCar(String nameCar) {
        this.nameCar = nameCar;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public Integer getYearIssue() {
        return yearIssue;
    }

    public void setYearIssue(Integer yearIssue) {
        this.yearIssue = yearIssue;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(Integer fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "nameCar='" + nameCar + '\'' +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", yearIssue=" + yearIssue +
                ", category='" + category + '\'' +
                ", color='" + color + '\'' +
                ", fuelConsumption=" + fuelConsumption +
                ", id=" + id +
                '}';
    }
}