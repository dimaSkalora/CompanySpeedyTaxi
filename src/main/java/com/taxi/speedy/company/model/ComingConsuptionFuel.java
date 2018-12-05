package com.taxi.speedy.company.model;

//Приход, расход топлива
public class ComingConsuptionFuel extends AbstractBaseEntity {
    private String dateCCF;
    private UserVehicle idUserVehicle;
    private Double consuption;                          //Расход топливо литров
    private Double coming;                              //Приход топлива литров
    private Double remainder;                           //Остаток топливо литров
    private Double kilometer;                           //Сколько километров проехал юзер на ТС
    private Double totaKilometer;                       //Сколько всего километов проехал юзер на ТС

    public ComingConsuptionFuel() {
    }

    public ComingConsuptionFuel(Integer id, String dateCCF, UserVehicle idUserVehicle, Double consuption, Double coming, Double remainder, Double kilometer, Double totaKilometer) {
        super(id);
        this.dateCCF = dateCCF;
        this.idUserVehicle = idUserVehicle;
        this.consuption = consuption;
        this.coming = coming;
        this.remainder = remainder;
        this.kilometer = kilometer;
        this.totaKilometer = totaKilometer;
    }

    public ComingConsuptionFuel(String dateCCF, UserVehicle idUserVehicle, Double consuption, Double coming, Double remainder, Double kilometer, Double totaKilometer) {
        this.dateCCF = dateCCF;
        this.idUserVehicle = idUserVehicle;
        this.consuption = consuption;
        this.coming = coming;
        this.remainder = remainder;
        this.kilometer = kilometer;
        this.totaKilometer = totaKilometer;
    }

    public String getDateCCF() {
        return dateCCF;
    }

    public void setDateCCF(String dateCCF) {
        this.dateCCF = dateCCF;
    }

    public UserVehicle getIdUserVehicle() {
        return idUserVehicle;
    }

    public void setIdUserVehicle(UserVehicle idUserVehicle) {
        this.idUserVehicle = idUserVehicle;
    }

    public Double getConsuption() {
        return consuption;
    }

    public void setConsuption(Double consuption) {
        this.consuption = consuption;
    }

    public Double getComing() {
        return coming;
    }

    public void setComing(Double coming) {
        this.coming = coming;
    }

    public Double getRemainder() {
        return remainder;
    }

    public void setRemainder(Double remainder) {
        this.remainder = remainder;
    }

    public Double getKilometer() {
        return kilometer;
    }

    public void setKilometer(Double kilometer) {
        this.kilometer = kilometer;
    }

    public Double getTotaKilometer() {
        return totaKilometer;
    }

    public void setTotaKilometer(Double totaKilometer) {
        this.totaKilometer = totaKilometer;
    }

    @Override
    public String toString() {
        return "ComingConsuptionFuel{" +
                "dateCCF='" + dateCCF + '\'' +
                ", idUserVehicle=" + idUserVehicle +
                ", consuption=" + consuption +
                ", coming=" + coming +
                ", remainder=" + remainder +
                ", kilometer=" + kilometer +
                ", totaKilometer=" + totaKilometer +
                ", id=" + id +
                '}';
    }
}
