package com.taxi.speedy.company.model;

public class VehicleState extends AbstractBaseEntity {
    private String nameVS;                          //Название состояния (робочая, не рабочая, и т.д.)

    public VehicleState() {
    }

    public VehicleState(Integer id, String nameVS) {
        super(id);
        this.nameVS = nameVS;
    }

    public String getNameVS() {
        return nameVS;
    }

    public void setNameVS(String nameVS) {
        this.nameVS = nameVS;
    }

    @Override
    public String toString() {
        return "VehicleState{" +
                "nameVS='" + nameVS + '\'' +
                ", id=" + id +
                '}';
    }
}