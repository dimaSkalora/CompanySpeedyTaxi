package com.taxi.speedy.company.model;


//Транспортное средство
public class Vehicle  extends AbstractBaseEntity{
    private String name_car;                    //Название, модель ТС
    private String vehicle_number;              //Номер ТС
    private Integer year_issue;                 //Год выпуска ТС
    private String category;                    //Легковая, грузовая, автобус и т.д.
    private String color;                       //Цвет ТС
    private Integer fuel_consumption;           //Расход топлива л/100км

    public Vehicle() {
    }

    public Vehicle(Integer id, String name_car, String vehicle_number, Integer year_issue, String category, String color, Integer fuel_consumption) {
        super(id);
        this.name_car = name_car;
        this.vehicle_number = vehicle_number;
        this.year_issue = year_issue;
        this.category = category;
        this.color = color;
        this.fuel_consumption = fuel_consumption;
    }

    public Vehicle(String name_car, String vehicle_number, Integer year_issue, String category, String color, Integer fuel_consumption) {
        this(null, name_car, vehicle_number, year_issue, category, color, fuel_consumption);
    }

    public String getName_car() {
        return name_car;
    }

    public void setName_car(String name_car) {
        this.name_car = name_car;
    }

    public String getVehicle_number() {
        return vehicle_number;
    }

    public void setVehicle_number(String vehicle_number) {
        this.vehicle_number = vehicle_number;
    }

    public Integer getYear_issue() {
        return year_issue;
    }

    public void setYear_issue(Integer year_issue) {
        this.year_issue = year_issue;
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

    public Integer getFuel_consumption() {
        return fuel_consumption;
    }

    public void setFuel_consumption(Integer fuel_consumption) {
        this.fuel_consumption = fuel_consumption;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "name_car='" + name_car + '\'' +
                ", vehicle_number='" + vehicle_number + '\'' +
                ", year_issue=" + year_issue +
                ", category='" + category + '\'' +
                ", color='" + color + '\'' +
                ", fuel_consumption=" + fuel_consumption +
                ", id=" + id +
                '}';
    }
}